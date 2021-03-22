package com.crowdstreet.InterviewAPI.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CallbackController {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/callback/{id}")
    public void postCallback(@PathVariable String id, @RequestBody String body){

    }
}
