package com.crowdstreet.InterviewAPI;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolation(ConstraintViolationException e) {

    }
}
