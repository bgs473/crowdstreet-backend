package com.crowdstreet.InterviewAPI.Request.repository;

import com.crowdstreet.InterviewAPI.Request.model.RequestDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository <RequestDao, Long> {


}
