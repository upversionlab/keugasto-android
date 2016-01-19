package com.upversionlab.keugasto.model.categoryModel;

import android.provider.BaseColumns;

/**
 * Created by rborcat on 01/12/2016.
 */
public final class CategoryContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public CategoryContract() {}

    /* Inner class that defines the table contents */
    public static abstract class CategoryColumns implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_VALUE = "value";
    }
}
