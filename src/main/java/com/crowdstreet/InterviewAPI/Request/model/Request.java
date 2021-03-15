package com.crowdstreet.InterviewAPI.Request.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Request {

    @NotBlank
    private String body;
}
