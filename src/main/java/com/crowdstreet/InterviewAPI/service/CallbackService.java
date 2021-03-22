package com.crowdstreet.InterviewAPI.service;

import com.crowdstreet.InterviewAPI.exception.ThirdPartyException;
import com.crowdstreet.InterviewAPI.model.Request;
import com.crowdstreet.InterviewAPI.model.RequestDao;
import com.crowdstreet.InterviewAPI.repository.ApiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CallbackService {

    @Autowired
    ApiRepository repository;

    @Autowired
    ThirdPartyService thirdPartyService;

    public String generateRequestId(Request request) {
        RequestDao requestDao = repository.findRequestByBody(request.getBody());

        if(requestDao == null) {
            requestDao = new RequestDao();
            requestDao.setBody(request.getBody());
            repository.save(requestDao);
            log.info("Generated request ID: " + requestDao.getId());

            try {
                thirdPartyService.call(request, requestDao.getId().toString());
            } catch (ThirdPartyException e) {
                repository.delete(requestDao);
                throw e;
            }
        } else {
            log.info("Request previously found.");
        }

        return requestDao.getId().toString();
    }

}
