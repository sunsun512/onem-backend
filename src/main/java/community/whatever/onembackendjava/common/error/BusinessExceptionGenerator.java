package community.whatever.onembackendjava.common.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class BusinessExceptionGenerator {

    private static RestControllerAdvice restControllerAdvice;

    public BusinessExceptionGenerator(RestControllerAdvice restControllerAdvice){
        this.restControllerAdvice = restControllerAdvice;
    }

    public static BusinessException createBusinessException(String message) {
        return new BusinessException(message);
    }

    public static BusinessException createBusinessException(String message, HttpStatus status) {

        return new BusinessException(message, status);
    }

    public static BusinessException createBusinessException(String message, Exception ex) {

        return new BusinessException(message, ((ex!=null) ? restControllerAdvice.extractExceptionDetails(ex) : null));
    }
}
