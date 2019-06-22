package com.example.vjtiapp;

import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    public String user_email;
    public String user_pass;
    Button createButton;
    TextView signIn;
    CheckBox checkBoxRememberMe;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passEditText);
        createButton = findViewById(R.id.next);
        mAuth = FirebaseAuth.getInstance();
        signIn = findViewById(R.id.textViewSignUp);
        if (!new PrefManager(this).isUserLogedOut()) {
            //user's email and password both are saved in preferences
            finish();
            Home();
        }


        createButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {

                        user_email = email.getText().toString();
                        user_pass = password.getText().toString();

                        checkBoxRememberMe = findViewById(R.id.checkBox1);

                        login();

                    }});

        signIn.setOnClickListener(
                new TextView.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        finish();
                        startActivity(new Intent(MainActivity.this,Login.class));
                    }
                }
        );
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

                mAuth.createUserWithEmailAndPassword(user_email,user_pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"Successful!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
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



}










