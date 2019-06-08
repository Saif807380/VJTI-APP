package com.example.vjtiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;
import static android.Manifest.permission.READ_CONTACTS;


public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    String user_email;
    String user_pass;

    Button createButton;
    CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passEditText);

        createButton = findViewById(R.id.createButton1);

        if (!new PrefManager(this).isUserLogedOut()) {
            //user's email and password both are saved in preferences
            startHomeActivity();
        }


        createButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {

                        user_email = email.getText().toString();
                        user_pass = password.getText().toString();

                        checkBoxRememberMe = findViewById(R.id.checkBox1);

                        login();

                    }});

    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, ThankYou.class);
        startActivity(intent);
        finish();
    }

    private void saveLoginDetails(String email, String password) {
        new PrefManager(this).saveLoginDetails(email, password);
    }

    private void login()
    {
        if(user_email.contains(".vjti.ac.in"))
        {
            if(user_pass.length()>=8) {


                if(checkBoxRememberMe.isChecked()){
                    saveLoginDetails(user_email,user_pass);

                }
                startHomeActivity();
            }
            else
            {
                Toast.makeText(
                        MainActivity.this,"Password too short!",Toast.LENGTH_LONG
                ).show();
            }
        }
        else
        {
            Toast.makeText(
                    MainActivity.this,"Only VJTI domain email IDs allowed!",Toast.LENGTH_LONG
            ).show();
        }
    }

}










