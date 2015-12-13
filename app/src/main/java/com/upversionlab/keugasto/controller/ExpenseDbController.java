package com.upversionlab.keugasto.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.upversionlab.keugasto.addscreen.AddExpenseActivity;
import com.upversionlab.keugasto.model.ExpenseContract;
import com.upversionlab.keugasto.model.ExpenseDbHelper;

/**
 * Created by rborcat on 12/13/2015.
 */
public class ExpenseDbController {

    public static long insertValuesToDb(AddExpenseActivity activity, String category, String value, String date, String description) {
        ExpenseDbHelper dbHelper = new ExpenseDbHelper(activity);
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ExpenseContract.ExpenseColumns.COLUMN_NAME_CATEGORY, category);
        values.put(ExpenseContract.ExpenseColumns.COLUMN_NAME_VALUE, value);
        values.put(ExpenseContract.ExpenseColumns.COLUMN_NAME_DATE, date);
        values.put(ExpenseContract.ExpenseColumns.COLUMN_NAME_DESCRIPTION, description);

        // Insert the new row, returning the primary key value of the new row
        return db.insert(
                ExpenseContract.ExpenseColumns.TABLE_NAME,
                null,
                values);
    }

    public static Cursor readValuesFromDb(Context context) {
        ExpenseDbHelper dbHelper = new ExpenseDbHelper(context);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ExpenseContract.ExpenseColumns.COLUMN_NAME_CATEGORY,
                ExpenseContract.ExpenseColumns.COLUMN_NAME_VALUE,
                ExpenseContract.ExpenseColumns.COLUMN_NAME_DATE,
                ExpenseContract.ExpenseColumns.COLUMN_NAME_DESCRIPTION
        };

        String sortOrder = ExpenseContract.ExpenseColumns.COLUMN_NAME_DATE + " DESC";

        return db.query(
                ExpenseContract.ExpenseColumns.TABLE_NAME,  // The table to query
                projection,                 // The columns to return
                null,                       // The columns for the WHERE clause
                null,                       // The values for the WHERE clause
                null,                       // don't group the rows
                null,                       // don't filter by row groups
                sortOrder                   // The sort order
        );
    }
}
