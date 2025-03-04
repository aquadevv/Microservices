package ru.itmentor.spring.boot_security.demo.controller.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;

public interface AdminController {
    String getAllUsers(Model model);

    String addUser(@ModelAttribute("userDto") UserDto userDto);

    String deleteUser(@RequestParam("id") Long userId);

    String updateUser(@RequestParam("id") Long userId, @ModelAttribute("user") UserDto userDto);
}