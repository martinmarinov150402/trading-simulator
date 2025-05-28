package com.tradinsimulator.tradingsimulator.services;

import com.tradinsimulator.tradingsimulator.entities.Transaction;
import com.tradinsimulator.tradingsimulator.entities.User;
import com.tradinsimulator.tradingsimulator.repositories.TransactionRepository;
import com.tradinsimulator.tradingsimulator.repositories.UserCurrencyRepository;
import com.tradinsimulator.tradingsimulator.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.GONE;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserCurrencyRepository userCurrencyRepository;
    private final TransactionRepository transactionRepository;

    public User getUserByUsername(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(GONE,
                        "This user doesn't exist"));
    }

    public void reset(final String username) {
        User data = userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(GONE, "This user doesn't exist"));
        data.setBalance(10000.0);

        userRepository.save(data);

        userCurrencyRepository.deleteAllInBatch(userCurrencyRepository.findByUserId(data.getId()).orElse(new ArrayList<>()));
        transactionRepository.deleteAllInBatch(transactionRepository.findByUserId(data.getId()));

    }
}