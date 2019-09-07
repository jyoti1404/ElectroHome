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
import com.example.hp.electrician.activity.user.user.cart_activity;
import com.example.hp.electrician.pojo.OrderInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class antivirus_activity extends AppCompatActivity {

    Button button_anti, button_antin;
    TextView add1,add2,add3,add4;
    LinearLayout llac;
    int i=0;
    TextView service3,price3;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antivirus_activity);

        button_anti = findViewById(R.id.antivirus);
        button_antin = findViewById(R.id.antivirusn);
        service3 = findViewById(R.id.service3);
        price3 = findViewById(R.id.price3);
        llac = findViewById(R.id.llac);
        add3 = findViewById(R.id.add3);
        add4 = findViewById(R.id.add4);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPreferences = getSharedPreferences("myprf", MODE_PRIVATE);
        final String id = sharedPreferences.getString("id", null);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("orderinfo").child(id).child("currentOrders");
        setTitle("Antivirus");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    OrderInfo orderInfo = dataSnapshot1.getValue(OrderInfo.class);
                    if (orderInfo.getId() == "anti") {
                        if (orderInfo.getNo_of_items() == 0) {
                            add3.setText("Item");
                        } else {
                            add3.setText("" + dataSnapshot.child("anti").child("no_of_items").getValue(Integer.class));
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(antivirus_activity.this, "Error", Toast.LENGTH_SHORT).show();
            }

        });


        llac.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(antivirus_activity.this, cart_activity.class);
                startActivity(intent);
            }
        });

        button_anti.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String s1 = service3.getText().toString();
                int p1 = Integer.parseInt(price3.getText().toString());

                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId("anti");
                orderInfo.setService1(s1);
                i++;
                orderInfo.setNo_of_items(i);
                orderInfo.setPrice1(i*p1);
                databaseReference.child("anti").setValue(orderInfo);
                add3.setText("" + orderInfo.getNo_of_items());

            }
        });
        button_antin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i > 0) {
                    OrderInfo orderInfo = new OrderInfo();
                    String s1 = service3.getText().toString();
                    int p1 = Integer.parseInt(price3.getText().toString());
                    orderInfo.setId("anti");
                    orderInfo.setService1(s1);
                    i--;
                    orderInfo.setNo_of_items(i);
                    orderInfo.setPrice1(i * p1);
                    databaseReference.child("anti").setValue(orderInfo);
                    add3.setText("" + orderInfo.getNo_of_items());
                    if (i == 0) {
                        databaseReference.child("anti").removeValue();
                    }
                } else {
                    databaseReference.child("anti").removeValue();
                    add3.setText("Item");
                    Toast.makeText(antivirus_activity.this, "You cannot subtract items lesser than 0", Toast.LENGTH_SHORT).show();
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
