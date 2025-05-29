package com.tradinsimulator.tradingsimulator.dto;

import java.time.Instant;

public record TransactionDto(int userId, double ammount, double money, String currency, Instant createdAt) {
}
