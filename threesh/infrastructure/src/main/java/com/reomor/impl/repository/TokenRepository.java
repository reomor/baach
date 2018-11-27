package com.reomor.impl.repository;

import com.reomor.core.domain.User;
import com.reomor.core.domain.VerificationToken;

public interface TokenRepository {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);

    VerificationToken save(User user, String token);

    void delete(String token);
}
