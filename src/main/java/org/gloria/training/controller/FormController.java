package org.gloria.training.controller;

import org.gloria.training.model.Rectangle;
import org.gloria.training.repo.RectangleRepo;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller

public class FormController {

    @Autowired
    private RectangleRepo rectangleRepo;

    @GetMapping("/nav")
    public String getNav() {
        return "navigation";
    }

    @GetMapping("/area")
    public String getArea(Model model) {
        model.addAttribute("action", "calculate-area");
        return "calculate";
    }

    @GetMapping("/perimeter")
    public String getPerimeter(Model model) {
        model.addAttribute("action", "calculate-perimeter");
        return "calculate";
    }

    @PostMapping("/calculate-area")
    public String calcArea(@ModelAttribute Rectangle rectangle, Model model) {
        System.out.println("Area of " + rectangle.toString());
        model.addAttribute("result", rectangle.getArea());
        model.addAttribute("unit", rectangle.getUnit());
        model.addAttribute("scaledHeight", "height: " + rectangle.getScaledHeight() + "px");

        rectangle.setWidth(rectangle.getWidth());
        rectangle.setArea(rectangle.getArea());
        rectangle.setPerimeter(rectangle.getPerimeter());
        Rectangle recObj = rectangleRepo.save(rectangle);

        return "result";
    }

    @PostMapping("/calculate-perimeter")
    public String calcPerimeter(@Validated @ModelAttribute Rectangle rectangle, Model model) {
        System.out.println("Perimeter of " + rectangle.toString());
        model.addAttribute("result", rectangle.getPerimeter());
        model.addAttribute("unit", rectangle.getUnit());
        model.addAttribute("scaledHeight", "height: " + rectangle.getScaledHeight() + "px");

        rectangle.setWidth(rectangle.getWidth());
        rectangle.setArea(rectangle.getArea());
        rectangle.setPerimeter(rectangle.getPerimeter());
        Rectangle recObj = rectangleRepo.save(rectangle);
        return "result";
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(TypeMismatchException ex) {
        return "/";
    }
}
