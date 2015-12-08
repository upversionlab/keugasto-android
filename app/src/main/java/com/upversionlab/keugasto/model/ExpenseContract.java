package com.upversionlab.keugasto.model;

import android.provider.BaseColumns;

/**
 * Created by rborcat on 12/8/2015.
 */
public final class ExpenseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public ExpenseContract() {}

    /* Inner class that defines the table contents */
    public static abstract class ExpenseColumns implements BaseColumns {
        public static final String TABLE_NAME = "expense";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_VALUE = "value";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }
}
