package com.tradinsimulator.tradingsimulator.services;

import com.tradinsimulator.tradingsimulator.dto.RegistrationRequestDto;
import com.tradinsimulator.tradingsimulator.entities.User;
import com.tradinsimulator.tradingsimulator.exceptions.UserAlreadyExistsException;
import com.tradinsimulator.tradingsimulator.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(RegistrationRequestDto request) throws UserAlreadyExistsException {
        if (userRepository.existsByUsername(request.username()) ||
                userRepository.existsByEmail(request.email())) {

            throw new UserAlreadyExistsException();
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setBalance(10000.00);
        user.setPassHash(passwordEncoder.encode(request.password()));

        return userRepository.save(user);
    }
}

