package com.upversionlab.keugasto.model.categoryModel;

import java.io.Serializable;

/**
 * Created by rborcat on 01/12/2016.
 */
public class Category implements Serializable {
    public Integer id;
    public String name;
    public String value;

    public Category(Integer id, String name, String value) {
        this.id = id;
        this.name  = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{Id: " + this.id + "," +
                "Name: " + this.name + "," +
                "Value: " + this.value + "}";
    }
}
