package az.huseynov.springMVC.securityThymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage(){
        return "access-denied";
    }
}
