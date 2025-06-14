package org.example.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);  // Поиск пользователя по имени пользователя

    boolean existsByUsername(String username); // Проверка, существует ли пользователь с данным именем пользователя
}