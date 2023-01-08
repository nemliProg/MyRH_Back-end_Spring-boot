package ma.myrh.error.exception.offer;

import ma.myrh.error.exception.NotFoundException;

public class OfferNotFoundException extends NotFoundException {
    public OfferNotFoundException() {
    }

    public OfferNotFoundException(String message) {
        super(message);
    }

    public OfferNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfferNotFoundException(Throwable cause) {
        super(cause);
    }

    public OfferNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
