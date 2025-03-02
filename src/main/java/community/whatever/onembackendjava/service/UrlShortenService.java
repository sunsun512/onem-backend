package community.whatever.onembackendjava.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service
public class UrlShortenService {

    private final Map<String, String> shortenUrls = new HashMap<>();

    public String shortenUrlSearch(String key){
        if (!shortenUrls.containsKey(key)) {
            throw new IllegalArgumentException("Invalid key");
        }
        return shortenUrls.get(key);
    }

    public String shortenUrlCreate(String originUrl){
        if (!StringUtils.hasText(originUrl)) {
            throw new IllegalArgumentException("Request URL No exists ");
        }
        String randomKey;
        while(true){
            randomKey = String.valueOf(new Random().nextInt(10000000));
            if (shortenUrls.putIfAbsent(randomKey, originUrl ) == null){
                break;
            }
        }
        return randomKey;
    }
}
