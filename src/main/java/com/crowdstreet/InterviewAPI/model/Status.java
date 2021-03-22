package com.crowdstreet.InterviewAPI.model;


public enum Status {
    STARTED("STARTED"),
    PROCESSED("PROCESSED"),
    COMPLETED("COMPLETED"),
    ERROR("ERROR");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getValue(){
        return this.status;
    }
}
