package nl.jtosti.hermes.screen.advice;

import nl.jtosti.hermes.screen.exception.ScreenNotFoundException;
import nl.jtosti.hermes.util.ErrorDTO;
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
    ErrorDTO screenNotFoundHandler(ScreenNotFoundException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}
