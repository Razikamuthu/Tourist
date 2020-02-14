package com.example.tourist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.TextView;

public class Search extends AppCompatActivity {
    GridView simpegrid;
   String[] Places = {"Tirunelveli", "Thoothukudi", "Madurai", "Chennai", "MSengalpattu", "Coimbatore", "Dindugul", "kanchipuram","Virudhunagar","Nagerkovil","Tirupur"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, Places);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.location);
        actv.setThreshold(1);
        actv.setAdapter(adapter);
        actv.setTextColor(Color.BLACK);
    }
}
