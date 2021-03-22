package com.crowdstreet.InterviewAPI;

import com.crowdstreet.InterviewAPI.exception.RequestException;
import com.crowdstreet.InterviewAPI.exception.ThirdPartyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleConstraintViolation(DataIntegrityViolationException e) {
        log.error("This request has already been processed.", e);
    }

    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ThirdPartyException.class)
    public void handleThirdPartyException(ThirdPartyException e) {
        log.error("There was an error communicating with example.com.", e);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(RequestException.class)
    public void handleRequestException(RequestException e) {
        log.error("Unable to find request.");
    }
}
