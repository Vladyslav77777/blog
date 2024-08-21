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
                                .requestMatchers("/", "/home").permitAll() // Разрешаем доступ к / и /home всем
                                .anyRequest().authenticated() // Остальные запросы требуют авторизации
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login") // Страница входа
                                .loginProcessingUrl("/perform_login") // URL для обработки формы входа
                                .defaultSuccessUrl("/home", true) // URL после успешного входа
                                .permitAll() // Разрешаем доступ к форме входа всем
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout") // URL для выхода
                                .logoutSuccessUrl("/login?logout") // URL после выхода
                                .permitAll() // Разрешаем доступ к выходу всем
                )//DALSHE OSHIBKAAAAAAA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint((request, response, authException) ->
                                        response.sendRedirect("/custom_login")) // Перенаправление на страницу входа при неавторизованном доступе
                );
        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}







