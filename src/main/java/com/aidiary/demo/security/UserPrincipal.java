package com.aidiary.demo.security;

import com.aidiary.demo.dto.UserAccountDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record UserPrincipal(String username,
                             String password,
                            Collection<? extends GrantedAuthority> authorities) implements UserDetails {

    public static UserPrincipal of(String username ,String password)
    {
        Set<String> role = Set.of("ROLE_USER");
        return new UserPrincipal(username,
                                  password,
                                  role.stream()
                                          .map(SimpleGrantedAuthority::new)
                                          .collect(Collectors.toUnmodifiableSet()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return false;
    }

    public static UserPrincipal from(UserAccountDto dto)
    {
        return new UserPrincipal(dto.userId(), dto.password(), dto.grades()
                                                                .stream()
                                                                .map(SimpleGrantedAuthority::new)
                                                                .collect(Collectors.toUnmodifiableSet()));
    }
}
