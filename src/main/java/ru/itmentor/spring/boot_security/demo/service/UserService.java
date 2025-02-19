package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void createUser(UserDto userDto);

    void updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);

    User getCurrentUser();

    User findByUsername(String username);
}
