package com.cstore.exceptions;

public class NoSuchVariantException extends RuntimeException {
    public NoSuchVariantException() {
        super();
    }

    public NoSuchVariantException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoSuchVariantException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchVariantException(String message) {
        super(message);
    }

    public NoSuchVariantException(Throwable cause) {
        super(cause);
    }
}
