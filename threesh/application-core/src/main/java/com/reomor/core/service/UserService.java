package com.reomor.core.service;

import com.reomor.core.domain.User;

public interface UserService {

    User create(User user);

    User get(Long id);

    User delete(User user);

    User update(User user);
}
