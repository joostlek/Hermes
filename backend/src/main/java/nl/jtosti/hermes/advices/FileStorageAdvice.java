package nl.jtosti.hermes.advices;

import nl.jtosti.hermes.entities.dto.ErrorDTO;
import nl.jtosti.hermes.exceptions.FileStoreException;
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
