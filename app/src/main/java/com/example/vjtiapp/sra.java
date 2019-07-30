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

public class sra extends AppCompatActivity {

    ListView listView;
    TextView textView;
    String[] listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sra);

        listView=(ListView)findViewById(R.id.listView);
        textView = findViewById(R.id.listTextView);
        listItem = getResources().getStringArray(R.array.Coc);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_text_view, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position)
                {
                    case 0: Intent sra = new Intent();
                        sra.setAction(Intent.ACTION_VIEW);
                        sra.addCategory(Intent.CATEGORY_BROWSABLE);
                        sra.setData(Uri.parse("https://www.facebook.com/SRAVTA/"));
                        startActivity(sra);
                        break;
                    case 1: Intent sra1 = new Intent();
                        sra1.setAction(Intent.ACTION_VIEW);
                        sra1.addCategory(Intent.CATEGORY_BROWSABLE);
                        sra1.setData(Uri.parse("https://www.instagram.com/sra_vjti/?hl=en"));
                        startActivity(sra1);
                        break;
                }


            }
        });

    }
}
