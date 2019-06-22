package com.example.vjtiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class Profile extends AppCompatActivity implements OnItemSelectedListener{

    Spinner branch,year;
    String branch_name,year_name;
    Button createprofile;
    DatabaseReference mFireBase;
    String user_name;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        branch = (Spinner)findViewById(R.id.branch);
        year = findViewById(R.id.year);
        createprofile = findViewById(R.id.createaccount);
        mFireBase = FirebaseDatabase.getInstance().getReference();
        name = findViewById(R.id.pro_name);



        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.branches, android.R.layout.simple_spinner_item);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.year, android.R.layout.simple_spinner_item);


        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(adapter1);
        branch.setOnItemSelectedListener(this);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adapter2);
        year.setOnItemSelectedListener(this);

        createprofile.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        user_name = name.getText().toString();
                        mFireBase.child("User's Profile");
                            AddToUsersProfile();
                            ThankYou();

                    }});


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

       if(parent.getId()==R.id.branch){
           switch (position) {
               case 0:
                   // Whatever you want to happen when the first item gets selected
                   branch_name = "Computer";

                   break;
               case 1:
                   // Whatever you want to happen when the second item gets selected
                   branch_name = "Electronics";

                   break;
               case 2:
                   // Whatever you want to happen when the thrid item gets selected
                   branch_name = "IT";
                   break;
               case 3:
                   // Whatever you want to happen when the thrid item gets selected
                   branch_name = "EXTC";
                   break;
               case 4:
                   // Whatever you want to happen when the thrid item gets selected
                   branch_name = "Civil";
                   break;
               case 5:
                   // Whatever you want to happen when the thrid item gets selected
                   branch_name = "Textile";
                   break;
               case 6:
                   // Whatever you want to happen when the thrid item gets selected
                   branch_name = "Production";
                   break;
               case 7:
                   // Whatever you want to happen when the thrid item gets selected
                   branch_name = "Mechanical";
                   break;
               case 8:
                   // Whatever you want to happen when the thrid item gets selected
                   branch_name = "Electrical";
                   break;
           }
       }


       else{
           switch (position) {
               case 0:
                   // Whatever you want to happen when the first item gets selected
                   year_name = "First";

                   break;
               case 1:
                   // Whatever you want to happen when the second item gets selected
                   year_name = "Second";

                   break;
               case 2:
                   // Whatever you want to happen when the thrid item gets selected
                   year_name = "Third";
                   break;
               case 3:
                   // Whatever you want to happen when the thrid item gets selected
                   year_name = "Final";
                   break;
           }
       }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if(parent.getId()==R.id.branch)
        {
            Toast.makeText(
                    Profile.this,"Please select a branch",Toast.LENGTH_LONG
            ).show();

        }
        else{
            Toast.makeText(
                    Profile.this,"Please enter year",Toast.LENGTH_LONG
            ).show();
        }
    }

    private void ThankYou(){
        Intent intent1 = new Intent(this,ThankYou.class);
        startActivity(intent1);
        finish();
    }

    public void AddToUsersProfile(){

        mFireBase = FirebaseDatabase.getInstance().getReference().child("User's Profile");
        HashMap<String,String> datamap = new HashMap<String,String>();
        datamap.put("Name",user_name);
        datamap.put("Branch",branch_name);
        datamap.put("Year",year_name);

        mFireBase.push().setValue(datamap);
    }
}


