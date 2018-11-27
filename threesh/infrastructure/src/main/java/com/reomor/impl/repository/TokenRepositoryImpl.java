package com.reomor.impl.repository;

import com.reomor.core.domain.User;
import com.reomor.core.domain.VerificationToken;
import com.reomor.impl.entity.VerificationTokenEntity;
import com.reomor.impl.mapper.DomainToEntityMapper;
import com.reomor.impl.mapper.EntityToDomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRepositoryImpl implements TokenRepository {

    private final JpaVerificationTokenRepository tokenRepository;
    private final JpaUserRepository userRepository;
    private final DomainToEntityMapper domainToEntityMapper;
    private final EntityToDomainMapper entityToDomainMapper;

    @Autowired
    public TokenRepositoryImpl(
            JpaVerificationTokenRepository tokenRepository,
            JpaUserRepository userRepository,
            DomainToEntityMapper domainToEntityMapper,
            EntityToDomainMapper entityToDomainMapper
    ) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.domainToEntityMapper = domainToEntityMapper;
        this.entityToDomainMapper = entityToDomainMapper;
    }

    @Override
    public VerificationToken findByToken(String token) {
        return entityToDomainMapper.convertToken(tokenRepository.findByToken(token));
    }

    @Override
    public VerificationToken findByUser(User user) {
        VerificationTokenEntity verificationTokenEntity = tokenRepository.findByUser(domainToEntityMapper.convertUser(user));
        return entityToDomainMapper.convertToken(verificationTokenEntity);
    }

    @Override
    public VerificationToken save(User user, String token) {
        VerificationTokenEntity verificationTokenEntity = domainToEntityMapper.convertToken(new VerificationToken(token, null));
        verificationTokenEntity.setUser(userRepository.getOne(user.getId()));
        return entityToDomainMapper.convertToken(tokenRepository.save(verificationTokenEntity));
    }

    @Override
    public void delete(String token) {
        tokenRepository.deleteByToken(token);
    }
}
