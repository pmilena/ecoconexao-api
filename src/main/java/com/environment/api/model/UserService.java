package com.environment.api.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Usuario registerUser(Usuario user) {
        // Verifique se o email já existe
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email já está cadastrado.");
        }

        // Salvar o usuário no banco de dados
        return userRepository.save(user);
    }

    public List<Usuario> listAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }
}
