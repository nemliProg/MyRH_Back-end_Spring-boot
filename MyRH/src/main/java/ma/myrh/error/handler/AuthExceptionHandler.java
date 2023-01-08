package ma.myrh.error.handler;

import ma.myrh.dto.message.ErrorMessage;
import ma.myrh.error.exception.LoginFailedException;
import ma.myrh.error.exception.entreprise.EntrepriseRegisterFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus
public class AuthExceptionHandler {

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ErrorMessage> loginFailed(LoginFailedException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorMessage(HttpStatus.FORBIDDEN, exception.getMessage()));
    }

    @ExceptionHandler(EntrepriseRegisterFailedException.class)
    public ResponseEntity<ErrorMessage> entrepriseRegisterFailed(EntrepriseRegisterFailedException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> argumentNotValid(MethodArgumentNotValidException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(HttpStatus.BAD_REQUEST, "Invalid credentials, please check your inputs."));
    }

}
