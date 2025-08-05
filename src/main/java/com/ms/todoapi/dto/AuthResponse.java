package com.ms.todoapi.dto;

public record AuthResponse(
        String token)
{
    @Override
    public String toString() {
        return "[PROTECTED]";
    }
}
