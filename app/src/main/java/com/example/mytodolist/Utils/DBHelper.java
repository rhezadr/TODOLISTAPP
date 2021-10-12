package com.example.mytodolist.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "db_login";
    public static final String TABLE_NAME = "table_login";

    public static final String ROW_ID = "_id";
    public static final String ROW_USERNAME = "Username";
    public static final String ROW_PASSWORD = "Password";

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
        sqLiteDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ROW_USERNAME + " TEXT," + ROW_PASSWORD + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    //Insert Data
    public void insertData(ContentValues values){
        sqLiteDatabase.insert(TABLE_NAME, null, values);
    }


    public boolean checkUser(String username, String password){
        String[] columns = {ROW_ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = ROW_USERNAME + "=?" + " and " + ROW_PASSWORD + "=?";
        String[] selectionArgs = {username,password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }

}
