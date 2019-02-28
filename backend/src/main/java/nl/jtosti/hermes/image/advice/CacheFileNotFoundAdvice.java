package nl.jtosti.hermes.image.advice;

import nl.jtosti.hermes.image.exception.CacheFileNotFoundException;
import nl.jtosti.hermes.util.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CacheFileNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CacheFileNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorDTO cacheFileNotFoundHandler(CacheFileNotFoundException ex) {
        return new ErrorDTO(ex.getMessage());
    }

}
