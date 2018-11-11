package com.reomor.core.repository;

import com.reomor.core.domain.User;

import java.util.List;

public interface UserRepository {

    User findOneByEmail(String email);

    User create(User user);

    User get(Long id);

    User delete(User user);

    User update(User user);

    List<User> getAll();
}
