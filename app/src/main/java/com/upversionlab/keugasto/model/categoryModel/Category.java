package com.upversionlab.keugasto.model.categoryModel;

import java.io.Serializable;

/**
 * Created by rborcat on 01/12/2016.
 */
public class Category implements Serializable {
    public String name;
    public String value;

    public Category(String name, String value) {
        this.name  = name;
        this.value = value;
    }
}
