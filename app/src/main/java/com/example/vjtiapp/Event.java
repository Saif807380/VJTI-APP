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
                        Intent coc = new Intent();
                        coc.setAction(Intent.ACTION_VIEW);
                        coc.addCategory(Intent.CATEGORY_BROWSABLE);
                        coc.setData(Uri.parse("https://www.facebook.com/CommunityOfCoders/"));
                        startActivity(coc);
                    }
                }
        );

        techno.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent techno = new Intent();
                        techno.setAction(Intent.ACTION_VIEW);
                        techno.addCategory(Intent.CATEGORY_BROWSABLE);
                        techno.setData(Uri.parse("https://www.facebook.com/technovanza"));
                        startActivity(techno);
                    }
                }
        );

        prati.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent prati = new Intent();
                        prati.setAction(Intent.ACTION_VIEW);
                        prati.addCategory(Intent.CATEGORY_BROWSABLE);
                        prati.setData(Uri.parse("https://www.facebook.com/pratibimbvjti"));
                        startActivity(prati);
                    }
                }
        );


        enthu.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent enthu = new Intent();
                        enthu.setAction(Intent.ACTION_VIEW);
                        enthu.addCategory(Intent.CATEGORY_BROWSABLE);
                        enthu.setData(Uri.parse("https://www.facebook.com/enthusia.vjti"));
                        startActivity(enthu);
                    }
                }
        );

    }
}
