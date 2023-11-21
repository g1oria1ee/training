package org.gloria.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class NavigationController {

    @GetMapping("/test")
    public String getTest() {
        return "thirdTest";
    }

    @GetMapping("/contact")
    public String getContact() {
        return "contact";
    }
}
