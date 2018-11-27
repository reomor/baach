package com.reomor.impl.service;

import com.reomor.core.domain.User;
import com.reomor.core.domain.UserRoles;
import com.reomor.core.domain.VerificationToken;
import com.reomor.exception.UserAlreadyRegesteredException;
import com.reomor.impl.repository.TokenRepository;
import com.reomor.impl.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String name, String email, String password, boolean enable, UserRoles role, UserRoles... roles) {
        if (Objects.nonNull(userRepository.findOneByEmail(email))) {
            throw new UserAlreadyRegesteredException("User with this email has been registered before");
        }
        String passwordSalt = UUID.randomUUID().toString();
        String passwordHash = passwordEncoder.encode(password + passwordSalt);
        User user = new User(name, email, passwordHash, passwordSalt, enable, role, roles);
        return userRepository.create(user);
    }

    @Override
    public User register(String name, String email, String password) {
        return register(name, email, password, false, UserRoles.ROLE_USER);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public User findAndAuthenticate(String email, String providedPassword) {
        User user = userRepository.findOneByEmail(email);
        if (user == null) {
            return null;
        }
        String saltedPassword = providedPassword + user.getPasswordSalt();
        if (passwordEncoder.matches(saltedPassword, user.getPasswordHash())) {
            return user;
        }
        return null;
    }

    @Override
    @Transactional //for async event handling
    public void createVerificationToken(User user, String token) {
        tokenRepository.save(user, token);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public User create(User user) {
        return userRepository.create(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.get(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }
}
