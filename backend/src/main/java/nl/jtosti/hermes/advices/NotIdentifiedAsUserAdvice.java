package nl.jtosti.hermes.advices;

import nl.jtosti.hermes.exceptions.NotIdentifiedAsUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotIdentifiedAsUserAdvice {
    @ResponseBody
    @ExceptionHandler(NotIdentifiedAsUserException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String notIdentifedAsUserHandler(NotIdentifiedAsUserException ex) {
        return ex.getMessage();
    }
}
