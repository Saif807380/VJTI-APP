package com.example.vjtiapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class Aero extends AppCompatActivity {

    ListView listView;
    TextView textView;
    String[] listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aero);

        listView=(ListView)findViewById(R.id.listView);
        textView = findViewById(R.id.listTextView);
        listItem = getResources().getStringArray(R.array.aero);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_text_view, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position)
                {
                    case 0: Intent aero = new Intent();
                        aero.setAction(Intent.ACTION_VIEW);
                        aero.addCategory(Intent.CATEGORY_BROWSABLE);
                        aero.setData(Uri.parse("https://www.facebook.com/aerovjti.in/"));
                        startActivity(aero);
                        break;
                    case 1: Intent aero1 = new Intent();
                        aero1.setAction(Intent.ACTION_VIEW);
                        aero1.addCategory(Intent.CATEGORY_BROWSABLE);
                        aero1.setData(Uri.parse("https://aerovjti.com"));
                        startActivity(aero1);
                        break;
                }


            }
        });

    }
}
