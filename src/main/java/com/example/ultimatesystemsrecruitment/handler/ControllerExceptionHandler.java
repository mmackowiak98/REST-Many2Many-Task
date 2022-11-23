package com.example.ultimatesystemsrecruitment.handler;

import com.example.ultimatesystemsrecruitment.exception.EntityNotFoundException;
import com.example.ultimatesystemsrecruitment.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, javax.persistence.EntityNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleNotFoundException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("No such item found in repository"));
    }
}
