package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.entity.User;
import web.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }
}