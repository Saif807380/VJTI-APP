package com.example.vjtiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class Email_Verification extends AppCompatActivity {

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email__verification);

        next = findViewById(R.id.button);

        next.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().getCurrentUser().reload().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                if(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()){
                                    startActivity(new Intent(Email_Verification.this,Profile.class));
                                    finish();
                                }
                                else{
                                    Toast.makeText(Email_Verification.this,"Please Verify Your Email Address!",Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                }
        );
    }

}
