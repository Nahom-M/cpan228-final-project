package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/index").permitAll() // allow only index page without login
                .anyRequest().authenticated() // require login for everything else
            )
            .httpBasic() // basic auth (username/password)
            .and()
            .csrf().disable(); // disable CSRF for simplicity

        return http.build();
    }
}
