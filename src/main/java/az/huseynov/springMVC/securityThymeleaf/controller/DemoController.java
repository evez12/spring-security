package az.huseynov.springMVC.securityThymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/leaders")
    public String leadersPage() {
        return "leaders";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/view")
    public String viewPage() {
        return "view";
    }

}
