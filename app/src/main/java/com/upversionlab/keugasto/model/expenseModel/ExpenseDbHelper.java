package com.upversionlab.keugasto.model.expenseModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.upversionlab.keugasto.expense.AddExpenseActivity;
import com.upversionlab.keugasto.model.expenseModel.ExpenseContract.ExpenseColumns;

import java.util.ArrayList;

/**
 * Created by rborcat on 12/8/2015.
 */
public class ExpenseDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ExpenseDb.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ExpenseColumns.TABLE_NAME + "(" +
                    ExpenseColumns._ID + " INTEGER PRIMARY KEY," +
                    ExpenseColumns.COLUMN_NAME_CATEGORY + " TEXT," +
                    ExpenseColumns.COLUMN_NAME_VALUE + " TEXT," +
                    ExpenseColumns.COLUMN_NAME_DATE + " TEXT," +
                    ExpenseColumns.COLUMN_NAME_DESCRIPTION + " TEXT" +
            ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ExpenseColumns.TABLE_NAME;

    public ExpenseDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public static long createExpense(AddExpenseActivity activity, String category, String value, String date, String description) {
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

    public static ArrayList readExpense(Context context) {
        ArrayList arrayExpense = new ArrayList<>();
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

        Cursor cursor = db.query(
                ExpenseContract.ExpenseColumns.TABLE_NAME,  // The table to query
                projection,                 // The columns to return
                null,                       // The columns for the WHERE clause
                null,                       // The values for the WHERE clause
                null,                       // don't group the rows
                null,                       // don't filter by row groups
                sortOrder                   // The sort order
        );

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String category = cursor.getString(cursor.getColumnIndexOrThrow(ExpenseContract.ExpenseColumns.COLUMN_NAME_CATEGORY));
            String value = cursor.getString(cursor.getColumnIndexOrThrow(ExpenseContract.ExpenseColumns.COLUMN_NAME_VALUE));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(ExpenseContract.ExpenseColumns.COLUMN_NAME_DATE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(ExpenseContract.ExpenseColumns.COLUMN_NAME_DESCRIPTION));

            arrayExpense.add(new Expense(category, value, date, description));
        }

        return arrayExpense;
    }
}
