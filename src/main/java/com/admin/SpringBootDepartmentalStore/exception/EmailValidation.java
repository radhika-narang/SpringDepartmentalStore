package com.admin.SpringBootDepartmentalStore.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmailValidation {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalidEmail(IllegalArgumentException e) {
        return ResponseEntity.ok(e.getMessage());
    }

}
