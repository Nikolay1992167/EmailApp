package com.solbeg.emailservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbeg.emailservice.model.EmailRequest;
import com.solbeg.emailservice.service.impl.EmailServiceImpl;
import com.solbeg.emailservice.util.EmailRequestTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmailServiceImpl emailServiceImpl;

    private static final String EMAIL_INCORRECT = "mail.ru";

    @Test
    void shouldCheckSendUserDataSuccess() throws Exception {
        // given
        EmailRequest emailRequest = EmailRequestTestData.builder()
                .build()
                .getEmailRequest();
        String json = objectMapper.writeValueAsString(emailRequest);
        doNothing()
                .when(emailServiceImpl).sendEmailWithData(emailRequest);

        // when, then
        mockMvc.perform(post("/api/v1/send/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldThrowExceptionSendUserData() throws Exception {
        // given
        EmailRequest emailRequest = EmailRequestTestData.builder()
                .withToEmail(EMAIL_INCORRECT)
                .build()
                .getEmailRequest();
        String json = objectMapper.writeValueAsString(emailRequest);
        doNothing()
                .when(emailServiceImpl).sendEmailWithData(emailRequest);

        // when, then
        mockMvc.perform(post("/api/v1/send/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_message")
                        .value("{toEmail=must be a well-formed email address}"));
    }
}