package com.hrManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

public class SpringSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("Sabana")
                .password("JJJ2023")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authz -> authz
                        .antMatchers("/**")
                        .authenticated()
                )
                .httpBasic();
        return http.build();
    }
}