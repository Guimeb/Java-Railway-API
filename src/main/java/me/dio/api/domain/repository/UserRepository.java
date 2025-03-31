package me.dio.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.api.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
