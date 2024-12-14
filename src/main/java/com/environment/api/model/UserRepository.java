package com.environment.api.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
