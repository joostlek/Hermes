package nl.jtosti.hermes.screen.auth.advice;

import nl.jtosti.hermes.screen.auth.exception.ScreenPasswordExpiredException;
import nl.jtosti.hermes.util.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ScreenPasswordExpiredAdvice {
    @ResponseBody
    @ExceptionHandler(ScreenPasswordExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ErrorDTO screenPasswordExpiredAdvice(ScreenPasswordExpiredException ex) {
        return new ErrorDTO(ex.getMessage());
    }

}
