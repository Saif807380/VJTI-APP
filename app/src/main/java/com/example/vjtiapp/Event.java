package com.example.vjtiapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Event extends AppCompatActivity {

    ImageButton coc,techno,prati,enthu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        coc = findViewById(R.id.coc);
        techno = findViewById(R.id.techno);
        prati = findViewById(R.id.prati);
        enthu = findViewById(R.id.enthu);

        coc.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent coc_page = new Intent(Event.this,COC.class);
                        startActivity(coc_page);
                    }
                }
        );

        techno.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent techno_page = new Intent(Event.this,Techno.class);
                        startActivity(techno_page);

                    }
                }
        );

        prati.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent prati_page = new Intent(Event.this,Prati.class);
                        startActivity(prati_page);
                    }
                }
        );


        enthu.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent enthu_page = new Intent(Event.this,Enthu.class);
                        startActivity(enthu_page);
                    }
                }
        );

    }
}
