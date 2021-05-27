package com.hiwijaya.springsecurity.service;

import com.hiwijaya.springsecurity.entity.User;
import com.hiwijaya.springsecurity.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    public User saveUser(User user){
        return authRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = authRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println(user.getRole() + user.getPassword());

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority(user.getRole().toString()))     // try use roles() instead
                .build();
    }

}
