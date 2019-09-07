package com.example.hp.electrician.activity.user.electrician;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.electrician.R;
import com.example.hp.electrician.adapter.CustomAdapterReq;
import com.example.hp.electrician.pojo.CustomerPojo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class eviewrequests extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ArrayList<CustomerPojo> arrayList = new ArrayList<>();
    ListView request_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eviewrequests);

        request_list = findViewById(R.id.request_list);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("customerinfo");

        setTitle("Requests");

        databaseReference.child("acceptRequests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {

//                    OrderInfo orderInfo = dataSnapshot1.getValue(OrderInfo.class);
//                    arrayList.add(orderInfo);
                        CustomerPojo customerPojo = dataSnapshot2.getValue(CustomerPojo.class);
                        arrayList.add(customerPojo);
                    }
                }

                CustomAdapterReq customAdapterReq = new CustomAdapterReq(eviewrequests.this,R.layout.request_list,arrayList);
                request_list.setAdapter(customAdapterReq);
                Toast.makeText(eviewrequests.this,"Requests",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(eviewrequests.this,"Error",Toast.LENGTH_LONG).show();

            }
        });

    }
}
