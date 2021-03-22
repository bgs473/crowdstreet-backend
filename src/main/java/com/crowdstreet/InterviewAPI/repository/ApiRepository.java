package com.crowdstreet.InterviewAPI.repository;

import com.crowdstreet.InterviewAPI.model.RequestDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRepository extends JpaRepository<RequestDao, Long> {

    @Query("SELECT u FROM RequestDao u WHERE u.body = ?1")
    RequestDao findRequestByBody(String body);
}
