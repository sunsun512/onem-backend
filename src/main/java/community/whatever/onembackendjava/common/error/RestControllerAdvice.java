package community.whatever.onembackendjava.common.error;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import community.whatever.onembackendjava.common.util.model.ResultJson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.sasl.AuthenticationException;
import java.util.HashMap;

@Slf4j
@ControllerAdvice
public class RestControllerAdvice {
private final ObjectMapper objectMapper = new ObjectMapper();

HashMap<String, Object> extractExceptionDetails(Exception ex) {
    try {
        String causeMessage = ex.getCause() != null ? ex.getCause().getLocalizedMessage() : null;
        HashMap<String, Object> read = new HashMap<>();
        if (causeMessage != null) {
            HashMap<String, Object> errorResponseData = objectMapper.readValue(causeMessage, new TypeReference<>() {});
            if(errorResponseData.containsKey("data")) {
                if(!errorResponseData.get("data").toString().isEmpty()) {
                    return objectMapper.convertValue(errorResponseData.get("data"), new TypeReference<>() {});
                }
            }
            return errorResponseData;

        }
    } catch (Exception e) {
        log.error("Error while extracting exception details", e);
    }
    return new HashMap<>();
}

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResultJson> BdExceptionHandler(BusinessException ex) {

        return ResponseEntity
                .status((ex.getHttpStatus() !=null) ? ex.getHttpStatus() : HttpStatus.OK)
                .body(ResultJson.builder()
                        .resultCode(ex.getErrorCode())
                        .msg(ex.getMessage())
                        .data(ex.getData())
                        .build());
    }

}
