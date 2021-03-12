package com.crowdstreet.InterviewAPI.Request.controller;

import com.crowdstreet.InterviewAPI.Request.model.Request;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    @PostMapping(path = "/request")
    public String createRequest(@RequestBody Request request) {
        return "hello";
    }
}
