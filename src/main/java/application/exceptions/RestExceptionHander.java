package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class RestExceptionHander {

    @ExceptionHandler(ExceptionCustom.class)
    public ResponseEntity<ExceptionFormat> objectNotFound(ExceptionCustom exception, HttpServletRequest request) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(ExceptionFormat.builder()
                        .code(exception.getCode())
                        .message(exception.getMessage())
                        .detail(exception.getDetail())
                        .build());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionFormat> methodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionFormat.builder()
                        .code(78)
                        .message("Os dados devem ser enviados de acordo com as regras, por favor tente novamente")
                        .detail(exception.getMessage() == null ? exception.toString() : exception.getMessage())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionFormat> exception(Exception exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionFormat.builder()
                        .code(null)
                        .message("Ocorreu um erro, por favor tente novamente")
                        .detail(exception.getMessage() == null ? exception.toString() : exception.getMessage())
                        .build());
    }

}
