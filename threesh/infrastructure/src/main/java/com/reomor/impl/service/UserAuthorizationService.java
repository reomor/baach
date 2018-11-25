package com.reomor.impl.service;

import com.reomor.core.domain.User;
import com.reomor.core.domain.UserRoles;
import com.reomor.core.domain.VerificationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserAuthorizationService extends UserDetailsService {

    User register(String name, String email, String password, boolean enable, UserRoles role, UserRoles... roles);

    User register(String name, String email, String password);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    User findAndAuthenticate(String email, String providedPassword);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String token);
}
