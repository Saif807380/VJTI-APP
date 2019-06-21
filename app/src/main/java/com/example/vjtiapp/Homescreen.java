package com.example.vjtiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;

public class Homescreen extends AppCompatActivity {

    ImageButton exit,events,map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        exit = findViewById(R.id.exit);
        events = findViewById(R.id.events);
        map = findViewById(R.id.map);

        events.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        event();
                    }
                }
        );


        exit.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );


        map.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        map();
                    }
                }
        );

    }

    private void event(){
        Intent event_activity = new Intent(this,Event.class);
        startActivity(event_activity);


    }

    private void map(){
        Intent map_activity = new Intent(this,Map.class);
        startActivity(map_activity);

    }
}
