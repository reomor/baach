package com.reomor.core.service;

import com.reomor.core.domain.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User get(Long id);

    User delete(User user);

    User update(User user);

    List<User> getAll();
}
