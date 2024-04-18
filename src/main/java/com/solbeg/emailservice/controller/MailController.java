package com.solbeg.emailservice.controller;

import com.solbeg.emailservice.controller.openapi.MailOpenApi;
import com.solbeg.emailservice.model.EmailRequest;
import com.solbeg.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/send")
@RequiredArgsConstructor
public class MailController implements MailOpenApi {
    private final EmailService emailService;

    @Override
    @PostMapping("/email")
    public void sendUserData(@Validated @RequestBody EmailRequest emailRequest) {
        emailService.sendEmailWithData(emailRequest);
    }
}