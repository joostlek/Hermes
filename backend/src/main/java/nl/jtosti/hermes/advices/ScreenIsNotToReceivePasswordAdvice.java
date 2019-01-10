package nl.jtosti.hermes.advices;

import nl.jtosti.hermes.exceptions.ScreenIsNotToReceivePasswordException;
import nl.jtosti.hermes.exceptions.ScreenNotFoundException;
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
    String screenIsNotToReceivePasswordAdviceHandler(ScreenIsNotToReceivePasswordException ex) {
        return ex.getMessage();
    }
}
