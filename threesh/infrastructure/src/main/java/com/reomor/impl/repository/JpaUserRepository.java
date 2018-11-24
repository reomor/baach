package com.reomor.impl.repository;

import com.reomor.impl.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findOneByEmail(String email);

    List<UserEntity> findAll();
}
