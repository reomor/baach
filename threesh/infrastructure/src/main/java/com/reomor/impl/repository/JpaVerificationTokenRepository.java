package com.reomor.impl.repository;

import com.reomor.impl.entity.UserEntity;
import com.reomor.impl.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaVerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {

    VerificationTokenEntity findByToken(String token);

    VerificationTokenEntity findByUser(UserEntity userEntity);
}
