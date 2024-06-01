package com.solbeg.emailservice.exception;

import com.solbeg.emailservice.exception.model.IncorrectData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class MailExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<IncorrectData> exception(Exception exception) {
        return getResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static ResponseEntity<IncorrectData> getResponse(String message, HttpStatus status) {
        IncorrectData incorrectData = new IncorrectData(LocalDateTime.now(), message, status.value());
        return ResponseEntity.status(status).body(incorrectData);
    }
}
