package com.tradinsimulator.tradingsimulator.controllers;

import com.tradinsimulator.tradingsimulator.dto.AuthenticationRequestDto;
import com.tradinsimulator.tradingsimulator.dto.CurrencyTransactionDto;
import com.tradinsimulator.tradingsimulator.dto.UserCurrencyDto;
import com.tradinsimulator.tradingsimulator.entities.UserCurrency;
import com.tradinsimulator.tradingsimulator.services.UserCurrencyService;
import com.tradinsimulator.tradingsimulator.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyController {
    private final UserCurrencyService userCurrencyService;
    private final UserService userService;

    @PostMapping("/buy")
    public ResponseEntity<UserCurrency> buyCurrency(
            @RequestBody final CurrencyTransactionDto currencyTransactionDto, final Authentication authentication) {



        UserCurrencyDto data = new UserCurrencyDto(userService.getUserByUsername(authentication.getName()).getId(),
                                                   currencyTransactionDto.currency(),
                                                   currencyTransactionDto.ammount());

        return userCurrencyService.createBuyTransaction(data);

    }

    @PostMapping("/sell")
    public ResponseEntity<UserCurrency> sellCurrency(
            @RequestBody final CurrencyTransactionDto currencyTransactionDto, final Authentication authentication) {



        UserCurrencyDto data = new UserCurrencyDto(userService.getUserByUsername(authentication.getName()).getId(),
                currencyTransactionDto.currency(),
                currencyTransactionDto.ammount());

        return userCurrencyService.createSellTransaction(data);

    }
}
