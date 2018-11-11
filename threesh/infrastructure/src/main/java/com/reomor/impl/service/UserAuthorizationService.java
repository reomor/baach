package com.reomor.impl.service;

import com.reomor.impl.entity.UserEntity;
import com.reomor.impl.entity.UserRoles;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserAuthorizationService extends UserDetailsService {

    UserEntity register(String name, String email, String password, UserRoles role, UserRoles... roles);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    UserEntity findAndAuthenticate(String email, String providedPassword);
}
