package com.solbeg.emailservice.emailfactory;

import com.solbeg.emailservice.model.EmailRequest;
import org.springframework.mail.javamail.MimeMessagePreparator;

public interface EmailGenerator {

    MimeMessagePreparator generateEmail(EmailRequest request);
}
