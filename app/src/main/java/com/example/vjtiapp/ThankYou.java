package com.example.vjtiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.content.Intent;

public class ThankYou extends AppCompatActivity {

    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        home = findViewById(R.id.go_to_home);

        home.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Home();
                    }
                }
        );

    }

    private void Home(){
        Intent intent2 = new Intent(this,Homescreen.class);
        startActivity(intent2);
        finish();
    }
}
