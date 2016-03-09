package com.upversionlab.keugasto.model.expenseModel;

import com.upversionlab.keugasto.model.categoryModel.Category;

import java.io.Serializable;

/**
 * Created by rborcat on 11/3/2015.
 */
public class Expense implements Serializable {
    public Category category;
    public String value;
    public String date;
    public String description;

    public Expense(Category category, String value, String date, String description) {
        this.category  = category;
        this.value = value;
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return "{Category: " + this.category + "," +
                "Value: " + this.value + "," +
                "Date: " + this.date + "," +
                "Description: " + this.description + "}";
    }
}
