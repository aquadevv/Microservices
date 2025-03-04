package ru.itmentor.spring.boot_security.demo.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final RoleRepository roleRepository;

    public Set<Role> mapRoleNamesToEntities(Set<String> roleNames) {
        if (roleNames == null || roleNames.isEmpty()) {
            return Set.of(getDefaultRole());
        }

        return roleNames.stream()
            .map(this::getOrCreateRole)
            .collect(Collectors.toSet());
    }

    private Role getOrCreateRole(String roleName) {
        String normalizedName = roleName.toUpperCase();
        return roleRepository.findByName(normalizedName)
            .orElseGet(() -> createNewRole(normalizedName));
    }

    private Role createNewRole(String name) {
        Role role = new Role();
        role.setName(name);
        return roleRepository.save(role);
    }

    private Role getDefaultRole() {
        return getOrCreateRole("USER");
    }
}
