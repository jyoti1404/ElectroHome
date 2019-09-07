package com.example.hp.electrician.activity.user.pcservices;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.electrician.R;
import com.example.hp.electrician.activity.user.repairs.acrepair_activity;
import com.example.hp.electrician.activity.user.repairs.fan_activity;
import com.example.hp.electrician.activity.user.user.cart_activity;
import com.example.hp.electrician.pojo.OrderInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class pcrepair_activity extends AppCompatActivity {

    LinearLayout llac;
    int i=0,j=0,k=0,l=0;
    TextView add1,add2,add3,add4;
    Button pc_repair, lap_repair, pc_repairn, lap_repairn;
    TextView service3,service4,price3,price4;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcrepair_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        llac =  findViewById(R.id.llac);
        pc_repair = findViewById(R.id.button_pcrepair);
        lap_repair =  findViewById(R.id.button_laprepair);
        pc_repairn = findViewById(R.id.button_pcrepairn);
        lap_repairn = findViewById(R.id.button_laprepairn);
        service3 = findViewById(R.id.service3);
        price3 = findViewById(R.id.price3);
        service4 = findViewById(R.id.service4);
        price4 = findViewById(R.id.price4);
        add3 = findViewById(R.id.add3);
        add4 = findViewById(R.id.add4);
        SharedPreferences sharedPreferences = getSharedPreferences("myprf", MODE_PRIVATE);
        final String id = sharedPreferences.getString("id", null);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("orderinfo").child(id).child("currentOrders");
        setTitle("PC/Laptop Repairs");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    OrderInfo orderInfo = dataSnapshot1.getValue(OrderInfo.class);
                    if (orderInfo.getId() == "pcrepair") {
                        if (orderInfo.getNo_of_items() == 0) {
                            add3.setText("Item");
                        } else {
                            add3.setText("" + dataSnapshot.child("pcrepair").child("no_of_items").getValue(Integer.class));
                        }
                    }

                    if (orderInfo.getId() == "laprepair") {
                        if (orderInfo.getNo_of_items() == 0) {
                            add4.setText("Item");
                        } else {
                            add4.setText("" + dataSnapshot.child("laprepair").child("no_of_items").getValue(Integer.class));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(pcrepair_activity.this, "Error", Toast.LENGTH_SHORT).show();
            }

        });


        llac.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(pcrepair_activity.this, cart_activity.class);
                startActivity(intent);
            }
        });

        pc_repair.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String s1 = service3.getText().toString();
                int p1 = Integer.parseInt(price3.getText().toString());

                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId("pcrepair");
                orderInfo.setService1(s1);
                i++;
                orderInfo.setNo_of_items(i);
                orderInfo.setPrice1(i*p1);
                databaseReference.child("pcrepair").setValue(orderInfo);
                add3.setText("" + orderInfo.getNo_of_items());

            }
        });

        lap_repair.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String s1 = service4.getText().toString();
                int p1 = Integer.parseInt(price4.getText().toString());
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId("laprepair");
                orderInfo.setService1(s1);
                j++;
                orderInfo.setNo_of_items(j);
                orderInfo.setPrice1(j*p1);
                databaseReference.child("laprepair").setValue(orderInfo);
                add4.setText("" + orderInfo.getNo_of_items());
            }
        });

        pc_repairn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i > 0) {
                    OrderInfo orderInfo = new OrderInfo();
                    String s1 = service3.getText().toString();
                    int p1 = Integer.parseInt(price3.getText().toString());
                    orderInfo.setId("pcrepair");
                    orderInfo.setService1(s1);
                    i--;
                    orderInfo.setNo_of_items(i);
                    orderInfo.setPrice1(i * p1);
                    databaseReference.child("pcrepair").setValue(orderInfo);
                    add3.setText("" + orderInfo.getNo_of_items());
                    if (i == 0) {
                        databaseReference.child("pcrepair").removeValue();
                    }
                } else {
                    databaseReference.child("pcrepair").removeValue();
                    add3.setText("Item");
                    Toast.makeText(pcrepair_activity.this, "You cannot subtract items lesser than 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

            lap_repairn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    OrderInfo orderInfo = new OrderInfo();
                    if (j > 0) {
                        String s1 = service4.getText().toString();
                        int p1 = Integer.parseInt(price4.getText().toString());
                        orderInfo.setId("laprepair");
                        orderInfo.setService1(s1);

                        j--;
                        orderInfo.setNo_of_items(j);
                        orderInfo.setPrice1(j * p1);
                        databaseReference.child("laprepair").setValue(orderInfo);
                        add4.setText("" + orderInfo.getNo_of_items());
                        if (j == 0) {
                            databaseReference.child("laprepair").removeValue();
                        }
                    } else {
                        databaseReference.child("laprepair").removeValue();
                        add4.setText("Item");
                        Toast.makeText(pcrepair_activity.this, "You cannot subtract items lesser than 0", Toast.LENGTH_SHORT).show();
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
