package org.gloria.training.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gloria.training.model.Rectangle;
import org.gloria.training.service.RectangleService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@Controller
@Transactional
public class RectangleController {

    @Autowired
    //private RectangleRepo rectangleRepo;
    private RectangleService rectangleService;

    @GetMapping("/nav")
    public String getNav() {
        return "navigation";
    }

    @GetMapping("/addNewRectangle")
    public String addRec() { return "add"; }

    @PostMapping("/addNewRectangle")
    @ResponseBody
    public Rectangle addRec(@RequestBody Rectangle rectangle) { return rectangleService.addNewRectangle(rectangle);}

    @GetMapping("/getRectangleById/{id}")
    @ResponseBody
    public List<Rectangle> getRectangleById(@PathVariable Integer id) {
        return rectangleService.getRectangleById(id);
    }

    @GetMapping("/getAllRectangles")
    @ResponseBody
    public List<Rectangle> getAllRectangles() {
        return rectangleService.getAllRectangles();
    }

    @PostMapping("/viewNewRectangle")
    public String viewNewRec(@ModelAttribute Rectangle rectangle, Model model) {
        System.out.println(rectangle.toString());
        model.addAttribute("length", rectangle.getLength());
        model.addAttribute("width", rectangle.getWidth());
        model.addAttribute("style", "height: " + rectangle.getScaledHeight()
                + "px; background-color: " + rectangle.getColor());
        rectangleService.addNewRectangle(rectangle);

        return "result";
    }

    @GetMapping("/viewAllRectangles")
    public String getAllRec(@ModelAttribute Model model) {
        List<Rectangle> rectangleList = rectangleService.getAllRectangles();
        System.out.println(rectangleList);
        model.addAttribute("rectangleList", rectangleList);
        return "view";
    }

    @GetMapping("/area")
    public String getArea(Model model) {
        model.addAttribute("title", "Area");
        model.addAttribute("action", "calculate-area");
        return "calculate";
    }

    @GetMapping("/perimeter")
    public String getPerimeter(Model model) {
        model.addAttribute("title", "Perimeter");
        model.addAttribute("action", "calculate-perimeter");
        return "calculate";
    }

    @PostMapping("/calculate-area")
    public String calcArea(@ModelAttribute Rectangle rectangle, Model model) {
        System.out.println("Area of " + rectangle.toString());
        model.addAttribute("result", rectangle.getArea());
        model.addAttribute("length", rectangle.getLength());
        model.addAttribute("width", rectangle.getWidth());
        model.addAttribute("measurement", "area");
        model.addAttribute("scaledHeight", "height: " + rectangle.getScaledHeight() + "px");

        rectangle.setWidth(rectangle.getWidth());
        rectangle.setArea(rectangle.getArea());
        rectangle.setPerimeter(rectangle.getPerimeter());
       // Rectangle recObj = rectangleRepo.save(rectangle);

        return "result";
    }

    @PostMapping("/calculate-perimeter")
    public String calcPerimeter(@Validated @ModelAttribute Rectangle rectangle, Model model) {
        System.out.println("Perimeter of " + rectangle.toString());
        model.addAttribute("result", rectangle.getPerimeter());
        model.addAttribute("length", rectangle.getLength());
        model.addAttribute("width", rectangle.getWidth());
        model.addAttribute("measurement", "perimeter");
        model.addAttribute("scaledHeight", "height: " + rectangle.getScaledHeight() + "px");

        rectangle.setWidth(rectangle.getWidth());
        rectangle.setArea(rectangle.getArea());
        rectangle.setPerimeter(rectangle.getPerimeter());
      //  Rectangle recObj = rectangleRepo.save(rectangle);
        return "result";
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(TypeMismatchException ex) {
        return "/";
    }
}
