package ru.itmentor.spring.boot_security.demo.controller.admin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itmentor.spring.boot_security.demo.dto.UserCreateDto;
import ru.itmentor.spring.boot_security.demo.dto.UserResponse;
import ru.itmentor.spring.boot_security.demo.dto.UserUpdateDto;

import java.util.List;

public interface AdminController {
    List<UserResponse> getAllUsers();

    UserResponse getUserById(@PathVariable Long id);

    UserResponse addUser(@RequestBody UserCreateDto userDto);

    void deleteUser(@PathVariable Long id);

    UserResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userDetails);
}