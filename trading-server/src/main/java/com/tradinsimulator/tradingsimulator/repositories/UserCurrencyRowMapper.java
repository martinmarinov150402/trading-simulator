package com.tradinsimulator.tradingsimulator.repositories;

import com.tradinsimulator.tradingsimulator.entities.UserCurrency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCurrencyRowMapper implements RowMapper<UserCurrency> {
    @Override
    public UserCurrency mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserCurrency userC = new UserCurrency();
        userC.setId(rs.getInt("id"));
        userC.setUserId(rs.getInt("user_id"));
        userC.setCurrency(rs.getString("currency"));
        userC.setammount(rs.getDouble("amount"));
        return userC;
    }
}
