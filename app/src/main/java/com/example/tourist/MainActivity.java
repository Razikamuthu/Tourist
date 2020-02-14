package com.example.tourist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button Login, signup;
    EditText Email, Password;
    TextView Forget;
    String EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String TempPassword = "NOT_FOUND" ;
    public static final String UserEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login = (Button)findViewById(R.id.login);

        signup= (Button)findViewById(R.id.signup);

        Email = (EditText)findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);
        Forget=(TextView)findViewById(R.id.forgetpassword);

        sqLiteHelper = new SQLiteHelper(this);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckEditTextStatus();
                LoginFunction();
                if((Email.getText().toString().equals("Admin"))&&(Password.getText().toString().equals("123")))
                {
                    Toast.makeText(MainActivity.this, "ADMIN", Toast.LENGTH_LONG).show();
                    Intent E = new Intent(MainActivity.this, AdminActivity.class);
                    startActivity(E);
                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SIGN.class);
                startActivity(intent);

            }
        });
        Forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent F=new Intent(MainActivity.this,Forget.class);
                startActivity(F);
            }
        });

    }
    public void LoginFunction(){

        if(EditTextEmptyHolder) {
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
            cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);
            while (cursor.moveToNext()) {
                if (cursor.isFirst()) {
                    cursor.moveToFirst();
                    TempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_4_Password));
                    cursor.close();
                }
            }
            CheckFinalResult();
        }
        else {
            Toast.makeText(MainActivity.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();

        }

    }
    public void CheckEditTextStatus(){
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false ;
        }
        else {

            EditTextEmptyHolder = true ;
        }
    }
    public void CheckFinalResult(){

        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {

            Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, Search.class);
            intent.putExtra(UserEmail, EmailHolder);
            startActivity(intent);
        }
        else {
            Toast.makeText(MainActivity.this,"UserName or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();
        }
        TempPassword = "NOT_FOUND" ;
    }


}







