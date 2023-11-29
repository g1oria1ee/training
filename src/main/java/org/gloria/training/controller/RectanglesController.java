package org.gloria.training.controller;

import org.gloria.training.model.Rectangles;
import org.gloria.training.repo.RectangleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RectanglesController {

    @Autowired
    private RectangleRepo rectangleRepo;

    @GetMapping("/getAllRectangles")
    public ResponseEntity<List<Rectangles>> getAllRectangles() {
        try {
            List<Rectangles> rectanglesList = new ArrayList<>();
            rectangleRepo.findAll().forEach(rectanglesList::add);

            if (rectanglesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(rectanglesList, HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getRectangleById/{id}")
    public ResponseEntity<Rectangles> getRectangleById(@PathVariable Integer id) {
        Optional<Rectangles> rectangleData = rectangleRepo.findById(id);

        if (rectangleData.isPresent()) {
            return new ResponseEntity<>(rectangleData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addRectangle")
    public ResponseEntity<Rectangles> addRectangle(@RequestBody Rectangles rectangles) {
        rectangles.setArea(rectangles.getArea());
        rectangles.setPerimeter(rectangles.getPerimeter());
        Rectangles recObj = rectangleRepo.save(rectangles);
        return new ResponseEntity<>(recObj, HttpStatus.OK);
    }

    @PostMapping("/updateRectangleById/{id}")
    public ResponseEntity<Rectangles> updateBookById(@PathVariable Integer id, @RequestBody Rectangles newRecData) {
        Optional<Rectangles> oldRecData = rectangleRepo.findById(id);
        Rectangles rectangles = oldRecData.get();

        if (oldRecData.isPresent()) {
            Rectangles updatedRecData = oldRecData.get();
            updatedRecData.setLength(newRecData.getLength());
            updatedRecData.setWidth(newRecData.getWidth());
            updatedRecData.setUnit(newRecData.getUnit());
            updatedRecData.setArea(newRecData.getArea());
            updatedRecData.setPerimeter(newRecData.getPerimeter());

            Rectangles recObj = rectangleRepo.save(updatedRecData);
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
