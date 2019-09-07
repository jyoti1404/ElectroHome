package com.example.hp.electrician.activity.user.services;

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

public class kitchenappliances_activity extends AppCompatActivity {

    LinearLayout llac;
    int i=0,j=0,k=0,l=0,m=0;
    Button buttonac3,buttonac4,buttonac1,buttonac2,buttonac5,buttonac1n,buttonac2n,buttonac3n,buttonac4n,buttonac5n;
    FirebaseDatabase database;
    TextView add1,add2,add3,add4,add5;
    DatabaseReference databaseReference;
    TextView service1,price1,service2,price2,service3,price3,service4,price4,service5,price5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchenappliances_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        llac = (LinearLayout) findViewById(R.id.llac);
        buttonac3 = (Button) findViewById(R.id.buttonac3);
        buttonac4 = (Button) findViewById(R.id.buttonac4);
        buttonac2 = (Button) findViewById(R.id.buttonac2);
        buttonac5 = findViewById(R.id.buttonac5);
        buttonac1 = (Button) findViewById(R.id.buttonac1);
        buttonac1n = findViewById(R.id.buttonac1n);
        buttonac2n = findViewById(R.id.buttonac2n);
        buttonac3n = findViewById(R.id.buttonac3n);
        buttonac4n = findViewById(R.id.buttonac4n);
        buttonac5n = findViewById(R.id.buttonac5n);
        service1 = findViewById(R.id.service1);
        price1 = findViewById(R.id.price1);
        service2 = findViewById(R.id.service2);
        price2 = findViewById(R.id.price2);
        service3 = findViewById(R.id.service3);
        price3 = findViewById(R.id.price3);
        service4 = findViewById(R.id.service4);
        price4 = findViewById(R.id.price4);
        service5 = findViewById(R.id.service5);
        price5 = findViewById(R.id.price5);
        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        add3 = findViewById(R.id.add3);
        add4 = findViewById(R.id.add4);
        add5 = findViewById(R.id.add5);
        SharedPreferences sharedPreferences = getSharedPreferences("myprf", MODE_PRIVATE);
        final String id = sharedPreferences.getString("id", null);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("orderinfo").child(id).child("currentOrders");

        setTitle("Kitchen Services");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    OrderInfo orderInfo = dataSnapshot1.getValue(OrderInfo.class);
                    if (orderInfo.getId() == "kitchen1") {
                        if (orderInfo.getNo_of_items() == 0) {
                            add1.setText("Item");
                        } else {
                            add1.setText("" + dataSnapshot.child("kitchen1").child("no_of_items").getValue(Integer.class));
                        }
                    }
                    if (orderInfo.getId() == "kitchen2") {
                        if (orderInfo.getNo_of_items() == 0) {
                            add2.setText("Item");
                        } else {
                            add2.setText("" + dataSnapshot.child("kitchen2").child("no_of_items").getValue(Integer.class));
                        }
                    }
                    if (orderInfo.getId() == "kitchen3") {
                        if (orderInfo.getNo_of_items() == 0) {
                            add3.setText("Item");
                        } else {
                            add3.setText("" + dataSnapshot.child("kitchen3").child("no_of_items").getValue(Integer.class));
                        }
                    }

                    if (orderInfo.getId() == "kitchen4") {
                        if (orderInfo.getNo_of_items() == 0) {
                            add4.setText("Item");
                        } else {
                            add4.setText("" + dataSnapshot.child("kitchen4").child("no_of_items").getValue(Integer.class));
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(kitchenappliances_activity.this,"Error", Toast.LENGTH_SHORT).show();
            }

        });

        llac.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(kitchenappliances_activity.this, cart_activity.class);
                startActivity(intent);
            }
        });

        buttonac3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String s1 = service3.getText().toString();
                int p1 = Integer.parseInt(price3.getText().toString());
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId("kitchen3");
                orderInfo.setService1(s1);
                i++;
                orderInfo.setNo_of_items(i);
                orderInfo.setPrice1(i*p1);
                databaseReference.child("kitchen3").setValue(orderInfo);
                add3.setText("" + orderInfo.getNo_of_items());

            }
        });

        buttonac4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String s1 = service4.getText().toString();
                int p1 = Integer.parseInt(price4.getText().toString());
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId("kitchen4");
                orderInfo.setService1(s1);
                j++;
                orderInfo.setNo_of_items(j);
                orderInfo.setPrice1(j*p1);
                databaseReference.child("kitchen4").setValue(orderInfo);
                add4.setText("" + orderInfo.getNo_of_items());

            }
        });
        buttonac2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String s1 = service2.getText().toString();
                int p1 = Integer.parseInt(price2.getText().toString());
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId("kitchen2");
                orderInfo.setService1(s1);
                k++;
                orderInfo.setNo_of_items(k);
                orderInfo.setPrice1(k*p1);
                databaseReference.child("kitchen2").setValue(orderInfo);
                add2.setText("" + orderInfo.getNo_of_items());
            }
        });
        buttonac1.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v) {
               // databaseReference.removeValue();
                String s1 = service1.getText().toString();
                int p1 = Integer.parseInt(price1.getText().toString());
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId("kitchen1");
                orderInfo.setService1(s1);

                l++;
                orderInfo.setNo_of_items(l);
                orderInfo.setPrice1(l*p1);
                databaseReference.child("kitchen1").setValue(orderInfo);
                add1.setText("" + orderInfo.getNo_of_items());

            }
        });
        buttonac5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String s1 = service5.getText().toString();
                //String p1 = price5.getText().toString();
                int p1 = Integer.parseInt(price5.getText().toString());
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId("kitchen5");
                orderInfo.setService1(s1);
               // orderInfo.setPrice1(p1);

                m++;
                orderInfo.setNo_of_items(m);
                orderInfo.setPrice1(m*p1);
                databaseReference.child("kitchen5").setValue(orderInfo);

                add5.setText("" + orderInfo.getNo_of_items());
            }
        });

        buttonac1n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderInfo orderInfo = new OrderInfo();
                if (l > 0) {

                    String s1 = service1.getText().toString();
                    int p1 = Integer.parseInt(price1.getText().toString());
                    orderInfo.setId("kitchen1");
                    orderInfo.setService1(s1);
                    l--;
                    orderInfo.setNo_of_items(l);
                    orderInfo.setPrice1(l * p1);
                    databaseReference.child("kitchen1").setValue(orderInfo);
                    add1.setText("" + orderInfo.getNo_of_items());
                    if(l==0)
                    {
                        databaseReference.child("kitchen1").removeValue();
                    }
                } else {
                    databaseReference.child("kitchen1").removeValue();
                    add1.setText("Item");
                    Toast.makeText(kitchenappliances_activity.this, "You cannot subtract items lesser than 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonac4n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderInfo orderInfo = new OrderInfo();
                if (j > 0) {
                    String s1 = service4.getText().toString();
                    int p1 = Integer.parseInt(price4.getText().toString());
                    orderInfo.setId("kitchen4");
                    orderInfo.setService1(s1);

                    j--;
                    orderInfo.setNo_of_items(j);
                    orderInfo.setPrice1(j * p1);
                    databaseReference.child("kitchen4").setValue(orderInfo);
                    add4.setText("" + orderInfo.getNo_of_items());
                    if(j==0)
                    {
                        databaseReference.child("kitchen4").removeValue();
                    }
                } else {
                    databaseReference.child("kitchen4").removeValue();
                    add4.setText("Item");
                    Toast.makeText(kitchenappliances_activity.this, "You cannot subtract items lesser than 0", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonac2n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderInfo orderInfo = new OrderInfo();
                if (k > 0) {
                    String s1 = service2.getText().toString();
                    int p1 = Integer.parseInt(price2.getText().toString());
                    orderInfo.setId("kitchen2");
                    orderInfo.setService1(s1);
                    k--;
                    orderInfo.setNo_of_items(k);
                    orderInfo.setPrice1(k * p1);
                    databaseReference.child("kitchen2").setValue(orderInfo);
                    add2.setText("" + orderInfo.getNo_of_items());
                    if(k==0)
                    {
                        databaseReference.child("kitchen2").removeValue();
                    }
                } else {
                    databaseReference.child("kitchen2").removeValue();
                    add2.setText("Item");
                    Toast.makeText(kitchenappliances_activity.this, "You cannot subtract items lesser than 0", Toast.LENGTH_SHORT).show();
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
                    orderInfo.setId("kitchen3");
                    orderInfo.setService1(s1);
                    i--;
                    orderInfo.setNo_of_items(i);
                    orderInfo.setPrice1(i * p1);
                    databaseReference.child("kitchen3").setValue(orderInfo);
                    add3.setText("" + orderInfo.getNo_of_items());
                    if(i==0)
                    {
                        databaseReference.child("kitchen3").removeValue();
                    }
                } else {
                    databaseReference.child("kitchen3").removeValue();
                    add3.setText("Item");
                    Toast.makeText(kitchenappliances_activity.this, "You cannot subtract items lesser than 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonac5n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i > 0) {
                    OrderInfo orderInfo = new OrderInfo();
                    String s1 = service3.getText().toString();
                    int p1 = Integer.parseInt(price3.getText().toString());
                    orderInfo.setId("kitchen5");
                    orderInfo.setService1(s1);
                    i--;
                    orderInfo.setNo_of_items(i);
                    orderInfo.setPrice1(i * p1);
                    databaseReference.child("kitchen5").setValue(orderInfo);
                    add3.setText("" + orderInfo.getNo_of_items());
                    if(i==0)
                    {
                        databaseReference.child("kitchen5").removeValue();
                    }
                } else {
                    databaseReference.child("kitchen5").removeValue();
                    add3.setText("Item");
                    Toast.makeText(kitchenappliances_activity.this, "You cannot subtract items lesser than 0", Toast.LENGTH_SHORT).show();
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
