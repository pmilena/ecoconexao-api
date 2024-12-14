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
                // Configuração de autorizações
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Permitir acesso ao console H2 (banco)
                        .requestMatchers("/api/users/register").permitAll() //para permitir ao front acessar a url de cadastro
                        .anyRequest().authenticated() // Exigir autenticação para outras rotas
                )
                // Configuração de CORS
                .cors(Customizer.withDefaults())

                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher("/h2-console/**"),
                                new AntPathRequestMatcher("/api/users/register")
                        )
                )
                // Configuração de headers
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )
                // Configuração de login padrão
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
    }


