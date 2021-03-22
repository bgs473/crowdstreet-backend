package com.crowdstreet.InterviewAPI.enums;


public enum StatusEnum {
    STARTED("STARTED"),
    PROCESSED("PROCESSED"),
    COMPLETED("COMPLETED"),
    ERROR("ERROR");

    private String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getValue(){
        return this.status;
    }
}
