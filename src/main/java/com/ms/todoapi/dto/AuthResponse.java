package com.ms.todoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AuthResponse", description = "Response DTO returned after successful authentication")
public record AuthResponse(
        @Schema(description = "JWT token for authentication", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String token
)
{
    @Override
    public String toString() {
        return "[PROTECTED]";
    }
}
