package com.hiwijaya.springsecurity.config;

import com.hiwijaya.springsecurity.util.CustomAccessDeniedHandler;
import com.hiwijaya.springsecurity.util.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author Happy Indra Wijaya
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenFilter tokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/public").permitAll()
                .antMatchers("/api/**").permitAll()
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
                    //.exceptionHandling().accessDeniedPage("/access-denied")
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        ;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/img/**", "/css/**");
    }

}
