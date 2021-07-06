package com.hiwijaya.springsecurity.service;

import com.hiwijaya.springsecurity.util.TokenProvider;
import com.hiwijaya.springsecurity.entity.User;
import com.hiwijaya.springsecurity.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Happy Indra Wijaya
 */
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;


    public User register(User user){

        authRepository.findByUsername(user.getUsername())
                .ifPresent((u) -> {
                    throw new RuntimeException("Username already registered.");
                });

        return authRepository.save(user);
    }

    public String login(String username, String password){

        User user = authRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        return tokenProvider.createToken(username, user.getRole());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = authRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority(user.getRole().toString()))     // try use roles() instead
                .build();
    }

}
