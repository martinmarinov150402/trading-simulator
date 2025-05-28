package com.tradinsimulator.tradingsimulator.dto;

import java.time.Instant;
import java.util.UUID;

public record TransactionDto(UUID userId, double ammount, double money, String currency, Instant createdAt) {
}
