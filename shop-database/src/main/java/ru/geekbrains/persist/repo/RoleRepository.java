package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
