package com.cstore.exceptions;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {
        super();
    }

    public NoSuchProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoSuchProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchProductException(String message) {
        super(message);
    }

    public NoSuchProductException(Throwable cause) {
        super(cause);
    }
}
