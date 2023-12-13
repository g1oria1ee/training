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

    @PostMapping("/viewNewRectangle")
    public String viewNewRec(@ModelAttribute Rectangle rectangle, Model model) {
        String color = rectangle.getColor().replaceAll("\\s+","");
        model.addAttribute("length", rectangle.getLength());
        model.addAttribute("width", rectangle.getWidth());
        model.addAttribute("style", "height: " + rectangle.getScaledHeight()
                + "px; background-color: " + color);
        rectangleService.addNewRectangle(rectangle);
        return "result";
    }

    @GetMapping("/viewRectangle")
    public String viewRec(@RequestParam("id") Integer id, Model model) {
        Rectangle rectangle = rectangleService.getRectangleById(id);
        String color = rectangle.getColor().replaceAll("\\s+","");

        model.addAttribute("highlight", rectangle.getId());
        model.addAttribute("length", rectangle.getLength());
        model.addAttribute("width", rectangle.getWidth());
        model.addAttribute("showRec", true);
        model.addAttribute("style", "height: " + rectangle.getScaledHeight()
                + "px; background-color: " + color);
        return getAllRec(model);
    }

    @GetMapping("/viewAllRectangles")
    public String getAllRec(Model model) {
//        Rectangle testRec = new Rectangle(10);
//        testRec.setId(testRec.getId());
//        testRec.setWidth(testRec.getWidth());
//        testRec.setArea(testRec.getArea());
//        testRec.setColor("blue");
//        testRec.setPerimeter(testRec.getPerimeter());


        List<Rectangle> rectangleList = rectangleService.getAllRectangles();
        //System.out.println(rectangleList);
        model.addAttribute("rectangleList", rectangleList);
        return "view";
    }

    @GetMapping("/deleteRectangle")
    public String deleteRec(@RequestParam("id") Integer id,Model model) {
        rectangleService.deleteRectangle(id);
        return getAllRec(model);
    }

    @GetMapping("/updateRectangle")
    public String updateRec(@RequestParam("id") Integer id, Model model) {
        Rectangle rectangle = rectangleService.getRectangleById(id);
        model.addAttribute("highlight", rectangle.getId());
        model.addAttribute("updateRec", true);
        model.addAttribute("rectangle", rectangle);
        return viewRec(id, model);
    }

    @PostMapping("/viewUpdatedRectangle")
    public String viewUpRec(@ModelAttribute Rectangle rectangle, Model model) {
        rectangleService.updateRectangle(rectangle);
        model.addAttribute("highlight", rectangle.getId());
        return viewRec(rectangle.getId(), model);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(TypeMismatchException ex) {
        return "/";
    }

    @PostMapping("/addNewRectangle")
    @ResponseBody
    public Rectangle addRec(@RequestBody Rectangle rectangle) { return rectangleService.addNewRectangle(rectangle);}

    @GetMapping("/getRectangleById/{id}")
    @ResponseBody
    public Rectangle getRectangleById(@PathVariable Integer id) {
        return rectangleService.getRectangleById(id);
    }

    @GetMapping("/getAllRectangles")
    @ResponseBody
    public List<Rectangle> getAllRectangles() {
        return rectangleService.getAllRectangles();
    }

    @PostMapping("/updateRectangleById/{id}")
    @ResponseBody
    public Rectangle updateRectangleById(@PathVariable Integer id, @RequestBody Rectangle rectangle) {
        rectangle.setId(id);
        return rectangleService.updateRectangle(rectangle);
    }

    @DeleteMapping("/deleteRectangleById/{id}")
    @ResponseBody
    public Rectangle deleteRectangleById(@PathVariable Integer id) { return rectangleService.deleteRectangle(id);}

    @GetMapping("/getRectangleByColor/{color}")
    @ResponseBody
    public List<Rectangle> getRectangleByColor(@PathVariable String color) {
        return rectangleService.getRectangleByColor(color);
    }
}
