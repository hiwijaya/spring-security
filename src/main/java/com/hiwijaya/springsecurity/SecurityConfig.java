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
                .password(passwordEncoder().encode("secret"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("secret"))
                .roles("ADMIN")
                .build();

        UserDetails superAdmin = User.builder()
                .username("super-admin")
                .password(passwordEncoder().encode("secret"))
                .roles("SUPER_ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin, superAdmin);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/public").permitAll()
                .antMatchers("/profile/**").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                .antMatchers("/config").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .antMatchers("/config/reset/**").hasRole("SUPER_ADMIN")
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/perform-login")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/perform-logout")
                    .logoutSuccessUrl("/")
                .and()
                    .exceptionHandling().accessDeniedPage("/access-denied")
        ;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/img/**", "/css/**");
    }

}
