package com.reomor.impl.repository;

import com.reomor.core.domain.User;

public interface UserAuthorizationRepository {
    User findOneByEmail(String email);
}
