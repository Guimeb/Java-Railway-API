package me.dio.api.service;

import org.springframework.stereotype.Service;

import me.dio.api.domain.model.User;
import me.dio.api.service.UserService;

@Service
public interface UserService {
    User findById(Long id);

    User create(User userToCreate);

    User update(Long id, User updatedUserData);

    void delete(Long id);
}
