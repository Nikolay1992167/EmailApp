package com.solbeg.emailservice.util;

import com.solbeg.emailservice.enums.EmailType;
import com.solbeg.emailservice.model.EmailRequest;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.solbeg.emailservice.util.Constants.USER_ADMIN;

@Data
@Builder(setterPrefix = "with")
public class EmailRequestTestData {

    @Builder.Default
    private EmailType emailType = EmailType.USER_WELCOME_EMAIL;

    @Builder.Default
    private String toEmail = USER_ADMIN;

    @Builder.Default
    private Map<String, String> data = new HashMap<>() {{
        put("firstName", "Nikolay");
        put("lastName", "Minich");
        put("activationLink", "exampleLink");
    }};

    public EmailRequest getEmailRequest() {
        return EmailRequest.builder()
                .emailType(emailType)
                .toEmail(toEmail)
                .data(data)
                .build();
    }
}