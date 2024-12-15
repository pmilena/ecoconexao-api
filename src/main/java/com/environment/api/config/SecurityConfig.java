package com.environment.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        // Definir usuário admin com login "admin" e senha "admin"
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password("{noop}admin")  // {noop} para senha sem criptografia
                        .roles("ADMIN")
                        .build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/users/register").permitAll()
                        .requestMatchers("/api/users/list").permitAll()
                        .requestMatchers("/api/users/delete/{id}").permitAll()
                        .requestMatchers("/api/users/update/{id}").permitAll()
                        .requestMatchers("/admin.html").hasRole("ADMIN") // Restrição para admin
                        .anyRequest().authenticated() // Exigir autenticação para outras rotas
                )
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher("/h2-console/**"),
                                new AntPathRequestMatcher("/api/users/register"),
                                new AntPathRequestMatcher("/api/users/list"),
                                new AntPathRequestMatcher("/api/users/delete/{id}"),
                                new AntPathRequestMatcher("/api/users/update/{id}")
                        )
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página de login personalizada
                        .permitAll() // Permitir acesso a todos para a página de login
                );

        return http.build();
    }
}
