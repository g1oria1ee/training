package org.gloria.training.controller;

import org.gloria.training.model.FirstName;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class NavigationController {

    @GetMapping("/form")
    public String getFormOne() {
        return "form-one";
    }

    @GetMapping("/nav")
    public String getNav() {
        return "navigation";
    }

    @PostMapping("/calculate-area")
    public String calcArea(@ModelAttribute FirstName firstName, Model model) {
        System.out.println(firstName.toString());
        model.addAttribute("firstname", firstName.getFname());
        return "result";
    }
}
