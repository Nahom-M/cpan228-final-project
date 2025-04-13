package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("message", "Hello from Spring Boot!");
        return "home";
    }

    @GetMapping("/request-item")
    public String showRequestForm() {
        return "request-item";
    }

}
