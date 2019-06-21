package com.example.vjtiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
public class Map extends AppCompatActivity {

    ImageButton location,map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        location = findViewById(R.id.location);
        map = findViewById(R.id.campus_map);

        location.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent location = new Intent();
                        location.setAction(Intent.ACTION_VIEW);
                        location.addCategory(Intent.CATEGORY_BROWSABLE);
                        location.setData(Uri.parse("https://www.google.com/maps/place/Veermata+Jijabai+Technological+Institute/@19.0205141,72.8556745,16.81z/data=!4m8!1m2!2m1!1svjti!3m4!1s0x3be7cf26f4972d21:0x2c50185364aca4c1!8m2!3d19.0222181!4d72.8561212"));
                        startActivity(location);
                    }
                }
        );


        map.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent map = new Intent();
                        map.setAction(Intent.ACTION_VIEW);
                        map.addCategory(Intent.CATEGORY_BROWSABLE);
                        map.setData(Uri.parse("https://www.youtube.com/watch?v=48uVXES73qA"));
                        startActivity(map);
                    }
                }
        );

    }
}
