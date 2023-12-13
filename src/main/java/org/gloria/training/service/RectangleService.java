package org.gloria.training.service;

import org.gloria.training.dao.RectangleDao;
import org.gloria.training.model.Rectangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RectangleService {

    @Autowired
    private RectangleDao rectangleDao;

    public Rectangle addNewRectangle(Rectangle rectangle) {
        rectangle.setWidth(rectangle.getWidth());
        rectangle.setArea(rectangle.getArea());
        rectangle.setPerimeter(rectangle.getPerimeter());
        System.out.println(rectangle.toString());
        Rectangle newRec = rectangleDao.addNewRectangle(rectangle);
        return newRec;
    }

    public Rectangle getRectangleById(Integer id) {
        Rectangle rec = rectangleDao.getRectangleById(id);
        return rec;
    }

    public List<Rectangle> getAllRectangles() {
        List<Rectangle> allRecs = rectangleDao.getAllRectangles();
        return allRecs;
    }

    public Rectangle deleteRectangle(Integer id) {
        Rectangle rec = rectangleDao.deleteRectangle(id);
        return rec;
    }

    public Rectangle updateRectangle(Rectangle rec) {
        return rectangleDao.updateRectangle(rec);
    }
}
