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

public class Ecell extends AppCompatActivity {

    ListView listView;
    TextView textView;
    String[] listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecell);


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
                    case 0: Intent coc = new Intent();
                        coc.setAction(Intent.ACTION_VIEW);
                        coc.addCategory(Intent.CATEGORY_BROWSABLE);
                        coc.setData(Uri.parse("https://www.facebook.com/ecell.vjti/?hc_ref=ART8ukgecNMQkgZnSW5PRjuowWOkj0FtG0U3TTsJN7NeiISdlIy6-m0nD4Nd70yo9Dw&fref=nf&__xts__[0]=68.ARAeW-y9LcsefWQbBlkqZWz3oBvC1yl_C6Gw7juHuKrr0pwf2bmwJTkGM8RSe3XFRd8UDNHmkS0X3vDwmXiQa4VTk3XqNUXS34W42n39HFYdky-hyncHyiDK2cewcvmUSdC8l7HTpjbyBTPv4ut09IiDocFe2utnscAgaRVgnn609do-s-R4MZ2J11CNQFPnObNxdJUfmIpR6h1EthnqYyD3CXoQ1CgIojWByg6XdcGeRyesn61JXPXETxDKZEfrrIRvkYE9w1JoqvncWXLyj68u0OLC3cRlOfFbX2AguQ6xiKH0DKjsDgxtXSk1zQkhjZbXEESXIl7obyD9eU-FJHgdOA&__tn__=kC-R"));
                        startActivity(coc);
                        break;
                    case 1: Intent coc1 = new Intent();
                        coc1.setAction(Intent.ACTION_VIEW);
                        coc1.addCategory(Intent.CATEGORY_BROWSABLE);
                        coc1.setData(Uri.parse("https://www.instagram.com/ecellvjti/?hl=en"));
                        startActivity(coc1);
                        break;
                }


            }
        });
    }
}
