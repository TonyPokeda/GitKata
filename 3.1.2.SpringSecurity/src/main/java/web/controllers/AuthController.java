
package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthController {

    @GetMapping("/admin")
    public String adminPanel() {
        return "admin";
    }

    @GetMapping("/user")
    public String usernPage() {
        return "user";
    }




}





