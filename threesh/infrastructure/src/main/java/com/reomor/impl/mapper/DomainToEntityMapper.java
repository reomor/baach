package com.reomor.impl.mapper;

import com.reomor.core.domain.User;
import com.reomor.impl.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class DomainToEntityMapper {
    public UserEntity convertUser(User user) {
        return new UserEntity(user.getId(), user.getName(), user.getEmail(), user.getPasswordHash(), user.getPasswordSalt(), user.getRolesList());
    }
}
