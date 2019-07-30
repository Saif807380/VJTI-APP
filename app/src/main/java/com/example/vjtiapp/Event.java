package com.example.vjtiapp;

import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Event extends AppCompatActivity  {

    ImageButton coc,techno,prati,enthu,sra,aero,racing,rang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        coc = findViewById(R.id.coc);
        techno = findViewById(R.id.techno);
        prati = findViewById(R.id.prati);
        enthu = findViewById(R.id.enthu);
        sra = findViewById(R.id.sra);
        rang = findViewById(R.id.rang);
        racing = findViewById(R.id.racing);
        aero = findViewById(R.id.aero);

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

        sra.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent sra = new Intent(Event.this,sra.class);
                        startActivity(sra);
                    }
                }
        );

        aero.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent aero = new Intent(Event.this,Aero.class);
                        startActivity(aero);
                    }
                }
        );

        racing.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent racing = new Intent(Event.this,Racing.class);
                        startActivity(racing);
                    }
                }
        );

        rang.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent rang = new Intent(Event.this,Rang.class);
                        startActivity(rang);
                    }
                }
        );
    }

}
