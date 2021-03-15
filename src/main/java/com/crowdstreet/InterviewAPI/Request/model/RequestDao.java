package com.crowdstreet.InterviewAPI.Request.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Table(name = "requests")
@Entity
public class RequestDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "body", unique = true)
    private String body;
    private Date created;
    private Date updated;
}
