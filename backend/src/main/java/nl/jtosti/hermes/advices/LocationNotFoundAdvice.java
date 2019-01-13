package nl.jtosti.hermes.advices;

import nl.jtosti.hermes.entities.dto.ErrorDTO;
import nl.jtosti.hermes.exceptions.LocationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LocationNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorDTO locationNotFoundHandler(LocationNotFoundException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}
