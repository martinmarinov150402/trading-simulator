package com.tradinsimulator.tradingsimulator.repositories;

import com.tradinsimulator.tradingsimulator.entities.Transaction;
import com.tradinsimulator.tradingsimulator.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Repository

public class TransactionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Transaction> findByUserId(int userId) {
        return jdbcTemplate.query("SELECT * from transactions WHERE user_id = ?" , new TransactionRowMapper(), userId);
    }

    public void save(Transaction t) {
        String sql = "INSERT INTO transactions (\"user_id\", \"currency\", \"amount\", \"money\", \"created_at\") VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, t.getUserId(), t.getCurrency(), t.getAmmount(), t.getMoney(), new Date());
    }

    public void deleteAllInBatch(List<Transaction> toDelete) {
        String sql = "DELETE FROM users WHERE id = ?";

        jdbcTemplate.batchUpdate(sql, toDelete, toDelete.size(), (ps, t) -> {
            ps.setLong(1, t.getId());
        });
    }
}
