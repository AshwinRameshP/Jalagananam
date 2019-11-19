package com.example.first;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class IndexDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "kcvalues.db";
    public static final String TABLE_NAME = "kc";
    public static final String COL_1 = "crop";
    public static final String COL_2 = "ins";
    public static final String COL_3 = "ds";
    public static final String COL_4 = "ms";
    public static final String COL_5 = "ls";

    public IndexDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       //// sqLiteDatabase.execSQL("CREATE TABLE kc (crop TEXT PRIMARY  KEY ,ins INTEGER ,ds INTEGER,ms INTEGER,ls INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       // sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        //onCreate(sqLiteDatabase);
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


    public double checkdata(String inout, String Season, String soil,String NAME) {
        double p = 0;
        double eto = 0, et = 0;
        double temp = 0;
        String cn="";
        if(NAME.equals("INITIAL"))

        {
            cn="ins";
        }
        else if(NAME.equals("DEVELOPMENT"))
        {
            cn="ds";
        }
        else if(NAME.equals("MID-"))
        {
            cn="ms";
        }
        else if(NAME.equals("FINAL"))
        {
            cn="ls";
        }

        String[] columns = {cn};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_1 + "=?";
        String[] selectionArgs = {inout};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();


       // db.close();

        if (count > 0) {
            if (Season.equals("JAN")) {
                p = 0.26;
            } else if (Season.equals("APR")) {
                p = 0.28;
            } else if (Season.equals("JUL")) {
                p = 0.29;
            } else if (Season.equals("SEP")) {
                p = 0.28;
            }
            if (soil.equals("WET")) {
                temp = 23.02;
            } else if (soil.equals("DRY")) {
                temp = 26.8;
            }
            eto = p * (0.46 * temp + 8);
            cursor.moveToFirst();
             String data = cursor.getString(cursor.getColumnIndex(cn));
cursor.close();
db.close();
            double kc = Double.parseDouble(data);

            et = eto * kc * 30 * 4046.86;

            return et;
        }
            else
                return -1;
        }
    }



