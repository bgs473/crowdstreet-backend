package com.crowdstreet.InterviewAPI.service;

import com.crowdstreet.InterviewAPI.enums.StatusEnum;
import com.crowdstreet.InterviewAPI.exception.RequestException;
import com.crowdstreet.InterviewAPI.exception.ThirdPartyException;
import com.crowdstreet.InterviewAPI.model.CallbackPutRequest;
import com.crowdstreet.InterviewAPI.model.Request;
import com.crowdstreet.InterviewAPI.model.RequestDto;
import com.crowdstreet.InterviewAPI.repository.RequestRepository;
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
    RequestRepository repository;

    @Autowired
    ThirdPartyService thirdPartyService;

    public String generateRequestId(Request request) {
        RequestDto requestDto = repository.findRequestByBody(request.getBody());

        if(requestDto == null) {
            requestDto = new RequestDto();
            requestDto.setBody(request.getBody());
            requestDto.setCreated(Date.from(Instant.now()));
            repository.save(requestDto);
            log.info("Generated request ID: " + requestDto.getId());

            try {
                thirdPartyService.call(request.getBody(), requestDto.getId().toString());
            } catch (ThirdPartyException e) {
                repository.delete(requestDto);
                throw e;
            }
        } else {
            log.info("Request previously found.");
        }

        return requestDto.getId().toString();
    }

    public void postCallback(String id) {
        try {
            RequestDto requestDto = getCallback(id);
            updateStatus(requestDto, StatusEnum.STARTED.getValue());
            updateUpdateTime(requestDto);
            requestDto.setUpdated(Date.from(Instant.now()));
            thirdPartyService.call("STARTED", id);
            repository.save(requestDto);
        } catch (ThirdPartyException e) {
            log.error("Post callback to third party failed.");
            throw e;
        }
    }

    public RequestDto getCallback(String id) {
        Optional<RequestDto> optionalRequestDao = repository.findById(Long.parseLong(id));
        if(!optionalRequestDao.isPresent()){
            throw new RequestException("Unable to locate request.");
        }
        log.debug("Request found: " + optionalRequestDao.get().toString());
        return optionalRequestDao.get();
    }

    public void putCallback(String id, CallbackPutRequest request) {
        RequestDto requestDto = getCallback(id);
        updateDetail(requestDto, request.getDetail());
        updateStatus(requestDto, request.getStatus());
        updateUpdateTime(requestDto);
        repository.save(requestDto);
    }

    private void updateDetail(RequestDto request, String detail) {
        request.setDetail(detail);
        log.debug("Setting details to: " + request.getDetail());
    }

    private void updateStatus(RequestDto request, String status) {
        StatusEnum statusEnum = StatusEnum.valueOf(status.toUpperCase().trim());
        request.setStatus(statusEnum.getValue());
        log.debug("Setting status to: " + statusEnum.getValue());
    }

    private void updateUpdateTime(RequestDto request) {
        Date now = Date.from(Instant.now());
        request.setUpdated(now);
        log.debug("Setting updated datetime to: " + now.toString());
    }
}
