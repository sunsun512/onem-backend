package community.whatever.onembackendjava.api.controller;

import community.whatever.onembackendjava.api.dto.ShortenUrlDto;
import community.whatever.onembackendjava.api.service.UrlShortenService;
import community.whatever.onembackendjava.common.util.ResponseFormatter;
import community.whatever.onembackendjava.common.util.model.ResultJson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
@RequiredArgsConstructor
public class UrlShortenController {
    private final UrlShortenService urlShortenService;

    @GetMapping("/shorten-url")
    public ResponseEntity<ResultJson> shortenUrlSearch(@Valid ShortenUrlDto.Get.Request param) {
        log.info("param >> {}", param);
        ShortenUrlDto.Get.Response result = urlShortenService.shortenUrlSearch(param);
        log.info("result >> {}", result);
        return ResponseFormatter.ConvertResponse(result);
    }

    @PostMapping("/shorten-url")
    public ResponseEntity<ResultJson> shortenUrlCreate(@RequestBody ShortenUrlDto.Create.Request param) {
        log.info("param >> {}", param);
        ShortenUrlDto.Create.Response result = urlShortenService.shortenUrlCreate(param);
        log.info("result >> {}", result);
        return ResponseFormatter.ConvertResponse(result);
    }
}
