package nl.jtosti.hermes.company.advice;

import nl.jtosti.hermes.company.exception.LocationHasImagesException;
import nl.jtosti.hermes.util.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LocationHasImagesAdvice {
    @ResponseBody
    @ExceptionHandler(LocationHasImagesException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorDTO locationHasImagesHandler(LocationHasImagesException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}

