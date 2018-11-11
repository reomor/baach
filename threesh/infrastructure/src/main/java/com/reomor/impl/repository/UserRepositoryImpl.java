package com.reomor.impl.repository;

import com.reomor.core.domain.User;
import com.reomor.core.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User delete(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
