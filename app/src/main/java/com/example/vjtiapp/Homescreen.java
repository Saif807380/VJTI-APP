package com.example.vjtiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;

public class Homescreen extends AppCompatActivity {

    ImageButton exit,events,map,academics,upload,download;
    String branch,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        exit = findViewById(R.id.exit);
        events = findViewById(R.id.events);
        map = findViewById(R.id.map);
        academics = findViewById(R.id.academics);
        upload = findViewById(R.id.upload);
        download = findViewById(R.id.download);

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

        academics.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        branch = new GetStudentDetails().getBranch();
                        year = new GetStudentDetails().getYear();
                        startActivity(new Intent(Homescreen.this,Download.class));
                    }
                }
        );

        upload.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Homescreen.this,Upload.class));
                    }
                }
        );

        download.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {

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
