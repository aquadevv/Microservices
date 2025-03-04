package ru.itmentor.spring.boot_security.demo.service.admin;

import ru.itmentor.spring.boot_security.demo.dto.UserCreateDto;
import ru.itmentor.spring.boot_security.demo.dto.UserResponse;
import ru.itmentor.spring.boot_security.demo.dto.UserUpdateDto;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse createUser(UserCreateDto userDto);

    UserResponse updateUser(Long userId, UserUpdateDto userDto);

    void deleteUser(Long userId);

    User findByUsername(String username);
}
