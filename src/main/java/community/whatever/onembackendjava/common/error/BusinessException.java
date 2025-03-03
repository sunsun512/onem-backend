package community.whatever.onembackendjava.common.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;
    private HashMap<String, Object> data;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        this.message = message;
    }


    public BusinessException( String message, HashMap<String, Object> data) {
        this.message = message;
        this.data = data;
    }

    public BusinessException( String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }


}
