package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import web.entity.Role;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
