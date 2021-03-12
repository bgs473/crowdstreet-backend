package com.crowdstreet.InterviewAPI.Request.controller;

import com.crowdstreet.InterviewAPI.InterviewApiApplication;
import com.crowdstreet.InterviewAPI.Request.model.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = InterviewApiApplication.class)
public class RequestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private String generateRequest(String str) throws Exception{
        Request request = new Request();
        request.setBody(str);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(request);

        return jsonString;
    }

    @Test
    public void testWithGoodRequest() throws Exception{
        MvcResult results = mvc.perform(MockMvcRequestBuilders.post("/request")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .content(generateRequest("hello")))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        assertEquals("hello", results.getResponse().getContentAsString());
    }

    @Test
    public void testWithEmptyRequest() throws Exception{
        MvcResult results = mvc.perform(MockMvcRequestBuilders.post("/request")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(generateRequest("")))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("hello", results.getResponse().getContentAsString());
    }
}
