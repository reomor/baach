package com.reomor.impl.service;

import com.reomor.core.domain.User;
import com.reomor.core.domain.UserRoles;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserAuthorizationService extends UserDetailsService {

    User register(String name, String email, String password, UserRoles role, UserRoles... roles);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    User findAndAuthenticate(String email, String providedPassword);
}
