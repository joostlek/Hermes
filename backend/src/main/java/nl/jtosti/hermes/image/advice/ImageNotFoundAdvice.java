package nl.jtosti.hermes.image.advice;

import nl.jtosti.hermes.image.exception.ImageNotFoundException;
import nl.jtosti.hermes.util.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ImageNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ImageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorDTO imageNotFoundHandler(ImageNotFoundException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}
