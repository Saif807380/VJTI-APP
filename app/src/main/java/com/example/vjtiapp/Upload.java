package com.example.vjtiapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Upload extends AppCompatActivity implements View.OnClickListener{

    final static int PICK_PDF_CODE = 2342;

    //these are the views
    TextView textViewStatus;
    EditText editTextFilename;
    Spinner sub;
    String path;
    String  pos;



    //the firebase objects for storage and database
    StorageReference mStorageReference;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        sub = findViewById(R.id.choose_subject);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Second_year_comps, R.layout.spinner_item);


        sub.setAdapter(adapter1);

        sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: path = Constants.STORAGE_SECOND_YEAR_COMPS_Maths;
                            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_Maths);
                            pos = "Maths";
                            break;
                    case 1: path = Constants.STORAGE_SECOND_YEAR_COMPS_Discrete;
                        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_Discrete);
                        pos = "Discrete Maths";
                        break;
                    case 2: path = Constants.STORAGE_SECOND_YEAR_COMPS_DLD;
                        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_DLD);
                        pos = "DLD";
                        break;
                    case 3: path = Constants.STORAGE_SECOND_YEAR_COMPS_DS;
                        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_DS);
                        pos = "DS";
                        break;
                    case 4: path = Constants.STORAGE_SECOND_YEAR_COMPS_JAVA;
                        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_JAVA);
                        pos = "JAVA";
                        break;
                    case 5: path = Constants.STORAGE_SECOND_YEAR_COMPS_EVS;
                        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_EVS);
                        pos = "EVS";
                        break;
                    case 6: path = Constants.STORAGE_SECOND_YEAR_COMPS_COA;
                        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_COA);
                        pos = "COA";
                        break;
                    case 7: path = Constants.STORAGE_SECOND_YEAR_COMPS_PYTHON;
                        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.STORAGE_SECOND_YEAR_COMPS_PYTHON);
                        pos = "PYTHON";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //getting firebase objects
        mStorageReference = FirebaseStorage.getInstance().getReference();



        //getting the views
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
        editTextFilename = (EditText) findViewById(R.id.editTextFileName);



        //attaching listeners to views
        findViewById(R.id.buttonUploadFile).setOnClickListener(this);




    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonUploadFile:
                getPDF();

        }
    }


    //this function will get the pdf from the storage
    private void getPDF() {
        //for greater than lolipop versions we need the permissions asked on runtime
        //so if the permission is not available user will go to the screen to allow storage permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            return;
        }

        //creating an intent for file chooser
        Intent intent = new Intent();
        String [] mimeTypes = {"application/*", "audio/*","image/*"};
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf file"), PICK_PDF_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //when the user choses the file
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                    uploadFile(data.getData(),path);
            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }



    //this method is uploading the file
    //the code is same as the previous tutorial
    //so we are not explaining it
    private void uploadFile(Uri data,String path) {


         final StorageReference sRef = mStorageReference.child(path + editTextFilename.getText().toString());

        sRef.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        textViewStatus.setText("File Uploaded Successfully");

                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uri.isComplete());
                            Uri url =uri.getResult();

                        BasicUpload upload = new BasicUpload(editTextFilename.getText().toString(),url.toString());
                        mDatabaseReference.child(mDatabaseReference.push().getKey()).setValue(upload);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        textViewStatus.setText((int) progress + "% Uploading...");
                    }
                });

    }


}

