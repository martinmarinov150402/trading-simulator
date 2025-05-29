package com.tradinsimulator.tradingsimulator.repositories;

import com.tradinsimulator.tradingsimulator.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users where username = ?" ;
        Optional<User> result = Optional.empty();


        List<User> l = jdbcTemplate.query(sql, new UserRowMapper(), username);
        if(!l.isEmpty()) {
            result = Optional.of(l.getFirst());
        }
        return result;
    }

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users where email = ?" ;
        Optional<User> result = Optional.empty();


        List<User> l = jdbcTemplate.query(sql, new UserRowMapper(), email);
        if(!l.isEmpty()) {
            result = Optional.of(l.getFirst());
        }
        return result;
    }

    public Optional<User> findById(int userId) {
        String sql = "SELECT * FROM users where id = ?";
        Optional<User> result = Optional.empty();


        List<User> l = jdbcTemplate.query(sql, new UserRowMapper(), userId);
        if(!l.isEmpty()) {
            result = Optional.of(l.getFirst());
        }
        return result;
    }

    public User save(User user) {

        if(findById(user.getId()).isPresent()) {
            String template = "UPDATE users SET username = ?, pass_hash = ?, balance = ?, email = ? WHERE id = ?";
            jdbcTemplate.update(template, user.getUsername(), user.getPassHash(), user.getBalance(), user.getEmail(), user.getId());
        }
        else {
            String template = "INSERT INTO users (\"username\", \"pass_hash\", \"balance\", \"email\", \"created_at\") VALUES (?, ?, ?, ?, ?)" ;
            jdbcTemplate.update(template, user.getUsername(), user.getPassHash(), user.getBalance(), user.getEmail(), new Date());
        }


        return user;
    }

    public boolean existsByUsername(String username) {
        return findByUsername(username).isPresent();
    }


    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }
}
