package com.crowdstreet.InterviewAPI.service;

import com.crowdstreet.InterviewAPI.exception.RequestException;
import com.crowdstreet.InterviewAPI.exception.ThirdPartyException;
import com.crowdstreet.InterviewAPI.model.Request;
import com.crowdstreet.InterviewAPI.model.RequestDao;
import com.crowdstreet.InterviewAPI.model.Status;
import com.crowdstreet.InterviewAPI.repository.ApiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

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
            requestDao.setCreated(Date.from(Instant.now()));
            repository.save(requestDao);
            log.info("Generated request ID: " + requestDao.getId());

            try {
                thirdPartyService.call(request.getBody(), requestDao.getId().toString());
            } catch (ThirdPartyException e) {
                repository.delete(requestDao);
                throw e;
            }
        } else {
            log.info("Request previously found.");
        }

        return requestDao.getId().toString();
    }

    public void postCallback(String id) {
        try {
            RequestDao requestDao = getCallback(id);
            requestDao.setStatus(Status.STARTED.getValue());
            thirdPartyService.call("STARTED", id);
            repository.save(requestDao);
        } catch (ThirdPartyException e) {
            log.error("Post callback to third party failed.");
            throw e;
        }
    }

    public RequestDao getCallback(String id) {
        Optional<RequestDao> optionalRequestDao = repository.findById(Long.parseLong(id));
        if(!optionalRequestDao.isPresent()){
            throw new RequestException("Unable to locate request.");
        }
        return optionalRequestDao.get();
    }
}
