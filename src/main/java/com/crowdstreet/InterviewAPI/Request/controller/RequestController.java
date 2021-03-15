package com.crowdstreet.InterviewAPI.Request.controller;

import com.crowdstreet.InterviewAPI.Request.model.Request;
import com.crowdstreet.InterviewAPI.Request.service.CallbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RequestController {

    @Autowired
    CallbackService callbackService;

    @PostMapping(path = "/request")
    public String createRequest(@RequestBody @Valid Request request) {
        return callbackService.generateRequestId(request);
    }
}
