package com.example.hp.electrician.activity.user.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.hp.electrician.R;

public class service_activity extends AppCompatActivity {

    ListView listView;
    String arr[]={"AC Cleaning","Air Cooler Cleaning","Water purifier","Kitchen Appliances"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_activity);
        listView=(ListView)findViewById(R.id.service_listview);
        ArrayAdapter arrayAdapter=new ArrayAdapter(service_activity.this,android.R.layout.simple_list_item_1,arr);
        listView.setAdapter(arrayAdapter);

        setTitle("Services");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = listView.getAdapter().getItem(position).toString();
                if (item == "AC Cleaning"){
                    Intent intent = new Intent(service_activity.this, acservice_activity.class);
                    startActivity(intent);
                }
                if (item == "Air Cooler Cleaning"){
                    Intent intent = new Intent(service_activity.this, coolerservice_activity.class);
                    startActivity(intent);
                }
                if(item == "Water purifier"){
                    Intent intent = new Intent(service_activity.this,roservice_activity.class);
                    startActivity(intent);
                }
                if (item == "Kitchen Appliances") {
                    Intent intent = new Intent(service_activity.this, kitchenappliances_activity.class);
                    startActivity(intent);
                }

            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;


    }
}
