package com.upversionlab.keugasto.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.upversionlab.keugasto.model.ExpenseContract.ExpenseColumns;

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
}
