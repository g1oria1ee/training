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
        Rectangle newRec = rectangleDao.addNewRectangle(rectangle);
        return newRec;
    }

//    public Rectangle deleteRectangleById(Integer id) {
//        Rectangle rec = rectangleDao.deleteRectangleById(id);
//        return rec;
//    }

    public List<Rectangle> getRectangleById(Integer id) {
        List<Rectangle> rec = rectangleDao.getRectangleById(id);
        return rec;
    }

    public List<Rectangle> getAllRectangles() {
        List<Rectangle> allRecs = rectangleDao.getAllRectangles();
        return allRecs;
    }
}
