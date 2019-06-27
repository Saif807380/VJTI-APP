package com.example.vjtiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FileManager extends AppCompatActivity {

    Button upload,download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);
        upload = findViewById(R.id.Upload_button);
        download = findViewById(R.id.Download_button);

        upload.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(FileManager.this,Admin.class));
                    }
                }
        );

        download.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(FileManager.this,Download.class));
                    }
                }
        );
    }
}
