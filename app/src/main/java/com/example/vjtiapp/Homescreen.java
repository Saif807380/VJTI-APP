package com.example.vjtiapp;

import android.Manifest;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Homescreen extends AppCompatActivity{

    Button profile,events,map,academics,logout;
    String branch,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        profile = findViewById(R.id.profile);
        events = findViewById(R.id.events);
        map = findViewById(R.id.map);
        academics = findViewById(R.id.academics);
        logout = findViewById(R.id.logout);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(Homescreen.this,"Please Allow All Permissions And Login Again!",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            return;
        }



        events.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        event();
                    }
                }
        );


        profile.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Homescreen.this,Display_Profile.class));
                    }
                }
        );


        map.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        map();
                    }
                }
        );

        academics.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        branch = new GetStudentDetails().getBranch();
                        year = new GetStudentDetails().getYear();
                        startActivity(new Intent(Homescreen.this,FileManager.class));
                    }
                }
        );

        logout.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        new PrefManager(Homescreen.this).deleteSharedPreferences();
                        startActivity(new Intent(Homescreen.this,Login.class));
                        finish();
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
