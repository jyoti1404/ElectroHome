package com.example.hp.electrician.activity.user.repairs;

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

public class lights_activity extends AppCompatActivity {

    LinearLayout llac;
    int i=0,j=0,k=0,l=0;
    Button buttonac3,buttonac4, buttonac3n, buttonac4n;
    TextView add1,add2,add3,add4;
    TextView service3,service4,price3,price4;
    FirebaseDatabase database;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        llac = (LinearLayout) findViewById(R.id.llac);
        buttonac3 = (Button) findViewById(R.id.buttonac3);
        buttonac4 = (Button) findViewById(R.id.buttonac4);
        buttonac3n = findViewById(R.id.buttonac3n);
        buttonac4n = findViewById(R.id.buttonac4n);
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

        setTitle("Lights Repairs");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    OrderInfo orderInfo = dataSnapshot1.getValue(OrderInfo.class);
                    if (orderInfo.getId() == "lights3") {
                        if (orderInfo.getNo_of_items() == 0) {
                            add3.setText("Item");
                        } else {
                            add3.setText("" + dataSnapshot.child("lights3").child("no_of_items").getValue(Integer.class));
                        }
                    }

                    if (orderInfo.getId() == "lights4") {
                        if (orderInfo.getNo_of_items() == 0) {
                            add4.setText("Item");
                        } else {
                            add4.setText("" + dataSnapshot.child("lights4").child("no_of_items").getValue(Integer.class));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(lights_activity.this, "Error", Toast.LENGTH_SHORT).show();
            }

        });


        llac.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(lights_activity.this, cart_activity.class);
                startActivity(intent);
            }
        });

        buttonac3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String s1 = service3.getText().toString();
                int p1 = Integer.parseInt(price3.getText().toString());
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId("lights3");
                orderInfo.setService1(s1);
                i++;
                orderInfo.setNo_of_items(i);
                orderInfo.setPrice1(i*p1);
                databaseReference.child("lights3").setValue(orderInfo);
                add3.setText("" + orderInfo.getNo_of_items());

            }
        });

        buttonac4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String s1 = service4.getText().toString();
                int p1 = Integer.parseInt(price4.getText().toString());
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId("lights4");
                orderInfo.setService1(s1);
                j++;
                orderInfo.setNo_of_items(j);
                orderInfo.setPrice1(j*p1);
                databaseReference.child("lights4").setValue(orderInfo);
                add4.setText("" + orderInfo.getNo_of_items());

            }
        });

        buttonac4n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderInfo orderInfo = new OrderInfo();
                if (j > 0) {
                    String s1 = service4.getText().toString();
                    int p1 = Integer.parseInt(price4.getText().toString());
                    orderInfo.setId("lights4");
                    orderInfo.setService1(s1);

                    j--;
                    orderInfo.setNo_of_items(j);
                    orderInfo.setPrice1(j * p1);
                    databaseReference.child("lights4").setValue(orderInfo);
                    add4.setText("" + orderInfo.getNo_of_items());
                    if(j==0)
                    {
                        databaseReference.child("lights4").removeValue();
                    }
                } else {
                    databaseReference.child("lights4").removeValue();
                    add4.setText("Item");
                    Toast.makeText(lights_activity.this, "You cannot subtract items lesser than 0", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonac3n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i > 0) {
                    OrderInfo orderInfo = new OrderInfo();
                    String s1 = service3.getText().toString();
                    int p1 = Integer.parseInt(price3.getText().toString());
                    orderInfo.setId("lights3");
                    orderInfo.setService1(s1);
                    i--;
                    orderInfo.setNo_of_items(i);
                    orderInfo.setPrice1(i * p1);
                    databaseReference.child("lights3").setValue(orderInfo);
                    add3.setText("" + orderInfo.getNo_of_items());
                    if(i==0)
                    {
                        databaseReference.child("lights3").removeValue();
                    }
                } else {
                    databaseReference.child("lights3").removeValue();
                    add3.setText("Item");
                    Toast.makeText(lights_activity.this, "You cannot subtract items lesser than 0", Toast.LENGTH_SHORT).show();
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
