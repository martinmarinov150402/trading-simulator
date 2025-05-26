package com.tradinsimulator.tradingsimulator.controllers;

import com.tradinsimulator.tradingsimulator.dto.RegistrationRequestDto;
import com.tradinsimulator.tradingsimulator.dto.RegistrationResponseDto;
import com.tradinsimulator.tradingsimulator.entities.User;
import com.tradinsimulator.tradingsimulator.exceptions.UserAlreadyExistsException;
import com.tradinsimulator.tradingsimulator.repositories.UserRepository;
import com.tradinsimulator.tradingsimulator.services.UserRegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;

    private final UserRegistrationMapper userRegistrationMapper;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> registerUser(
            @RequestBody final RegistrationRequestDto registrationDTO) throws UserAlreadyExistsException {

        final var registeredUser = userRegistrationService
                .registerUser(registrationDTO);

        return ResponseEntity.ok(
                userRegistrationMapper.toRegistrationResponseDto(registeredUser)
        );
    }

}

