package nl.jtosti.hermes.user.advice;

import nl.jtosti.hermes.user.exception.EmailAlreadyUsedException;
import nl.jtosti.hermes.util.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmailAlreadyUsedAdvice {
    @ResponseBody
    @ExceptionHandler(EmailAlreadyUsedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorDTO emailAlreadyUsedHandler(EmailAlreadyUsedException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}
