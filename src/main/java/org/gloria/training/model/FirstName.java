package org.gloria.training.model;

public class FirstName {

    private String fname;
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @Override
    public String toString() {
        return "FirstName{" +
                "fname='" + fname + '\'' +
                '}';
    }

}
