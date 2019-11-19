package com.example.first;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbfin extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "dur.db";
    public static final String TABLE_NAME = "kc";
    public static final String COL_1 = "crop";
    public static final String COL_2 = "ins";
    public static final String COL_3 = "ds";
    public static final String COL_4 = "ms";
    public static final String COL_5 = "ls";

    public dbfin(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE kc (crop TEXT PRIMARY  KEY ,ins INTEGER ,ds INTEGER,ms INTEGER,ls INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addcrop() {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("crop", "groundnut");
        contentValues.put("ins", "0.45");
        contentValues.put("ds", "0.75");
        contentValues.put("ms", "1.05");
        contentValues.put("ls", "0.70");
        db.insert("kc", null, contentValues);
        db.close();


    }
}