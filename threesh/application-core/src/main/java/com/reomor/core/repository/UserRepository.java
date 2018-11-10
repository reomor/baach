package com.reomor.core.repository;

import com.reomor.core.domain.User;

public interface UserRepository {

    User create(User user);

    User get(Long id);

    User delete(User user);

    User update(User user);
}
