package com.environment.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Permitir acesso ao console H2
                        .requestMatchers("/api/users/register").permitAll() // Permitir acesso ao cadastro
                        .requestMatchers("/api/users/list").permitAll() // Permitir acesso à lista de usuários
                        .anyRequest().authenticated() // Exigir autenticação para outras rotas
                )
                .cors(Customizer.withDefaults())  // Certifique-se de que o CORS está ativado
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher("/h2-console/**"),
                                new AntPathRequestMatcher("/api/users/register"),
                                new AntPathRequestMatcher("/api/users/list")
                        )
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )
                .formLogin(Customizer.withDefaults()); // Configuração de login padrão

        return http.build();
    }


}


