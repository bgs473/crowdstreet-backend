package com.crowdstreet.InterviewAPI.Request.service;

import com.crowdstreet.InterviewAPI.Request.model.Request;
import com.crowdstreet.InterviewAPI.Request.model.RequestDao;
import com.crowdstreet.InterviewAPI.Request.repository.ApiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CallbackService {

    @Autowired
    ApiRepository repository;

    public String generateRequestId(Request request) {
        RequestDao requestDao = new RequestDao();
        requestDao.setBody(request.getBody());
        repository.save(requestDao);
        return requestDao.getId().toString();
    }

}
