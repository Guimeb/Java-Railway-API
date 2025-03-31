package me.dio.api.service.impl;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.dio.api.domain.model.User;
import me.dio.api.domain.repository.UserRepository;
import me.dio.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User user) {
        if (user.getAccounts() != null) {
            user.getAccounts().forEach(account -> account.setUser(user));
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, User updatedUserData) {
        // Recupera o usuário existente ou lança exceção se não encontrado
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com id: " + id));

        // Atualiza os dados do usuário
        existingUser.setName(updatedUserData.getName());

        // Se os dados das contas forem informados, atualize-os
        if (updatedUserData.getAccounts() != null) {
            // Se o usuário já possui contas, substitua-as pela nova lista
            if (existingUser.getAccounts() == null) {
                existingUser.setAccounts(new ArrayList<>());
            } else {
                existingUser.getAccounts().clear();
            }
            updatedUserData.getAccounts().forEach(account -> {
                account.setUser(existingUser);
                existingUser.getAccounts().add(account);
            });
        }

        // Salva e retorna o usuário atualizado
        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Recupera o usuário existente ou lança exceção se não encontrado
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com id: " + id));

        // A deleção do usuário, se houver cascade no relacionamento, também removerá as contas associadas
        userRepository.delete(existingUser);
    }
}
