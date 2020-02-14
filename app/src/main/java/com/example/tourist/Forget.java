package com.example.tourist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Forget extends AppCompatActivity {
    EditText Emailforget,Passforget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        Emailforget=(EditText)findViewById(R.id.email1forget);
        Passforget=(EditText)findViewById(R.id.passforget);
    }
}
