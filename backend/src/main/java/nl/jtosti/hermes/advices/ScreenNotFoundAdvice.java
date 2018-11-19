package nl.jtosti.hermes.advices;

import nl.jtosti.hermes.exceptions.ScreenNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ScreenNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ScreenNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String screenNotFoundHandler(ScreenNotFoundException ex) {
        return ex.getMessage();
    }
}