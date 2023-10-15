package com.cstore.exception;

public class WarehouseNotFoundException extends RuntimeException {
    public WarehouseNotFoundException() {
        super();
    }

    public WarehouseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public WarehouseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WarehouseNotFoundException(String message) {
        super(message);
    }

    public WarehouseNotFoundException(Throwable cause) {
        super(cause);
    }
}
