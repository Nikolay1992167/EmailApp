package com.solbeg.emailservice.validation;

import com.solbeg.emailservice.model.EmailRequest;
import com.solbeg.emailservice.util.EmailRequestTestData;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.solbeg.emailservice.util.Constants.EXCHANGE_DEAD_LETTER;
import static com.solbeg.emailservice.util.Constants.ROUTING_KEY_DLQ;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailRequestValidatorTest {

    @InjectMocks
    private EmailRequestValidator emailRequestValidator;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testValidateRequestWithErrors() {
        // given
        EmailRequest request = EmailRequestTestData.getEmailRequestInvalid();

        // when, then
        assertThatThrownBy(() -> emailRequestValidator.validateRequest(request))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    public void testValidateRequestWithoutErrors() {
        // given
        EmailRequest request = EmailRequestTestData.getEmailRequest();

        // when
        emailRequestValidator.validateRequest(request);

        // then
        verify(rabbitTemplate, times(0))
                .convertAndSend(EXCHANGE_DEAD_LETTER, ROUTING_KEY_DLQ, request);
    }
}