package com.example.tourist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SIGN extends AppCompatActivity {
    EditText Email;
    EditText Password;
    EditText Name;
    EditText Confirm;
    EditText PhoneNumber;
    Button Sign;
    String NameHolder, EmailHolder, PasswordHolder,ConfirmPasswordHolder,PhoneNumberHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);
        Sign = (Button) findViewById(R.id.sign2);
        Email = (EditText) findViewById(R.id.email1);
        PhoneNumber = (EditText) findViewById(R.id.phone_number);
        Password = (EditText) findViewById(R.id.password2);
        Confirm = (EditText) findViewById(R.id.reenter);
        Name = (EditText) findViewById(R.id.username);
        sqLiteHelper = new SQLiteHelper(this);
        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                CheckEditTextStatus();
                CheckingEmailAlreadyExistsOrNot();
                EmptyEditTextAfterDataInsert();
            }
        });

    }
    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }
    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME + "(" + SQLiteHelper.Table_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + SQLiteHelper.Table_Column_1_Name + " VARCHAR, " + SQLiteHelper.Table_Column_2_Email + " VARCHAR,"+SQLiteHelper.Table_Column_3_Phone_Number+" INTEGER, " + SQLiteHelper.Table_Column_4_Password + " VARCHAR)");

    }

    public void InsertDataIntoSQLiteDatabase(){

        if(EditTextEmptyHolder == true)
        {
            SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+"(name,email,phoneNumber,password) VALUES('"+NameHolder+"', '"+EmailHolder+"', '"+PhoneNumberHolder+"','"+PasswordHolder+"');";
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            sqLiteDatabaseObj.close();
            Toast.makeText(SIGN.this,"User Registered Successfully", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(SIGN.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
        }

    }
    public void EmptyEditTextAfterDataInsert(){

        Name.getText().clear();

        Email.getText().clear();

        Password.getText().clear();
    }
    public void CheckEditTextStatus(){

        NameHolder = Name.getText().toString() ;
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false ;
        }
        else {

            EditTextEmptyHolder = true ;
        }
    }

    public void CheckingEmailAlreadyExistsOrNot(){
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.isFirst()) {
                cursor.moveToFirst();
                F_Result = "Email Found";
                cursor.close();
            }
        }
        CheckFinalResult();
    }
    public void CheckFinalResult(){
        if(F_Result.equalsIgnoreCase("Email Found"))
        {
            Toast.makeText(SIGN.this,"Email Already Exists",Toast.LENGTH_LONG).show();
        }
        else {

            InsertDataIntoSQLiteDatabase();
        }
        F_Result = "Not_Found" ;
    }

}
