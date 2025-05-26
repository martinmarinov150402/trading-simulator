package com.tradinsimulator.tradingsimulator.controllers;

import com.tradinsimulator.tradingsimulator.dto.RegistrationRequestDto;
import com.tradinsimulator.tradingsimulator.dto.RegistrationResponseDto;
import com.tradinsimulator.tradingsimulator.entities.User;
import org.springframework.stereotype.Component;

// UserRegistrationMapper.java
@Component
public class UserRegistrationMapper {

    public User toEntity(RegistrationRequestDto registrationRequestDto) {
        final var user = new User();

        user.setEmail(registrationRequestDto.email());
        user.setUsername(registrationRequestDto.username());
        user.setBalance(10000.00);
        user.setPassHash(registrationRequestDto.password());

        return user;
    }

    public RegistrationResponseDto toRegistrationResponseDto(
            final User user) {

        return new RegistrationResponseDto(
                user.getEmail(), user.getUsername());
    }

}
