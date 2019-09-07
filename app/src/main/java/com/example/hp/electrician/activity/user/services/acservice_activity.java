package com.example.hp.electrician.activity.user.services;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.electrician.R;
import com.example.hp.electrician.activity.user.user.cart_activity;
import com.example.hp.electrician.pojo.OrderInfo;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class acservice_activity extends AppCompatActivity {

    String  type;
    LinearLayout llac;
    int i=0,j=0,k=0,l=0, h=0;
    int p,q,r,s;
    String a=null;
    Button buttonac3,buttonac4,buttonac1,buttonac2,buttonac1n,buttonac2n,buttonac3n,buttonac4n;
    TextView service1,price1, service2,price2,service3,price3,service4,price4;
    TextView add1,add2,add3,add4;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acservice_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        llac = (LinearLayout) findViewById(R.id.llac);
        buttonac3 = (Button) findViewById(R.id.buttonac3);
        buttonac4 = (Button) findViewById(R.id.buttonac4);
        buttonac2 = (Button) findViewById(R.id.buttonac2);
        buttonac1 = (Button) findViewById(R.id.buttonac1);
        buttonac1n = findViewById(R.id.buttonac1n);
        buttonac2n = findViewById(R.id.buttonac2n);
        buttonac3n = findViewById(R.id.buttonac3n);
        buttonac4n = findViewById(R.id.buttonac4n);
        service1 = findViewById(R.id.service1);
        price1 = findViewById(R.id.price1);
        service2 = findViewById(R.id.service2);
        price2 = findViewById(R.id.price2);
        service3 = findViewById(R.id.service3);
        price3 = findViewById(R.id.price3);
        service4 = findViewById(R.id.service4);
        price4 = findViewById(R.id.price4);
        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        add3 = findViewById(R.id.add3);
        add4 = findViewById(R.id.add4);
       // final OrderInfo orderInfo=new OrderInfo();
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        SharedPreferences sharedPreferences = getSharedPreferences("myprf", MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);

        setTitle("AC Services");
        Log.d("order info", "onCreate: user id" + id);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("orderinfo").child(id).child("currentOrders");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    OrderInfo orderInfo = dataSnapshot1.getValue(OrderInfo.class);
                    if (orderInfo.getId() == "ac1") {
                        if (orderInfo.getNo_of_items() == 0) {
                            add1.setText("Item");
                        } else {
                            add1.setText("" + dataSnapshot.child("ac1").child("no_of_items").getValue(Integer.class));
                        }
                    }
                    if (orderInfo.getId() == "ac2") {
                        if (orderInfo.getNo_of_items() == 0) {
                            add2.setText("Item");
                        } else {
                            add2.setText("" + dataSnapshot.child("ac2").child("no_of_items").getValue(Integer.class));
                        }
                    }
                    if (orderInfo.getId() == "ac3") {
                        if (orderInfo.getNo_of_items() == 0) {
                            add3.setText("Item");
                        } else {
                            add3.setText("" + dataSnapshot.child("ac3").child("no_of_items").getValue(Integer.class));
                        }
                    }

                    if (orderInfo.getId() == "ac4") {
                        if (orderInfo.getNo_of_items() == 0) {
                            add4.setText("Item");
                        } else {
                            add4.setText("" + dataSnapshot.child("ac4").child("no_of_items").getValue(Integer.class));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(acservice_activity.this,"Error", Toast.LENGTH_SHORT).show();
            }

        });

//        databaseReference.addValueEventListener(new ValueEventListener() {
//                                                    @Override
//                                                    public void onDataChange(DataSnapshot dataSnapshot) {
//
////                                                        buttonac1.setText("+" + dataSnapshot.child("ac1").child("no_of_items").getValue(Integer.class));
////                                                        buttonac2.setText("+" + dataSnapshot.child("ac2").child("no_of_items").getValue(Integer.class));
////                                                        buttonac3.setText("+" + dataSnapshot.child("ac3").child("no_of_items").getValue(Integer.class));
////                                                        buttonac4.setText("+" + dataSnapshot.child("ac4").child("no_of_items").getValue(Integer.class))
////
////            a = String.valueOf(dataSnapshot.child("ac1").child("no_of_items").getValue(Integer.class));
////            if(!a.equals(null))
////            {
////               buttonac1.setText("" + a);
////            }
////            else
////            {
////                buttonac1.setText("+");
////            }
//
//                                                    }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        llac.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(acservice_activity.this, cart_activity.class);
                // intent.putExtra("type", "ac");
                //  buttonac1.setText("Add +"+null);
                startActivity(intent);
            }
        });
//3 nodes listener dataSnaps.getchildren
        buttonac3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //databaseReference.removeValue();
                String s1 = service3.getText().toString();
                int p1 = Integer.parseInt(price3.getText().toString());
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId("ac3");
                orderInfo.setService1(s1);
                i++;
                orderInfo.setNo_of_items(i);
                p= i*p1;
                orderInfo.setPrice1(p);
                databaseReference.child("ac3").setValue(orderInfo);
                add3.setText("" + orderInfo.getNo_of_items());

            }
        });

        buttonac4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // databaseReference.removeValue();
                String s1 = service4.getText().toString();
                int p1 = Integer.parseInt(price4.getText().toString());

                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId("ac4");
                orderInfo.setService1(s1);
                j++;
                orderInfo.setNo_of_items(j);
                q = j*p1;
                orderInfo.setPrice1(q);
                databaseReference.child("ac4").setValue(orderInfo);
                add4.setText("" + orderInfo.getNo_of_items());

            }
        });
        buttonac2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // databaseReference.removeValue();
                String s1 = service2.getText().toString();
                int p1 = Integer.parseInt(price2.getText().toString());
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId("ac2");
                orderInfo.setService1(s1);
                k++;
                orderInfo.setNo_of_items(k);
                r = k*p1;
                orderInfo.setPrice1(r);
                databaseReference.child("ac2").setValue(orderInfo);
                add2.setText("" + orderInfo.getNo_of_items());
            }
        });
        buttonac1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  databaseReference.removeValue();
                String s1 = service1.getText().toString();
                int p1 = Integer.parseInt(price1.getText().toString());
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId("ac1");
                orderInfo.setService1(s1);
                l++;
                orderInfo.setNo_of_items(l);
                s = l*p1;
                orderInfo.setPrice1(s);
                databaseReference.child("ac1").setValue(orderInfo);
                add1.setText("" + orderInfo.getNo_of_items());

//                orderInfo.setNo_of_items(l);
//                l++;
//
//                buttonac1.setText("add +" + l);


//                if(l==1) {
//
//                    databaseReference.child(id).setValue(orderInfo);
//                }
//                else
//                {
//
//                    databaseReference.child(id).child(String.valueOf(l)).setValue(orderInfo);
//                }

            }
        });
        buttonac1n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderInfo orderInfo = new OrderInfo();
                if (l > 0) {

                    String s1 = service1.getText().toString();
                    int p1 = Integer.parseInt(price1.getText().toString());
                    orderInfo.setId("ac1");
                    orderInfo.setService1(s1);
                    l--;
                    orderInfo.setNo_of_items(l);
                    int p;
                    s = l*p1;
                    orderInfo.setPrice1(s);
                    databaseReference.child("ac1").setValue(orderInfo);
                    add1.setText("" + orderInfo.getNo_of_items());
                    if(l==0)
                    {
                        databaseReference.child("ac1").removeValue();
                    }
                } else {
                    databaseReference.child("ac1").removeValue();
                    add1.setText("Item");
                    Toast.makeText(acservice_activity.this, "You cannot subtract items lesser than 0", Toast.LENGTH_SHORT).show();
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
                    orderInfo.setId("ac4");
                    orderInfo.setService1(s1);

                    j--;
                    orderInfo.setNo_of_items(j);
                    int p;
                    q = j*p1;
                    orderInfo.setPrice1(q);
                    databaseReference.child("ac4").setValue(orderInfo);
                    add4.setText("" + orderInfo.getNo_of_items());
                    if(j==0)
                    {
                        databaseReference.child("ac4").removeValue();
                    }
                } else {
                    databaseReference.child("ac4").removeValue();
                    add4.setText("Item");
                    Toast.makeText(acservice_activity.this, "You cannot subtract items lesser than 0", Toast.LENGTH_SHORT).show();
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
                    orderInfo.setId("ac2");
                    orderInfo.setService1(s1);
                    k--;
                    orderInfo.setNo_of_items(k);
                    int p;
                    r = k*p1;
                    orderInfo.setPrice1(r);
                    databaseReference.child("ac2").setValue(orderInfo);
                    add2.setText("" + orderInfo.getNo_of_items());
                    if(k==0)
                    {
                        databaseReference.child("ac2").removeValue();
                    }
                } else {
                    databaseReference.child("ac2").removeValue();
                    add2.setText("Item");
                    Toast.makeText(acservice_activity.this, "You cannot subtract items lesser than 0", Toast.LENGTH_SHORT).show();
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
                    orderInfo.setId("ac3");
                    orderInfo.setService1(s1);
                    i--;
                    orderInfo.setNo_of_items(i);
                    int p;
                    p = i*p1;
                    orderInfo.setPrice1(p);
                    databaseReference.child("ac3").setValue(orderInfo);
                    add3.setText("" + orderInfo.getNo_of_items());
                    if(i==0)
                    {
                        databaseReference.child("ac3").removeValue();
                    }
                } else {
                    databaseReference.child("ac3").removeValue();
                    add3.setText("Item");
                    Toast.makeText(acservice_activity.this, "You cannot subtract items lesser than 0", Toast.LENGTH_SHORT).show();
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


