package com.anuworks.kafkademo.controller;

import com.anuworks.kafkademo.service.KafkaProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class KafkaProducerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    KafkaProducerService kafkaProducerService;

    @Test
    void testSendMessage() throws Exception {
        String message = "Hello World";
        mockMvc.perform(post("/api/v1/kafka/send")
                        .param("message", message))
                .andExpect(status().isOk());
    }
}