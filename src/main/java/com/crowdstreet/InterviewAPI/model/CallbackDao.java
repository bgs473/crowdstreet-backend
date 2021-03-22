package com.crowdstreet.InterviewAPI.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "Callback")
public class CallbackDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String status;
    private String detail;
    private Date created;
    private Date updated;
}
