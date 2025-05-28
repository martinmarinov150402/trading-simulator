package com.tradinsimulator.tradingsimulator.services;

import com.tradinsimulator.tradingsimulator.WebSocketHandler;
import com.tradinsimulator.tradingsimulator.dto.UserCurrencyDto;
import com.tradinsimulator.tradingsimulator.entities.Transaction;
import com.tradinsimulator.tradingsimulator.entities.User;
import com.tradinsimulator.tradingsimulator.entities.UserCurrency;
import com.tradinsimulator.tradingsimulator.repositories.TransactionRepository;
import com.tradinsimulator.tradingsimulator.repositories.UserCurrencyRepository;
import com.tradinsimulator.tradingsimulator.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.GONE;

@Service
@RequiredArgsConstructor
public class UserCurrencyService {
    private final UserCurrencyRepository userCurrencyRepository;
    private final UserRepository userRepository;
    private final WebSocketHandler webSocketHandler;
    private final TransactionRepository transactionRepository;

    @Transactional
    public ResponseEntity<UserCurrency> createBuyTransaction(UserCurrencyDto transaction) {
        User user = userRepository.findById(transaction.userId()).orElseThrow(() -> new ResponseStatusException(GONE,
                "This user doesn't exist"));
        Optional<UserCurrency> data = userCurrencyRepository.findByUserIdAndCurrency(transaction.userId(), transaction.currency());

        if(user.getBalance() < transaction.ammount() * webSocketHandler.getData(transaction.currency()).ask()) {
            return ResponseEntity.badRequest().build();
        }

        user.setBalance(user.getBalance() - transaction.ammount() * webSocketHandler.getData(transaction.currency()).ask());

        System.out.println("Buy Transaction");
        System.out.println(transaction.ammount());
        System.out.println(webSocketHandler.getData(transaction.currency()).bid());

        Transaction t = new Transaction();
        t.setCurrency(transaction.currency());
        t.setAmmount(transaction.ammount());
        t.setUserId(transaction.userId());
        t.setMoney(-transaction.ammount() * webSocketHandler.getData(transaction.currency()).ask());

        transactionRepository.save(t);

        userRepository.save(user);

        if (data.isPresent()) {
            UserCurrency record = data.get();
            record.setammount(record.getammount() + transaction.ammount());
            userCurrencyRepository.save(record);

            return ResponseEntity.ok(record);
        }
        else {
            UserCurrency record = new UserCurrency();
            record.setUserId(transaction.userId());
            record.setCurrency(transaction.currency());
            record.setammount(transaction.ammount());
            userCurrencyRepository.save(record);
            return ResponseEntity.ok(record);
        }

    }

    @Transactional
    public ResponseEntity<UserCurrency> createSellTransaction(UserCurrencyDto transaction) {
        User user = userRepository.findById(transaction.userId()).orElseThrow(() -> new ResponseStatusException(GONE,
                "This user doesn't exist"));
        Optional<UserCurrency> data = userCurrencyRepository.findByUserIdAndCurrency(transaction.userId(), transaction.currency());



        if (data.isPresent()) {

            UserCurrency record = data.get();
            if(record.getammount() < transaction.ammount()) {
                return ResponseEntity.badRequest().build();
            }
            record.setammount(record.getammount() - transaction.ammount());
            System.out.println("Sell Transaction");
            System.out.println(transaction.ammount());
            System.out.println(webSocketHandler.getData(transaction.currency()).bid());
            user.setBalance(user.getBalance() + transaction.ammount() * webSocketHandler.getData(transaction.currency()).bid());
            Transaction t = new Transaction();
            t.setCurrency(transaction.currency());
            t.setAmmount(transaction.ammount());
            t.setUserId(transaction.userId());
            t.setMoney(transaction.ammount() * webSocketHandler.getData(transaction.currency()).bid());

            transactionRepository.save(t);

            userRepository.save(user);
            userCurrencyRepository.save(record);
            return ResponseEntity.ok(record);
        }
        else {
            return ResponseEntity.badRequest().build();
        }

    }
}
