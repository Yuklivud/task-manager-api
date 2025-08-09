package com.ms.todoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Schema(name = "AuthRequest", description = "DTO for user authentication")
public class AuthRequest {

    @Email
    @NotBlank
    @Schema(description = "User email address", example = "user@example.com", required = true)
    private String email;

    @NotBlank
    @Schema(description = "User password", example = "strongPassword123", required = true)
    private String password;

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthRequest{email='" + email + "', password='[PROTECTED]'}";
    }
}
