package com.crowdstreet.InterviewAPI.controller;

import com.crowdstreet.InterviewAPI.model.RequestDao;
import com.crowdstreet.InterviewAPI.service.CallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CallbackController {

    @Autowired
    CallbackService callbackService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/callback/{id}")
    public void postCallback(@PathVariable String id, @RequestBody String body){
        log.info("Received Post Callback with id: " + id + " body:" + body );
        callbackService.postCallback(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @GetMapping(path = "/callback/{id}")
    public RequestDao getCallback(@PathVariable String id){
        log.info("Received Get Callback with id: " + id);
        return callbackService.getCallback(id);
    }
}
