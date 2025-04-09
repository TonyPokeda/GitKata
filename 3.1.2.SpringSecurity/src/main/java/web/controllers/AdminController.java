package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.entity.Role;
import web.entity.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getUsers(Model model, @ModelAttribute("error") String error,
                           @ModelAttribute("message") String message) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", error);
        }
        if (message != null && !message.isEmpty()) {
            model.addAttribute("message", message);
        }
        return "admin";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        List<Role> roles = roleService.getAllRoles();
        System.out.println("Available roles: " + roles); // Логируем доступные роли
        model.addAttribute("roles", roles);
        return "new";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("user") @Valid User user,
                          @RequestParam(value = "roles", required = false) List<Long> roleIds,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        // Проверка на наличие ошибок валидации
        if (bindingResult.hasErrors()) {
            List<Role> roles = roleService.getAllRoles(); // Получаем все роли
            model.addAttribute("roles", roles); // Передаем роли в модель
            return "new"; // Возвращаемся к форме создания
        }

        // Проверяем, были ли выбраны роли
        if (roleIds != null && !roleIds.isEmpty()) {
            List<Role> roles = roleService.findRolesByIds(roleIds); // Получаем роли по ID
            System.out.println("Roles retrieved: " + roles); // Логируем полученные роли
            user.setRoles(new HashSet<>(roles)); // Устанавливаем роли для пользователя
        } else {
            System.out.println("No role IDs received."); // Логируем, если ID не получены
        }

        userService.saveUser(user, roleIds); // Сохраняем пользователя с передачей roleIds
        redirectAttributes.addFlashAttribute("message", "Пользователь успешно создан!"); // Сообщение об успешном создании
        return "redirect:/admin"; // Перенаправление на страницу администрирования
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("message", "Пользователь успешно удалён!");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String getEditUserForm(@RequestParam("id") long id, Model model) {
        User user = userService.getUserById(id);
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             @RequestParam(value = "roles", required = false) List<Long> roleIds,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "update";
        }
        try {
            userService.updateUser(user.getId(), user, roleIds);
            redirectAttributes.addFlashAttribute("message", "Пользователь успешно обновлён!");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin";
    }
}
