package com.solbeg.emailservice.listener;

import com.solbeg.emailservice.emailfactory.EmailGenerator;
import com.solbeg.emailservice.emailfactory.EmailGeneratorFactory;
import com.solbeg.emailservice.model.EmailRequest;
import com.solbeg.emailservice.validation.EmailRequestValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static com.solbeg.emailservice.util.Constants.ACTIVATION_QUEUE_NAME;
import static com.solbeg.emailservice.util.Constants.INFORMATION_QUEUE_NAME;

@Service
@EnableRabbit
@Slf4j
@RequiredArgsConstructor
public class EventListener {
    private final EmailRequestValidator emailRequestValidator;

    private final JavaMailSender emailSender;

    private final EmailGeneratorFactory emailGeneratorFactory;

    @RabbitListener(queues = ACTIVATION_QUEUE_NAME)
    public void processMyQueueActivation(@Valid @Payload EmailRequest request) {
        emailRequestValidator.validateRequest(request);

        EmailGenerator generator = emailGeneratorFactory.getEmailGenerator(request.getEmailType());

        MimeMessagePreparator message = generator.generateEmail(request);
        emailSender.send(message);
    }

    @RabbitListener(queues = INFORMATION_QUEUE_NAME)
    public void processMyQueueInformation(@Valid @Payload EmailRequest request) {
        emailRequestValidator.validateRequest(request);

        EmailGenerator generator = emailGeneratorFactory.getEmailGenerator(request.getEmailType());

        MimeMessagePreparator message = generator.generateEmail(request);
        emailSender.send(message);
    }
}