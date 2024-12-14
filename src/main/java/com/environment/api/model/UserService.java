package com.environment.api.model;

import jakarta.validation.Valid;
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

    public void updateUser(Long id, Usuario updatedUser) {
        Usuario existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setTipoConteudo(updatedUser.getTipoConteudo());
        userRepository.save(existingUser);
    }

}
