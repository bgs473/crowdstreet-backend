package com.crowdstreet.InterviewAPI.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Request {

    @NotBlank(message = "The body must not be blank.")
    private String body;
}
