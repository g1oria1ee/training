package org.gloria.training.model;

public class Rectangle {


    private Integer length;

    private Integer width;

    public Integer getWidth() {
        if (width == null) return length;
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length='" + length + '\'' +
                ", width='" + width + '\'' +
                '}';
    }

    public Integer getArea() {
        return getLength() * getWidth();
    }

    public Integer getPerimeter() {
        return 2 * (getLength() + getWidth());
    }


}
