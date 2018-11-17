package com.reomor.impl.mapper;

import com.reomor.core.domain.User;
import com.reomor.impl.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityToDomainMapper {
    public User convertUser(UserEntity userEntity) {
        return new User(userEntity.getName(), userEntity.getEmail(), userEntity.getPasswordHash(), userEntity.getPasswordSalt(), userEntity.getRolesList());
    }
}
