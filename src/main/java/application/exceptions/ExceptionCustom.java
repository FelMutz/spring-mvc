package application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ExceptionCustom extends RuntimeException {

    private Integer code;
    private String detail;
    private String message;
    private Throwable throwable;
    private HttpStatus httpStatus;

}
