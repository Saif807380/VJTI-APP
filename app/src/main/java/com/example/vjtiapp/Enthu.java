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

public class Enthu extends AppCompatActivity {
    ListView listView;
    TextView textView;
    String[] listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enthu);

        listView=(ListView)findViewById(R.id.listView);
        textView = findViewById(R.id.listTextView);
        listItem = getResources().getStringArray(R.array.Enthu);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_text_view, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position)
                {
                    case 0: Intent enthu = new Intent();
                        enthu.setAction(Intent.ACTION_VIEW);
                        enthu.addCategory(Intent.CATEGORY_BROWSABLE);
                        enthu.setData(Uri.parse("https://www.facebook.com/enthusia.vjti"));
                        startActivity(enthu);
                        break;
                    case 1: Intent enthu1 = new Intent();
                        enthu1.setAction(Intent.ACTION_VIEW);
                        enthu1.addCategory(Intent.CATEGORY_BROWSABLE);
                        enthu1.setData(Uri.parse("https://www.instagram.com/enthusiavjti/?hl=en"));
                        startActivity(enthu1);
                        break;
                }


            }
        });
    }
}
