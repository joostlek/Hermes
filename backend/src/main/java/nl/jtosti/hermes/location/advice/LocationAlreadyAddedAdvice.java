package nl.jtosti.hermes.location.advice;

import nl.jtosti.hermes.location.exception.LocationAlreadyAddedException;
import nl.jtosti.hermes.location.exception.LocationNotFoundException;
import nl.jtosti.hermes.util.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LocationAlreadyAddedAdvice {
    @ResponseBody
    @ExceptionHandler(LocationAlreadyAddedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorDTO locationAlreadyAddedHandler(LocationAlreadyAddedException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}
