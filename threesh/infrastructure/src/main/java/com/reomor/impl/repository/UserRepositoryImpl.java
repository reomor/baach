package com.reomor.impl.repository;

import com.reomor.core.domain.User;
import com.reomor.impl.entity.UserEntity;
import com.reomor.impl.mapper.DomainToEntityMapper;
import com.reomor.impl.mapper.EntityToDomainMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final EntityToDomainMapper entityToDomainMapper;
    private final DomainToEntityMapper domainToEntityMapper;

    public UserRepositoryImpl(
            JpaUserRepository jpaUserRepository,
            EntityToDomainMapper entityToDomainMapper,
            DomainToEntityMapper domainToEntityMapper
    ) {
        this.jpaUserRepository = jpaUserRepository;
        this.entityToDomainMapper = entityToDomainMapper;
        this.domainToEntityMapper = domainToEntityMapper;
    }

    @Override
    public User findOneByEmail(String email) {
        UserEntity userEntity = jpaUserRepository.findOneByEmail(email);
        return userEntity == null ? null : entityToDomainMapper.convertUser(userEntity);
    }

    @Override
    public User create(User user) {
        UserEntity userEntity = jpaUserRepository.save(domainToEntityMapper.convertUser(user));
        return entityToDomainMapper.convertUser(userEntity);
    }

    @Override
    public User get(Long id) {
        UserEntity userEntity = jpaUserRepository.findById(id).orElse(null);
        return entityToDomainMapper.convertUser(userEntity);
    }

    @Override
    public void delete(User user) {
        jpaUserRepository.deleteById(user.getId());
    }

    @Override
    public User update(User user) {
        return create(user);
    }

    @Override
    public List<User> getAll() {
        List<UserEntity> all = jpaUserRepository.findAll();
        return all.stream().map(entityToDomainMapper::convertUser).collect(Collectors.toList());
    }
}
