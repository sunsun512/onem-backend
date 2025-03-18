package community.whatever.onembackendjava.api.dto;


import lombok.*;

public class ShortenUrlDto {

    public static class Get{
        @Data
        @NoArgsConstructor
        public static class Request{
            private String key;

        }

        @Data
        @Builder
        public static class Response{
            public String originUrl;
        }
    }

    public static class Create{
        @Data
        @NoArgsConstructor
        public static class Request{
            private String originUrl;
            private long ttlInSeconds = 60;   // 기본값 1분

        }

        @Data
        @Builder
        public static class Response{
            public String key;
        }
    }

}
