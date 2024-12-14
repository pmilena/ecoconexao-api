package com.environment.api.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
    List<Usuario> findAll();
}
