package nl.jtosti.hermes.location.advice;

import nl.jtosti.hermes.location.exception.LocationIsFromCompanyException;
import nl.jtosti.hermes.util.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LocationIsFromCompanyAdvice {
    @ResponseBody
    @ExceptionHandler(LocationIsFromCompanyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorDTO locationIsFromCompanyHandler(LocationIsFromCompanyException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}
