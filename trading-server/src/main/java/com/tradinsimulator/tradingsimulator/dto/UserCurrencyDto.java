package com.tradinsimulator.tradingsimulator.dto;

import java.util.UUID;

public record UserCurrencyDto(int userId, String currency, Double ammount) {
}

