package com.solbeg.emailservice.util;

import com.solbeg.emailservice.enums.EmailType;
import com.solbeg.emailservice.model.EmailRequest;

import java.util.HashMap;

import static com.solbeg.emailservice.util.Constants.USER_ADMIN;

public class EmailRequestTestData {

    public static EmailRequest getEmailRequest() {
        return EmailRequest.builder()
                .emailType(EmailType.USER_WELCOME_EMAIL)
                .toEmail(USER_ADMIN)
                .data(new HashMap<>() {{
                    put("firstName", "Nikolay");
                    put("lastName", "Minich");
                    put("activationLink", "exampleLink");
                }})
                .build();
    }

    public static EmailRequest getEmailRequestInvalid() {
        return EmailRequest.builder()
                .emailType(null)
                .toEmail("invalid_email")
                .data(null)
                .build();
    }
}