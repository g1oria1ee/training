package org.gloria.training.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="Dates")
@Data
public class Date {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer updateMonth;
    private Integer updateDay;
    private Integer updateYear;

}
