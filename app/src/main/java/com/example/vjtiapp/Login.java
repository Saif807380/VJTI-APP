package com.example.vjtiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    //defining views
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;

    //firebase auth object
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        if (!new PrefManager(this).isUserLogedOut()) {
            //user's email and password both are saved in preferences
            Home();
        }



        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);
        textViewSignup  = (TextView) findViewById(R.id.textViewSignUp);

        buttonSignIn.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        String email = editTextEmail.getText().toString().trim();
                        String password  = editTextPassword.getText().toString().trim();

                        //checking if email and passwords are empty
                        if(TextUtils.isEmpty(email)){
                            Toast.makeText(Login.this,"Please enter email",Toast.LENGTH_LONG).show();
                            return;
                        }

                        if(TextUtils.isEmpty(password)){
                            Toast.makeText(Login.this,"Please enter password",Toast.LENGTH_LONG).show();
                            return;
                        }

                        saveLoginDetails(email,password);
                        //logging in the user
                        firebaseAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        //if the task is successfull
                                        if(task.isSuccessful()){
                                            //start the profile activity
                                            finish();
                                            Home();
                                        }
                                        else{
                                            Toast.makeText(Login.this,"Incorrect Password!",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                }
        );

        textViewSignup.setOnClickListener(
                new TextView.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Login.this,MainActivity.class));
                        finish();
                    }
                }
        );

    }

    private void saveLoginDetails(String email, String password) {
        new PrefManager(this).saveLoginDetails(email, password);
    }

    private void Home(){
        Intent intent2 = new Intent(this,Homescreen.class);
        startActivity(intent2);
        finish();
    }
}
