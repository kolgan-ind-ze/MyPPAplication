package com.example.myppaplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context) {
        super(context, DataBaseConst.DATA_BASE_NAME, null, DataBaseConst.DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DataBaseConst.CREATE_TABLE_USERS);
        sqLiteDatabase.execSQL(DataBaseConst.CREATE_TABLE_ORDERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DataBaseConst.DELETE_TABLE_USERS);
        sqLiteDatabase.execSQL(DataBaseConst.DELETE_TABLE_ORDERS);
        onCreate(sqLiteDatabase);
    }
}
