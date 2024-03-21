package com.solbeg.emailservice.model;

import com.solbeg.emailservice.enums.EmailType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    @NotNull
    private EmailType emailType;

    @Email
    private String toEmail;

    @NotEmpty
    private Map<String, String> data;

    public Map<String, String> getData() {
        if (data == null) {
            data = new HashMap<>();
        }
        return data;
    }
}