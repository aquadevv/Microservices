package ru.itmentor.spring.boot_security.demo.service.user;

import ru.itmentor.spring.boot_security.demo.dto.UserResponse;

public interface UserService {
    UserResponse getCurrentUser();
}
