package com.app.shared.adapters;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.shared.application.exception.HandlerException;

public abstract class BaseController {

    private static final String INTERNAL_ERROR = "INTERNAL_ERROR";

    protected <T> ResponseEntity<T> handleRequest(Supplier<T> action) {
        try {
            return ResponseEntity.ok(action.get());
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e.getMessage(), INTERNAL_ERROR);
        }
    }

    protected ResponseEntity<Void> handleVoidRequest(Runnable action) {
        try {
            action.run();
            return ResponseEntity.ok().build();
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e.getMessage(), INTERNAL_ERROR);
        }
    }

    @ExceptionHandler(HandlerException.class)
    public ResponseEntity<ErrorResponse> handleHandlerException(HandlerException ex) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponse(ex.getErrorCode(), ex.getMessage(), ex.getTimestamp()));
    }

    record ErrorResponse(String errorCode, String message, LocalDateTime timestamp) {}
} 