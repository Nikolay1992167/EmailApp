package com.solbeg.emailservice.service;

import com.solbeg.emailservice.model.EmailRequest;

public interface EmailService {

    void sendEmailWithData(EmailRequest request);
}
