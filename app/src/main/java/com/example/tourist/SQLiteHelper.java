package com.example.tourist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME = "UserDataBase";

    public static final String TABLE_NAME = "UserTable";

    public static final String Table_Column_ID = "id";

    public static final String Table_Column_1_Name = "name";

    public static final String Table_Column_2_Email = "email";
    public static final String Table_Column_3_Phone_Number = "phoneNumber";

    public static final String Table_Column_4_Password = "password";
    public static final String Table_Column_5_ConfimPassword = "confirmpassword";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabaseObj;

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + Table_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + Table_Column_1_Name + " VARCHAR, " + Table_Column_2_Email + " VARCHAR," + Table_Column_3_Phone_Number + " INTEGER, " + Table_Column_4_Password + ")";
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

}
