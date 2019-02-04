package nl.jtosti.hermes.user.advice;

import nl.jtosti.hermes.user.exception.UserNotFoundException;
import nl.jtosti.hermes.util.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorDTO userNotFoundHandler(UserNotFoundException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}
