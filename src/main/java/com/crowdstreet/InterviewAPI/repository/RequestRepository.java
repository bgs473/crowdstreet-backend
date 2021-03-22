package com.crowdstreet.InterviewAPI.repository;

import com.crowdstreet.InterviewAPI.model.RequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<RequestDto, Long> {

    @Query("SELECT r FROM RequestDto r WHERE r.body = ?1")
    RequestDto findRequestByBody(String body);
}
