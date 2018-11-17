package com.reomor.impl.service;

import com.reomor.core.domain.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User get(Long id);

    void delete(User user);

    User update(User user);

    List<User> getAll();
}
