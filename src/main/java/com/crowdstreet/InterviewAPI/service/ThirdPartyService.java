package com.crowdstreet.InterviewAPI.service;

import com.crowdstreet.InterviewAPI.exception.ThirdPartyException;
import com.crowdstreet.InterviewAPI.model.Request;
import com.crowdstreet.InterviewAPI.model.ThirdPartyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Slf4j
@Service
public class ThirdPartyService {

    private static final String url = "http://example.com/request";

    private RestTemplate restTemplate;

    public ThirdPartyService() {
        restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(10l))
                .build();
    }

    public void call(String body, String id) throws ThirdPartyException{
        ThirdPartyRequest thirdPartyRequest = generateRequest(body, id);
        sendRequest(thirdPartyRequest);
    }

    private ThirdPartyRequest generateRequest(String body, String id){
        ThirdPartyRequest thirdPartyRequest = new ThirdPartyRequest();
        thirdPartyRequest.setBody(body);
        thirdPartyRequest.setCallback("/callback/" + id);

        log.info("Generated ThirdPartyRequest with:" + thirdPartyRequest.toString());
        return thirdPartyRequest;
    }

    private void sendRequest(ThirdPartyRequest request) throws ThirdPartyException{
        log.debug("Sending request to third party with:" + request.toString());
        try {
//            ResponseEntity<String> responseEntity =
//                    restTemplate.postForEntity(url, request, String.class);

        } catch (RestClientException e) {
            log.debug("Request to third party failed with:" + request.toString());
            int statusCode = ((HttpClientErrorException) e).getStatusCode().value();
            String message = "The ThirdPartyRequest returned with the following status code: "
                    + statusCode;
            throw new ThirdPartyException(message, e);
        } catch (Exception e) {
            log.debug("Request to third party failed with: " + request.toString());
            String message = "The ThirdPartyRequest returned with the following error: "
                    + e.getLocalizedMessage();
            throw new ThirdPartyException(message, e);
        }
        log.info("Request to third party succeeded for: " + request.toString());
    }
}
