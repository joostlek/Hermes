package nl.jtosti.hermes.screen.advice;

import nl.jtosti.hermes.screen.exception.ScreenIsNotToReceivePasswordException;
import nl.jtosti.hermes.screen.exception.ScreenNotFoundException;
import nl.jtosti.hermes.util.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ScreenIsNotToReceivePasswordAdvice {
    @ResponseBody
    @ExceptionHandler(ScreenNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ErrorDTO screenIsNotToReceivePasswordAdviceHandler(ScreenIsNotToReceivePasswordException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}
