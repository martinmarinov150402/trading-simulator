package com.tradinsimulator.tradingsimulator.dto;

// RegistrationRequestDto.java
public record RegistrationRequestDto(
        String username,
        String email,
        String password
) {
}
