package com.tradinsimulator.tradingsimulator.repositories;

import com.tradinsimulator.tradingsimulator.entities.UserCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class UserCurrencyRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserCurrency> findByUserId(int userId) {
        String sql = "SELECT * FROM user_currency where user_id = ?" ;

        return jdbcTemplate.query(sql, new UserCurrencyRowMapper(), userId);
    }
    public Optional<UserCurrency> findByUserIdAndCurrency(int userId, String currency) {
        String sqlT = "SELECT * FROM user_currency where user_id = ? AND currency = ?" ;

        Optional<UserCurrency> result = Optional.empty();

        List<UserCurrency> queryResult = jdbcTemplate.query(sqlT,  new UserCurrencyRowMapper(), userId, currency);
        if(!queryResult.isEmpty()) {
            result = Optional.of(queryResult.getFirst());
        }
        return result;
    }

    public void save(UserCurrency uc) {
        String sql = "";
        if(findByUserIdAndCurrency(uc.getUserId(), uc.getCurrency()).isPresent()) {
            String template = "UPDATE user_currency SET user_id = ?, currency = ?, amount = ?, WHERE id = ?";
            jdbcTemplate.update(sql, uc.getUserId(), uc.getCurrency(), uc.getammount(), uc.getId());

        }
        else {
            String template = "INSERT INTO user_currency (\"user_id\", \"currency\", \"amount\", \"created_at\") VALUES (?, ?, ?, ?)" ;
            jdbcTemplate.update(template, uc.getUserId(), uc.getCurrency(), uc.getammount(), new Date());
        }

    }

    public void deleteAllInBatch(List<UserCurrency> toDelete) {
        String sql = "DELETE FROM users WHERE id = ?";

        jdbcTemplate.batchUpdate(sql, toDelete, toDelete.size(), (ps, uc) -> {
            ps.setLong(1, uc.getId());
        });

    }
}
