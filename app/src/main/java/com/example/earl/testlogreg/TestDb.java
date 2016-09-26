package com.example.earl.testlogreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Earl on 22/09/2016.
 */
public class TestDb extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Register.db";
    private static final String TABLE_NAME = "Users";
    private static final String COL_ID = "id";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    public static final String COL_DATE_CREATED = "date_created";
    private static final String COL_FNAME = "first_name";
    private static final String COL_LNAME = "last_name";
    private static final String COL_UNAME = "username";
    public TestDb(Context con) {
        super(con, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE  = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_FNAME + " TEXT," + COL_LNAME + " TEXT," + COL_UNAME + " TEXT," + COL_EMAIL + " TEXT," + COL_PASSWORD + " TEXT,"  + COL_DATE_CREATED + " DATE" + ");";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insetData(String fname, String lname, String uname, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FNAME, fname);
        contentValues.put(COL_LNAME, lname);
        contentValues.put(COL_UNAME, uname);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getInfo(TestDb dop)
    {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {COL_EMAIL, COL_PASSWORD, COL_ID};
        Cursor CR = SQ.query(TABLE_NAME, columns, null, null, null, null, null);
        return CR;


    }

}
