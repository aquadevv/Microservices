package ru.itmentor.crud.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private short age;

    @Column(nullable = false)
    private String email;

    public User(String name, short age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
