package com.crowdstreet.InterviewAPI.controller;

import com.crowdstreet.InterviewAPI.model.Request;
import com.crowdstreet.InterviewAPI.service.CallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class RequestController {

    @Autowired
    CallbackService callbackService;

    @PostMapping(path = "/request")
    public String createRequest(@RequestBody @Valid Request request) {
        log.info("Create request received: " + request.toString());
        return callbackService.generateRequestId(request);
    }
}
