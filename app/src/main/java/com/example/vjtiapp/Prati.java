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

public class Prati extends AppCompatActivity {

    ListView listView;
    TextView textView;
    String[] listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prati);

        listView=(ListView)findViewById(R.id.listView);
        textView = findViewById(R.id.listTextView);
        listItem = getResources().getStringArray(R.array.Prati);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_text_view, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position)
                {
                    case 0: Intent prati = new Intent();
                        prati.setAction(Intent.ACTION_VIEW);
                        prati.addCategory(Intent.CATEGORY_BROWSABLE);
                        prati.setData(Uri.parse("https://www.facebook.com/pratibimbvjti"));
                        startActivity(prati);
                        break;
                    case 1: Intent prati1 = new Intent();
                        prati1.setAction(Intent.ACTION_VIEW);
                        prati1.addCategory(Intent.CATEGORY_BROWSABLE);
                        prati1.setData(Uri.parse("https://www.instagram.com/pratibimbvjti/?hl=en"));
                        startActivity(prati1);
                        break;
                }


            }
        });
    }
}
