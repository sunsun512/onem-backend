package community.whatever.onembackendjava.api.dto;


import lombok.Builder;
import lombok.Getter;

public class ShortenUrlDto {

    public static class Get{
        @Getter
        public static class Request{
            private String key;

        }

        @Getter
        @Builder
        public static class Response{
            public String originUrl;
        }
    }

    public static class Create{
        @Getter
        public static class Request{
            private String originUrl;

        }

        @Getter
        @Builder
        public static class Response{
            public String key;
        }
    }

}
