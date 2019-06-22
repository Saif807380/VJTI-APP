package com.example.vjtiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    public String user_email;
    public String user_pass;
    DatabaseReference mFireBase;
    Button createButton;
    CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFireBase = FirebaseDatabase.getInstance().getReference();
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passEditText);
        createButton = findViewById(R.id.next);


        if (!new PrefManager(this).isUserLogedOut()) {
            //user's email and password both are saved in preferences
            Home();
        }


        createButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {

                        user_email = email.getText().toString();
                        user_pass = password.getText().toString();

                        checkBoxRememberMe = findViewById(R.id.checkBox1);
                        mFireBase.child("Users");
                        login();

                    }});

    }

    private void startProfileActivity() {
        Intent intent = new Intent(this, Profile.class);
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
                AddToUsers();
                startProfileActivity();
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



    private void Home(){
        Intent intent2 = new Intent(this,Homescreen.class);
        startActivity(intent2);
        finish();
    }

    public void AddToUsers(){

        mFireBase = FirebaseDatabase.getInstance().getReference().child("Users");
        HashMap<String,String> datamap = new HashMap<String,String>();
        datamap.put("Email",user_email);
        datamap.put("Password",user_pass);

        mFireBase.push().setValue(datamap);
    }

}










