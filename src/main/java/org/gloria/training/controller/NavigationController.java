package org.gloria.training.controller;

import org.gloria.training.model.Rectangle;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller

public class NavigationController {

    @GetMapping("/nav")
    public String getNav() {
        return "navigation";
    }

    @GetMapping("/area")
    public String getArea() {
        return "area";
    }

    @GetMapping("/perimeter")
    public String getPerimeter() {
        return "perimeter";
    }

    @PostMapping("/calculate-area")
    public String calcArea(@Validated @ModelAttribute Rectangle rectangle, Model model) {
        System.out.println("Area of " + rectangle.toString());
        model.addAttribute("result", rectangle.getArea());
        model.addAttribute("unit", "units squared");
        return "result";
    }

    @PostMapping("/calculate-perimeter")
    public String calcPerimeter(@Validated @ModelAttribute Rectangle rectangle, Model model) {
        System.out.println("Perimeter of " + rectangle.toString());
        model.addAttribute("result", rectangle.getPerimeter());
        model.addAttribute("unit", "units");
        return "result";
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(TypeMismatchException ex) {
        return "/";
    }
}
