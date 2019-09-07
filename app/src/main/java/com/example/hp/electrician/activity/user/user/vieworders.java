package com.example.hp.electrician.activity.user.user;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.electrician.R;
import com.example.hp.electrician.adapter.CustomAdapterOrder;
import com.example.hp.electrician.adapter.CustomYourOrder;
import com.example.hp.electrician.pojo.OrderInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class vieworders extends AppCompatActivity {

    ArrayList<OrderInfo> arrayList = new ArrayList<>();
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieworders);

        listView = findViewById(R.id.listview_yourorder);
        database = FirebaseDatabase.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences("myprf", MODE_PRIVATE);
        final String id = sharedPreferences.getString("id",null);

        databaseReference = database.getReference("orderinfo").child(id).child("previousOrders");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Orders");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    for(DataSnapshot d1:d.getChildren()){
                        OrderInfo orderInfo = d1.getValue(OrderInfo.class);
                        arrayList.add(orderInfo);
                    }
                }
                CustomYourOrder customYourOrder = new CustomYourOrder(vieworders.this,R.layout.listview_yourorders,arrayList);
                listView.setAdapter(customYourOrder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
