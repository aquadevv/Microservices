package ru.itmentor.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.crud.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}