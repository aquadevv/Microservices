package ru.itmentor.spring.boot_security.demo.service.admin;

import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.dto.UserResponse;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse createUser(UserDto userDto);

    UserResponse updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);

    User findByUsername(String username);
}
