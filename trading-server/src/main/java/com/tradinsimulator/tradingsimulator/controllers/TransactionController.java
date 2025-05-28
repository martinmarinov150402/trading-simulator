package com.tradinsimulator.tradingsimulator.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradinsimulator.tradingsimulator.dto.TransactionDto;
import com.tradinsimulator.tradingsimulator.entities.Transaction;
import com.tradinsimulator.tradingsimulator.entities.User;
import com.tradinsimulator.tradingsimulator.repositories.TransactionRepository;
import com.tradinsimulator.tradingsimulator.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    public final TransactionRepository transactionRepository;
    public final UserRepository userRepository;
    public final ObjectMapper objectMapper;

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> getAllTransactionsOfUser(final Authentication authentication) {
        try {
            User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
            System.out.println(transactionRepository.findByUserId(user.getId()));

            var transactions = transactionRepository.findByUserId(user.getId());
            return ResponseEntity.ok(transactions.stream().map(transaction -> new TransactionDto(transaction.getUserId(),
                                                                            transaction.getAmmount(),
                                                                            transaction.getMoney(),
                                                                            transaction.getCurrency(),
                                                                            transaction.getCreatedAt())).toList());
        }
        catch(NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }



    }
}
