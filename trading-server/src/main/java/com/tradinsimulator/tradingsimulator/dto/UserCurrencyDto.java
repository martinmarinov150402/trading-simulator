package com.tradinsimulator.tradingsimulator.dto;

import java.util.UUID;

public record UserCurrencyDto(UUID userId, String currency, Double ammount) {
}

