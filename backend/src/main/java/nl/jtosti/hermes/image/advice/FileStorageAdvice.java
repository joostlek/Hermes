package nl.jtosti.hermes.image.advice;

import nl.jtosti.hermes.image.exception.FileStoreException;
import nl.jtosti.hermes.util.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FileStorageAdvice {
    @ResponseBody
    @ExceptionHandler(FileStoreException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorDTO fileStorageHandler(FileStoreException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}
