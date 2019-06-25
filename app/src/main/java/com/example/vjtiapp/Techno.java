package com.example.vjtiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Techno extends AppCompatActivity {

    ListView listView;
    TextView textView;
    String[] listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techno);

        listView=(ListView)findViewById(R.id.listView);
        textView = findViewById(R.id.listTextView);
        listItem = getResources().getStringArray(R.array.Techno);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_text_view, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position)
                {
                    case 0: Intent techno = new Intent();
                        techno.setAction(Intent.ACTION_VIEW);
                        techno.addCategory(Intent.CATEGORY_BROWSABLE);
                        techno.setData(Uri.parse("https://www.facebook.com/technovanza"));
                        startActivity(techno);
                        break;
                    case 1: Intent techno1 = new Intent();
                        techno1.setAction(Intent.ACTION_VIEW);
                        techno1.addCategory(Intent.CATEGORY_BROWSABLE);
                        techno1.setData(Uri.parse("https://www.instagram.com/technovanza/?hl=en"));
                        startActivity(techno1);
                        break;
                }


            }
        });
    }
}
