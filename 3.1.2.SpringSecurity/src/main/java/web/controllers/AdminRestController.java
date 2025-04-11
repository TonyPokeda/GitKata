package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import web.entity.Role;
import web.entity.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с ID " + id + " не найден");
        }
        return user;
    }

    @PostMapping("/new")
    public User createUser(@RequestBody User user) {
        System.out.println("Received User: " + user);  // Логируем, что приходит в запросе
        return userService.saveUser(user); // Предполагается, что метод saveUser возвращает сохраненного пользователя
    }

    @GetMapping("/update/{id}")
    public Map<String, Object> getEditUserForm(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с ID " + id + " не найден");
        }
        List<Role> roles = roleService.getAllRoles();

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("roles", roles);

        return response;
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id,
                             @RequestBody User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ошибка валидации данных пользователя");
        }

        try {
            System.out.println("Updated roles: " + user.getRolesIds()); // Печатаем роли для отладки
            userService.updateUser(id, user, user.getRolesIds());
            return "Пользователь успешно обновлён!";
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        System.out.println("Deleting user with id: " + id);
        try {
            userService.deleteUser(id);
            return "Пользователь успешно удалён!";
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}