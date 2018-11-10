package com.reomor.impl.repository;

import com.reomor.core.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface JpaUserRepository extends CrudRepository<User, Long> {
}
