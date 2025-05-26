package com.tradinsimulator.tradingsimulator.controllers;

import com.tradinsimulator.tradingsimulator.components.UserMapper;
import com.tradinsimulator.tradingsimulator.dto.UserProfileDto;
import com.tradinsimulator.tradingsimulator.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getUserProfile(
            final Authentication authentication) {

        final var user =
                userService.getUserByUsername(authentication.getName());

        return ResponseEntity.ok(userMapper.toUserProfileDto(user));
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetUser(final Authentication authentication) {
        userService.reset(authentication.getName());
        return ResponseEntity.ok("{\"reset\": \"true\"}");
    }
}

