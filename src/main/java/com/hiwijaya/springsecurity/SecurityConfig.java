package com.hiwijaya.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


/**
 * @author Happy Indra Wijaya
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("rahasia"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("rahasia"))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/content").hasAnyRole("USER", "ADMIN")
                .antMatchers("/restricted").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/img/**", "/css/**");
    }

}
