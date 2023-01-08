package ma.myrh.dto.message;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorMessage extends MessageDto {

    HttpStatus status;

    public ErrorMessage(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
