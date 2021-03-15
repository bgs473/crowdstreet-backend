package com.crowdstreet.InterviewAPI.Request.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class RequestDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String body;

    private Date created;
    private Date updated;
}
