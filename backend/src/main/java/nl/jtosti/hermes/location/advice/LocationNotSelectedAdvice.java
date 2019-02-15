package nl.jtosti.hermes.location.advice;

import nl.jtosti.hermes.location.exception.LocationAlreadyAddedException;
import nl.jtosti.hermes.location.exception.LocationNotSelectedException;
import nl.jtosti.hermes.util.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LocationNotSelectedAdvice {
    @ResponseBody
    @ExceptionHandler(LocationNotSelectedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorDTO locationNotSelectedHandler(LocationNotSelectedException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}
