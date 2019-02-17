package nl.jtosti.hermes.company.advice;

import nl.jtosti.hermes.company.exception.CompanyHasImagesException;
import nl.jtosti.hermes.company.exception.CompanyNotFoundException;
import nl.jtosti.hermes.util.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CompanyHasImagesAdvice {
    @ResponseBody
    @ExceptionHandler(CompanyHasImagesException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorDTO companyHasImagesHandler(CompanyHasImagesException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}

