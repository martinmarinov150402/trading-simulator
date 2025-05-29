package com.tradinsimulator.tradingsimulator.repositories;

import com.tradinsimulator.tradingsimulator.entities.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.OffsetDateTime;

public class TransactionRowMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getInt("id"));
        transaction.setMoney(rs.getDouble("money"));
        transaction.setUserId(rs.getInt("user_id"));
        transaction.setAmmount(rs.getDouble("amount"));
        transaction.setCurrency(rs.getString("currency"));
        OffsetDateTime odt = rs.getObject("created_at", OffsetDateTime.class);
        transaction.setCreatedAt(odt.toInstant());
        return transaction;
    }
}
