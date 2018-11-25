package com.reomor.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class User implements UserDetails {

    private Long id;
    private String name;
    private String email;
    private String passwordHash;
    private String passwordSalt;
    private boolean enabled;
    private Set<UserRoles> rolesList;

    public User() {
        this.enabled = false;
    }

    public User(String name, String email, String passwordHash, String passwordSalt, boolean enabled, UserRoles role, UserRoles ... roles) {
        this(null, name, email, passwordHash, passwordSalt, enabled, EnumSet.of(role, roles));
    }

    public User(String name, String email, String passwordHash, String passwordSalt, boolean enabled, Set<UserRoles> rolesList) {
        this(null, name, email, passwordHash, passwordSalt, enabled, rolesList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolesList;
    }

    public void setRolesList(UserRoles role, UserRoles ... roles) {
        setRolesList(EnumSet.of(role, roles));
    }

    public void setRolesList(Set<UserRoles> rolesList) {
        this.rolesList = CollectionUtils.isEmpty(rolesList) ? Collections.emptySet() : EnumSet.copyOf(rolesList);
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
