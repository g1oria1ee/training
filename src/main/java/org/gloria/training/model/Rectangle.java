package org.gloria.training.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="Rectangles")
@Data
@JsonIgnoreProperties(value = { "scaledHeight" })
public class Rectangle {

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

    public Integer getArea() {
        return getLength() * getWidth();
    }

    public Integer getPerimeter() {
        return 2 * (getLength() + getWidth());
    }



    private String color;

    @Transient
    private Integer scaledHeight;

    public Integer getScaledHeight() {
        BigDecimal scaledHeight = (BigDecimal.valueOf(Math.min(getLength(), getWidth())).divide(
                BigDecimal.valueOf(Math.max(getLength(), getWidth())), 2, BigDecimal.ROUND_DOWN)).multiply(new BigDecimal(250));
        return Integer.valueOf(scaledHeight.intValue());
    }

}
