package com.example.tourist;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.tourist.SQLiteHelper.TABLE_NAME;
import static com.example.tourist.SQLiteHelper.Table_Column_1_Name;
import static com.example.tourist.SQLiteHelper.Table_Column_2_Email;
import static com.example.tourist.SQLiteHelper.Table_Column_4_Password;
import static com.example.tourist.SQLiteHelper.Table_Column_ID;

public class ListAdminActivity extends AppCompatActivity {
    ListView lv;
    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabaseObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlist);
        lv = (ListView) findViewById(R.id.list);
        sqLiteHelper = new SQLiteHelper(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list2,R.id.list_value, mobileArray);
        l.setAdapter(adapter);
       ;
    }
    public ArrayList getRegistrationData() {
        {
            String selectQuery = "SELECT  * FROM " + TABLE_NAME;
            sqLiteDatabaseObj = sqLiteHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabaseObj.rawQuery(selectQuery, null, null);
            ArrayList dataModelArrayList = new ArrayList();
            if (cursor.moveToFirst()) {
                do {
                    List regdm = new List();
                    regdm.setId(cursor.getString(cursor.getColumnIndex(Table_Column_ID)));
                    regdm.setName(cursor.getString(cursor.getColumnIndex(Table_Column_1_Name)));
                    regdm.setEmail(cursor.getString(cursor.getColumnIndex(Table_Column_2_Email)));
                    regdm.setPassword(cursor.getString(cursor.getColumnIndex(Table_Column_4_Password)));
                    dataModelArrayList.add(regdm);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return dataModelArrayList;
        }

    }

}





