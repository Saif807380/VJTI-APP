package com.example.vjtiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends AppCompatActivity {

    EditText get_pass;
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        get_pass = findViewById(R.id.admin_pass);
        upload = findViewById(R.id.upload_admin);

        upload.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(get_pass.getText().toString().trim().equals("BitchLasagna")){
                            startActivity(new Intent(Admin.this,Upload.class));
                            finish();
                        }
                        else{
                            Toast.makeText(Admin.this,"Incorrect Password!",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}
