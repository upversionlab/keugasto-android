package com.upversionlab.keugasto.model.categoryModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.upversionlab.keugasto.category.AddCategoryActivity;
import com.upversionlab.keugasto.model.categoryModel.CategoryContract.CategoryColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rborcat on 01/12/2016.
 */
public class CategoryDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CategoryDb.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CategoryColumns.TABLE_NAME + "(" +
                    CategoryColumns._ID + " INTEGER PRIMARY KEY," +
                    CategoryColumns.COLUMN_NAME_NAME + " TEXT," +
                    CategoryColumns.COLUMN_NAME_VALUE + " TEXT" +
            ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CategoryColumns.TABLE_NAME;

    public CategoryDbHelper(Context context) {
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

    public static long createCategory(AddCategoryActivity activity, String name, String value) {
        CategoryDbHelper dbHelper = new CategoryDbHelper(activity);
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(CategoryColumns.COLUMN_NAME_NAME, name);
        values.put(CategoryColumns.COLUMN_NAME_VALUE, value);

        // Insert the new row, returning the primary key value of the new row
        return db.insert(
                CategoryColumns.TABLE_NAME,
                null,
                values);
    }

    public static Category readCategoryById(Context context, Integer categoryId) {
        Category category = null;
        CategoryDbHelper dbHelper = new CategoryDbHelper(context);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                CategoryColumns._ID,
                CategoryColumns.COLUMN_NAME_NAME,
                CategoryColumns.COLUMN_NAME_VALUE
        };

        String whereColumns = CategoryColumns._ID + "= ?";

        String[] whereValues = {
                categoryId.toString()
        };

        Cursor cursor = db.query(
                CategoryColumns.TABLE_NAME, // The table to query
                projection,                 // The columns to return
                whereColumns,               // The columns for the WHERE clause
                whereValues,                // The values for the WHERE clause
                null,                       // don't group the rows
                null,                       // don't filter by row groups
                null                        // The sort order
        );

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(CategoryColumns._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(CategoryColumns.COLUMN_NAME_NAME));
            String value = cursor.getString(cursor.getColumnIndexOrThrow(CategoryColumns.COLUMN_NAME_VALUE));

            category = new Category(id, name, value);
        }

        return category;
    }

    public static List<Category> readCategory(Context context) {
        ArrayList arrayCategory = new ArrayList<>();
        CategoryDbHelper dbHelper = new CategoryDbHelper(context);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                CategoryColumns._ID,
                CategoryColumns.COLUMN_NAME_NAME,
                CategoryColumns.COLUMN_NAME_VALUE
        };

        Cursor cursor = db.query(
                CategoryColumns.TABLE_NAME,  // The table to query
                projection,                 // The columns to return
                null,                       // The columns for the WHERE clause
                null,                       // The values for the WHERE clause
                null,                       // don't group the rows
                null,                       // don't filter by row groups
                null                        // The sort order
        );

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(CategoryColumns._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(CategoryColumns.COLUMN_NAME_NAME));
            String value = cursor.getString(cursor.getColumnIndexOrThrow(CategoryColumns.COLUMN_NAME_VALUE));

            arrayCategory.add(new Category(id, name, value));
        }

        return arrayCategory;
    }
}
