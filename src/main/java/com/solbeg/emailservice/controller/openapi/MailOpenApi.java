package com.solbeg.emailservice.controller.openapi;

import com.solbeg.emailservice.exception.model.IncorrectData;
import com.solbeg.emailservice.model.EmailRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "EmailSend", description = "Send Email Api")
public interface MailOpenApi {

    @Operation(
            method = "POST",
            tags = "EmailSend",
            description = "Send email with text messages in app.",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmailRequest.class),
                            examples = @ExampleObject("""
                                    {
                                        "emailType": "USER_WELCOME_EMAIL",
                                        "toEmail": "zhenya.volkov.95.95@mail.ru",
                                        "data": {
                                            "firstName": "Nikolay",
                                            "lastName": "Minich"
                                        }
                                    }
                                    """)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "The endpoint has been completed."),
                    @ApiResponse(responseCode = "400", description = "The endpoint has not been completed because an email is incorrect",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = IncorrectData.class), examples = @ExampleObject("""
                                            {
                                                "timestamp": "2024-03-21T16:18:23.0968686",
                                                "error_message": "{toEmail=должно иметь формат адреса электронной почты}",
                                                "error_status": 400
                                            }
                                    """)))
            }
    )
    ResponseEntity<String> sendUserData(EmailRequest emailRequest);
}
