package ma.myrh.error.exception.entreprise;

import ma.myrh.error.exception.NotFoundException;

public class EntrepriseNotFoundException extends NotFoundException {

    public EntrepriseNotFoundException() {
    }

    public EntrepriseNotFoundException(String message) {
        super(message);
    }

    public EntrepriseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntrepriseNotFoundException(Throwable cause) {
        super(cause);
    }

    public EntrepriseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
