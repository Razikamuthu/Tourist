package com.example.tourist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    Button usermanagement,dataupload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        usermanagement=(Button)findViewById(R.id.Usermanagement);
        dataupload=(Button)findViewById(R.id.Dataupload);
        usermanagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminActivity.this,ListAdminActivity.class);
                startActivity(i);
            }
        });
    }
}
