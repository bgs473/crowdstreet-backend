package com.crowdstreet.InterviewAPI.Request.controller;

import com.crowdstreet.InterviewAPI.Request.model.Request;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RequestController {

    @PostMapping(path = "/request")
    public String createRequest(@RequestBody @Valid Request request) {
        //Validation of string in request
        //Service to track request, generate ID, and save to DB
        //return ID
        return "hello";
    }
}
