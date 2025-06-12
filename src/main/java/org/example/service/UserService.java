package org.example.service;

import org.example.config.ResourceNotFoundException;
import org.example.model.Role;
import org.example.model.User;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository; // Нужен для назначения ролей новым пользователям
    private final BCryptPasswordEncoder passwordEncoder; // Для хеширования паролей

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        logger.info("Fetching user by id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public Optional<User> findByUsername(String username) {
        logger.info("Fetching user by username: {}", username);
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User registerNewUser(User user) {
        logger.info("Registering new user: {}", user.getUsername());

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // 1. Устанавливаем роль по умолчанию (например, "USER")
        Role defaultRole = roleRepository.findByRoleName("User")  // Предполагается, что у вас есть роль "USER" в БД
                .orElseGet(() -> {
                    // Если роли "USER" нет, создаем ее
                    Role newRole = new Role();
                    newRole.setRoleName("USER");
                    return roleRepository.save(newRole);
                });
        user.setRole(defaultRole);

        // 2. Хешируем пароль
        String encodedPassword = passwordEncoder.encode(user.getPasswordHash()); // Хешируем введенный пароль
        user.setPasswordHash(encodedPassword);

        // 3. Сохраняем пользователя
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Integer id, User userDetails) {
        logger.info("Updating user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setUsername(userDetails.getUsername());
        //  Важно: Не позволяйте пользователям напрямую менять пароль через этот метод!
        //  Пароль нужно менять через отдельный метод changePassword!
        user.setRole(userDetails.getRole());

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Integer id) {
        logger.info("Deleting user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
    }

    @Transactional
    public void changePassword(Integer id, String oldPassword, String newPassword) {
        logger.info("Changing password for user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // 1. Проверяем, что старый пароль введен верно
        if (!passwordEncoder.matches(oldPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid old password");
        }

        // 2. Хешируем новый пароль
        String encodedNewPassword = passwordEncoder.encode(newPassword);

        // 3. Сохраняем новый пароль
        user.setPasswordHash(encodedNewPassword);
        userRepository.save(user);
    }
}