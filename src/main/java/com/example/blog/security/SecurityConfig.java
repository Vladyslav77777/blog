package com.example.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/home", "/user/register", "/user/login").permitAll() // Доступ к публичным страницам
                                .anyRequest().authenticated() // Все остальные запросы требуют авторизации
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/user/login") // Страница входа
                                .defaultSuccessUrl("/home", true) // Перенаправление после успешного входа
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/user/login?logout")
                                .permitAll()
                );
                //.csrf().disable(); // Отключение CSRF для упрощения (не рекомендуется на продакшене)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
