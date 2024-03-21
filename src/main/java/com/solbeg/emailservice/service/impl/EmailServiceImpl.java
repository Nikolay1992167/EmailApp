package com.solbeg.emailservice.service.impl;

import com.solbeg.emailservice.emailfactory.EmailGenerator;
import com.solbeg.emailservice.emailfactory.EmailGeneratorFactory;
import com.solbeg.emailservice.model.EmailRequest;
import com.solbeg.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    private final EmailGeneratorFactory emailGeneratorFactory;

    public void sendEmailWithData(EmailRequest request) {
        EmailGenerator generator = emailGeneratorFactory.getEmailGenerator(request.getEmailType());
        MimeMessagePreparator message = generator.generateEmail(request);
        emailSender.send(message);
    }
}