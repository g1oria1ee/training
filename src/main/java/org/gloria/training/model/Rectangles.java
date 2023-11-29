package org.gloria.training.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Rectangles")
@Setter
@Getter
@ToString
public class Rectangles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer length;

    private Integer width;

    public Integer getWidth() {
        if (width == null) return length;
        return width;
    }

    private Integer area;

    private Integer perimeter;

    private String unit;

    public Integer getArea() {
        return getLength() * getWidth();
    }

    public Integer getPerimeter() {
        return 2 * (getLength() + getWidth());
    }
}
