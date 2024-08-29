package com.auth.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()
//                        .requestMatchers("/user/sign-up", "/user/sign-in").permitAll()
//                        .anyRequest().authenticated()
//                );
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
