package com.hiwijaya.springsecurity;

import com.hiwijaya.springsecurity.entity.User;
import com.hiwijaya.springsecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Happy Indra Wijaya
 */
@Component
public class Runner implements ApplicationRunner {

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        User admin = User.builder()
                .username("hiwijaya")
                .password(passwordEncoder.encode("mesosfer!"))
                .role(Role.ROLE_ADMIN)
                .build();

//        authService.register(admin);

    }

}
