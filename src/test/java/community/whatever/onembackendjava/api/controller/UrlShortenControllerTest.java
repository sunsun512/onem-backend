package community.whatever.onembackendjava.api.controller;

import community.whatever.onembackendjava.setting.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UrlShortenControllerTest  extends RestDocsTestSupport {

    @Test
    void shortenUrlSearch() throws Exception{

        ResultActions result = this.mockMvc.perform(
                get("/shorten-url")
                        .contentType(contentType)
                        .accept(contentType)
                        .queryParam("key", "111111")
        );

        result.andExpect(status().isOk())
                .andDo(print())
                .andDo(restDocs.document(
                                queryParameters(
                                        parameterWithName("key").description("short url key")
                                ),
                                responseFields(
                                        fieldWithPath("resultCode").type(JsonFieldType.STRING).description("결과 코드"),
                                        fieldWithPath("msg").type(JsonFieldType.STRING).description("결과 메시지"),
                                        fieldWithPath("timeZone").type(JsonFieldType.STRING).description("서버 타임존"),
                                        fieldWithPath("timeStamp").type(JsonFieldType.NUMBER).description("서버 응답시간"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 객체").optional(),
                                        fieldWithPath("data.originUrl").type(JsonFieldType.STRING).description("원본 url").optional()
                                )
                        )
                );
    }

    @Test
    void shortenUrlCreate() throws Exception {
        HashMap<String , String> req = new HashMap<>();
        req.put("originUrl", "https://www.google.com");
//        req.put("originUrl", " ");

        ResultActions result = this.mockMvc.perform(
                post("/shorten-url")
                        .contentType(contentType)
                        .accept(contentType)
                        .content(objectMapper.writeValueAsString(req))
        );

        result.andExpect(status().isOk())
                .andDo(print())
                .andDo(restDocs.document(
                                requestFields(
                                        fieldWithPath("originUrl").type(JsonFieldType.STRING).description("요청 url")
                                )
//                                responseFields(
////                                        fieldWithPath("resultCode").type(JsonFieldType.STRING).description("결과 코드"),
////                                        fieldWithPath("msg").type(JsonFieldType.STRING).description("결과 메시지"),
////                                        fieldWithPath("timeZone").type(JsonFieldType.STRING).description("서버 타임존"),
////                                        fieldWithPath("timeStamp").type(JsonFieldType.NUMBER).description("서버 응답시간"),
////                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 객체").optional(),
////                                        fieldWithPath("data.key").type(JsonFieldType.STRING).description("생성된 키").optional()
//                                        fieldWithPath("").type(JsonFieldType.NUMBER).description("생성된 키").optional()
//                                )
                        )
                );
    }
}