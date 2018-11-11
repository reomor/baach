package com.reomor.impl.repository;

import com.reomor.impl.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findOneByEmail(String email);
}
