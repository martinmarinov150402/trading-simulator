package com.tradinsimulator.tradingsimulator.repositories;

import com.tradinsimulator.tradingsimulator.entities.UserCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserCurrencyRepository extends JpaRepository<UserCurrency, Integer> {
    Optional<List<UserCurrency>> findByUserId(UUID userId);
    Optional<UserCurrency> findByUserIdAndCurrency(UUID userId, String currency);
}
