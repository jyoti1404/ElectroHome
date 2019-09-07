package com.example.hp.electrician.activity.user.pcservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hp.electrician.R;

public class pcservices_activty extends AppCompatActivity {

    ListView pcservice_listview;
    String arr[]={"PC/Laptop Checking","PC/Laptop Repair","Antivirus"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcservices_activty);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("PC/Laptop Services");

        pcservice_listview = findViewById(R.id.pcservice_listview);
        ArrayAdapter arrayAdapter=new ArrayAdapter(pcservices_activty.this,android.R.layout.simple_list_item_1,arr);
        pcservice_listview.setAdapter(arrayAdapter);

        pcservice_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = pcservice_listview.getAdapter().getItem(position).toString();
                if (item == "PC/Laptop Checking"){
                    Intent intent = new Intent(pcservices_activty.this, pcchecking_activity.class);
                    startActivity(intent);
                }
                if(item == "PC/Laptop Repair"){
                    Intent intent = new Intent(pcservices_activty.this,pcrepair_activity.class);
                    startActivity(intent);
                }
                if (item == "Antivirus") {
                    Intent intent = new Intent(pcservices_activty.this, antivirus_activity.class);
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
