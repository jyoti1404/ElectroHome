package com.example.hp.electrician.activity.user.repairs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hp.electrician.R;

public class repair_activity extends AppCompatActivity {

    ListView repair_listview;
    String arr[]={"AC Repair","Fan","Lights","Switches & Sockets","Mcb Fuse","Geyser","Television","Refrigerator","Washing Machine"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_activity);

        repair_listview = findViewById(R.id.repair_listview);

        setTitle("Repairs");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter arrayAdapter=new ArrayAdapter(repair_activity.this,android.R.layout.simple_list_item_1,arr);
        repair_listview.setAdapter(arrayAdapter);

        repair_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = repair_listview.getAdapter().getItem(position).toString();
                if (item == "AC Repair"){
                    Intent intent = new Intent(repair_activity.this,acrepair_activity.class);
                    startActivity(intent);
                }
                if (item == "Fan"){
                    Intent intent = new Intent(repair_activity.this, fan_activity.class);
                    startActivity(intent);
                }
                if (item == "Lights"){
                    Intent intent = new Intent(repair_activity.this, lights_activity.class);
                    startActivity(intent);
                }
                if(item == "Switches & Sockets"){
                    Intent intent = new Intent(repair_activity.this,switchsockets_activity.class);
                    startActivity(intent);
                }
                if (item == "Mcb Fuse") {
                    Intent intent = new Intent(repair_activity.this, mcbfuse_activity.class);
                    startActivity(intent);
                }
                if (item == "Geyser") {
                    Intent intent = new Intent(repair_activity.this, geyser_activity.class);
                    startActivity(intent);
                }
                if (item == "Television") {
                    Intent intent = new Intent(repair_activity.this, tv_activity.class);
                    startActivity(intent);
                }
                if (item == "Refrigerator") {
                    Intent intent = new Intent(repair_activity.this, fridge_activity.class);
                    startActivity(intent);
                }
                if (item == "Washing Machine") {
                    Intent intent = new Intent(repair_activity.this, washingmachine_activity.class);
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
