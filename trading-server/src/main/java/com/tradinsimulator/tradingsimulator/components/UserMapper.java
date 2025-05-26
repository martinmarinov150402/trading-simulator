package com.tradinsimulator.tradingsimulator.components;

import com.tradinsimulator.tradingsimulator.dto.UserProfileDto;
import com.tradinsimulator.tradingsimulator.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserProfileDto toUserProfileDto(final User user) {
        return new UserProfileDto(user.getBalance(), user.getUsername());
    }
}