package com.example.vjtiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

public class Display_Profile extends AppCompatActivity {

    TextView name,branch,year;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__profile);

        name = findViewById(R.id.name);
        name.setText(new PrefManager(Display_Profile.this).getName());
        branch = findViewById(R.id.branch_name);
        branch.setText(new PrefManager(Display_Profile.this).getBranch());
        year = findViewById(R.id.year_name);
        year.setText(new PrefManager(Display_Profile.this).getYear());
        edit = findViewById(R.id.edit);

        edit.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        finish();
                        startActivity(new Intent(Display_Profile.this,EditProfile.class));
                    }
                }
        );

    }

}
