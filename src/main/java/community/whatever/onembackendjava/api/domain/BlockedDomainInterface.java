package community.whatever.onembackendjava.api.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface BlockedDomainInterface {
    void loadBlockedDomains(MultipartFile file);  // CSV 파일에서 차단 목록 로드
    Set<String> getBlockedDomains();              // 차단된 도메인 목록 반환
    boolean isBlocked(String url);                // 특정 URL이 차단된 도메인인지 확인
}
