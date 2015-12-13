package com.upversionlab.keugasto.model;

/**
 * Created by rborcat on 11/3/2015.
 */
public class Expense {
    public String category;
    public String value;
    public String date;
    public String description;

    public Expense(String category, String value, String date, String description) {
        this.category  = category;
        this.value = value;
        this.date = date;
        this.description = description;
    }
}
