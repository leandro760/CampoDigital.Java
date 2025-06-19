package com.app.shared.application.exception;

import java.time.LocalDateTime;

public class HandlerException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    private final String message;
    private final String errorCode;
    private final LocalDateTime timestamp;

    public HandlerException(String message, String errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format(
            "HandlerException{message='%s', errorCode='%s', timestamp='%s'}",
            message, errorCode, timestamp
        );
    }
}