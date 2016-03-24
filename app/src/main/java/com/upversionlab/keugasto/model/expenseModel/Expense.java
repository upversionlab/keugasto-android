package com.upversionlab.keugasto.model.expenseModel;

import com.upversionlab.keugasto.model.categoryModel.Category;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rborcat on 11/3/2015.
 */
public class Expense implements Serializable {
    public Category category;
    public String value;
    public Date date;
    public String description;

    public Expense(Category category, String value, Date date, String description) {
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
