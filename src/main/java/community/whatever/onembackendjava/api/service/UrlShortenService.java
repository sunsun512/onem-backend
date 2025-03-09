package community.whatever.onembackendjava.api.service;

import community.whatever.onembackendjava.api.dto.ShortenUrlDto;
import community.whatever.onembackendjava.common.error.BusinessException;
import community.whatever.onembackendjava.common.error.BusinessExceptionGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Slf4j
@Service
public class UrlShortenService {

    private static final String UrlRegex = "https?://(?:www\\.)?[a-zA-Z0-9./]+";
    private static final Pattern URL_PATTERN = Pattern.compile(UrlRegex);

    private final Map<String, String> shortenUrls = new HashMap<>();

    public ShortenUrlDto.Get.Response shortenUrlSearch(ShortenUrlDto.Get.Request param){
        if (!shortenUrls.containsKey(param.getKey())) {
            throw BusinessExceptionGenerator.createBusinessException("DB003");
        }

        return ShortenUrlDto.Get.Response.builder()
                .originUrl(shortenUrls.get(param.getKey()))
                .build();
    }

    public ShortenUrlDto.Create.Response shortenUrlCreate(ShortenUrlDto.Create.Request param){
        if (validateOriginUrl(param.getOriginUrl())) {
            throw BusinessExceptionGenerator.createBusinessException("DB001");
        }

        Random random = new Random();
        String returnKey = Stream.generate(() -> String.valueOf(random.nextInt(10000000)))
                .limit(1)
                .parallel()
                .map(key -> shortenUrls.computeIfAbsent(key, k -> param.getOriginUrl()))
                .filter(Objects::nonNull)
                .findAny()
                .orElse(null);

        if (!StringUtils.hasText(returnKey)) throw BusinessExceptionGenerator.createBusinessException("DB003");

        return ShortenUrlDto.Create.Response.builder()
                .key(returnKey)
                .build();
    }

    public static boolean isValidURL(String url) {
        Matcher matcher = URL_PATTERN.matcher(url);
        return matcher.matches();
    }

    private boolean validateOriginUrl(String originUrl) {
        return !StringUtils.hasText(originUrl) || !isValidURL(originUrl);
    }


}
