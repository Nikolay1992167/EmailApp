package com.solbeg.emailservice.validation;

import com.solbeg.emailservice.enums.ErrorMessage;
import com.solbeg.emailservice.model.EmailRequest;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.SimpleErrors;

import static com.solbeg.emailservice.util.Constants.EXCHANGE_DEAD_LETTER;
import static com.solbeg.emailservice.util.Constants.ROUTING_KEY_DLQ;

@Slf4j
@RequiredArgsConstructor
@Component
public class EmailRequestValidator {
    private final RabbitTemplate rabbitTemplate;

    public void validateRequest(EmailRequest request) {
        Errors errors = new SimpleErrors(request);
        validateFields(request, errors);

        if (errors.hasErrors()) {
            rabbitTemplate.convertAndSend(EXCHANGE_DEAD_LETTER, ROUTING_KEY_DLQ, request);
            throw new ValidationException(String.valueOf(errors));
        }
        log.info("Validation is successful!");
    }

    private void validateFields(EmailRequest request, Errors errors) {
        if (request.getEmailType() == null) {
            errors.rejectValue("emailType", "emailType.null", ErrorMessage.ERROR_EMAIL_TYPE.getMessage());
        }

        try {
            InternetAddress emailAddr = new InternetAddress(request.getToEmail());
            emailAddr.validate();
        } catch (AddressException ex) {
            errors.rejectValue("toEmail", "toEmail.invalid", ErrorMessage.ERROR_EMAIL_FORMAT.getMessage());
        }

        if (request.getData() == null || request.getData().isEmpty()) {
            errors.rejectValue("data", "data.empty", ErrorMessage.ERROR_NOT_EMPTY.getMessage());
        }
    }
}
