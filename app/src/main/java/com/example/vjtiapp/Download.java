package com.example.vjtiapp;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Download extends AppCompatActivity {
    //the listview
    ListView listView1;
    String path;
    Spinner sub;


    //database reference to get uploads data
    DatabaseReference mDatabaseReference;


    //list to store uploads data
    List<BasicUpload> uploadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        uploadList = new ArrayList<>();
        listView1 = (ListView) findViewById(R.id.subjects);
        sub = findViewById(R.id.choose_subject_folder);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Second_year_comps, R.layout.spinner_item);


        sub.setAdapter(adapter1);

        sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewAllFiles(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final BasicUpload upload = uploadList.get(position);

                downloadFile(upload.getUrl(),upload.getName());


                BroadcastReceiver onComplete = new BroadcastReceiver() {

                    public void onReceive(Context ctxt, Intent intent) {

                        // get the refid from the download manager
                        long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

                        new NotificationHelper(Download.this).createChannels();

                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(Download.this,NotificationHelper.CHANNEL_ONE_ID)
                                        .setSmallIcon(R.drawable.notification1)
                                        .setContentTitle("Download Complete")
                                        .setContentText(upload.getName() + " downloaded to Download/VJTI-APP/" + path);


                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(455, mBuilder.build());

                    }

                };


                registerReceiver(onComplete,
                        new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));


            }
        });

    }



    private void viewAllFiles(final int position) {
        //getting the database reference
        switch (position){
            case 0: mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_Maths);
                    path = "Maths";
                    break;
            case 1: mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_Discrete);
                path = "Discrete Maths";
                    break;
            case 2: mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_DLD);
                path = "DLD";
                    break;
            case 3: mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_DS);
                path = "DS";
                break;
            case 4: mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_JAVA);
                path = "JAVA";
                    break;
            case 5: mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_EVS);
                path = "EVS";
                break;
            case 6: mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_COA);
                path = "COA";
                break;
            case 7: mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_PYTHON);
                path = "PYTHON";
                break;
            case 8: mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_SECOND_YEAR_COMPS_OTHER);
                path = "OTHER";
                break;
        }


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uploadList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    BasicUpload upload  = postSnapshot.getValue(BasicUpload.class);
                    uploadList.add(upload);
                }


                String[] uploads = new String[uploadList.size()];

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.download_list,uploads);

                listView1.setAdapter(adapter);

                for(int i=0;i<uploads.length;i++){

                    uploads[i] = uploadList.get(i).getName();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void downloadFile(String uRl, final String name) {
        File direct = new File(Environment.getExternalStorageDirectory()
                + "/Download");

        if (!direct.exists()) {
             direct.mkdirs();
        }

        DownloadManager mgr = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        Uri downloadUri = Uri.parse(uRl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle(name)
                .setDestinationInExternalPublicDir("/Download/VJTI-APP/"+path, name);
         mgr.enqueue(request);


        }

}


