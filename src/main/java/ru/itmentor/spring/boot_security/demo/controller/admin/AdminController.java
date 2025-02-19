package ru.itmentor.spring.boot_security.demo.controller.admin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.dto.UserResponse;

import java.util.List;

public interface AdminController {
    List<UserResponse> getAllUsers();

    UserResponse getUserById(@PathVariable Long id);

    UserResponse addUser(@RequestBody UserDto userDto);

    void deleteUser(@PathVariable Long id);

    UserResponse updateUser(@PathVariable Long id, @RequestBody UserDto userDetails);
}