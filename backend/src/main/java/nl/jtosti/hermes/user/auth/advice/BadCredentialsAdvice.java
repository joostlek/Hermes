package nl.jtosti.hermes.user.auth.advice;

import nl.jtosti.hermes.util.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BadCredentialsAdvice {
    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ErrorDTO badCredentialsHandler(BadCredentialsException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}
