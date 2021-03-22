package com.crowdstreet.InterviewAPI.exception;

public class ThirdPartyException extends RuntimeException{

    public ThirdPartyException(String message, Exception e) {
        super(message, e);
    }
}
