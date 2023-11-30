package org.gloria.training.controller;

import org.gloria.training.model.Rectangle;
import org.gloria.training.repo.RectangleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RectangleController {

    @Autowired
    private RectangleRepo rectangleRepo;

    @GetMapping("/getAllRectangles")
    public ResponseEntity<List<Rectangle>> getAllRectangles() {
        try {
            List<Rectangle> rectangleList = new ArrayList<>();
            rectangleRepo.findAll().forEach(rectangleList::add);

            if (rectangleList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(rectangleList, HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getRectangleById/{id}")
    public ResponseEntity<Rectangle> getRectangleById(@PathVariable Integer id) {
        Optional<Rectangle> rectangleData = rectangleRepo.findById(id);

        if (rectangleData.isPresent()) {
            return new ResponseEntity<>(rectangleData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addRectangle")
    public ResponseEntity<Rectangle> addRectangle(@RequestBody Rectangle rectangle) {
        rectangle.setArea(rectangle.getArea());
        rectangle.setPerimeter(rectangle.getPerimeter());
        Rectangle recObj = rectangleRepo.save(rectangle);
        return new ResponseEntity<>(recObj, HttpStatus.OK);
    }

    @PostMapping("/updateRectangleById/{id}")
    public ResponseEntity<Rectangle> updateBookById(@PathVariable Integer id, @RequestBody Rectangle newRecData) {
        Optional<Rectangle> oldRecData = rectangleRepo.findById(id);
        Rectangle rectangle = oldRecData.get();

        if (oldRecData.isPresent()) {
            Rectangle updatedRecData = oldRecData.get();
            updatedRecData.setLength(newRecData.getLength());
            updatedRecData.setWidth(newRecData.getWidth());
            updatedRecData.setUnit(newRecData.getUnit());
            updatedRecData.setArea(newRecData.getArea());
            updatedRecData.setPerimeter(newRecData.getPerimeter());

            Rectangle recObj = rectangleRepo.save(updatedRecData);
            return new ResponseEntity<>(recObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("deleteRectangleById/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Integer id) {
        rectangleRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
