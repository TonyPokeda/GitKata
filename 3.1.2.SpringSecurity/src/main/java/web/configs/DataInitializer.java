package web.configs;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.entity.Role;
import web.entity.User;
import web.repositories.RoleRepository;
import web.repositories.UserRepository;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public ApplicationRunner initData(UserRepository userRepository
            , RoleRepository roleRepository
            , PasswordEncoder passwordEncoder) {
        return args -> initializeData(userRepository, roleRepository, passwordEncoder);
    }

    private void initializeData(UserRepository userRepository
            , RoleRepository roleRepository
            , PasswordEncoder passwordEncoder) {

        Role userRole = roleRepository
                .findByName("ROLE_USER")
                .orElseGet(() -> roleRepository
                        .save(new Role("ROLE_USER")));
        Role adminRole = roleRepository
                .findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepository
                        .save(new Role("ROLE_ADMIN")));

        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of(adminRole, userRole));
            admin.setSurname("Popov");
            admin.setName("Nikolay");
            userRepository.save(admin);
        }
        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRoles(Set.of(userRole));
            user.setSurname("Ivanov");
            user.setName("Anton");
            userRepository.save(user);
        }
    }
}