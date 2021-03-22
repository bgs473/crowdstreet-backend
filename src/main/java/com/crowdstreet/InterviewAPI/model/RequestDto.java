package com.crowdstreet.InterviewAPI.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Request")
public class RequestDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String body;

    private String status;
    private String detail;
    private Date created;
    private Date updated;
}
