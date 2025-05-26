package com.tradinsimulator.tradingsimulator.controllers;

import com.tradinsimulator.tradingsimulator.dto.AuthenticationRequestDto;
import com.tradinsimulator.tradingsimulator.dto.AuthenticationResponseDto;
import com.tradinsimulator.tradingsimulator.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody final AuthenticationRequestDto authenticationRequestDto
    ) {
        return ResponseEntity.ok(
                authenticationService.authenticate(authenticationRequestDto));
    }
}

// AuthenticationService.java


