package com.example.hp.electrician.activity.user.Cart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.electrician.R;
import com.example.hp.electrician.activity.user.user.paying_activity;
import com.example.hp.electrician.adapter.CustomAdapterOrder;
import com.example.hp.electrician.pojo.OrderInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class finalcart_activity extends AppCompatActivity {

    private static final String TAG = "finalcart_activity";

    ImageButton delete_item;
    int sum;
    Button paynow;
    TextView itemsselected, price1, total;
    ListView listView;
    ArrayList<OrderInfo> arrayList = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalcart_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        delete_item = findViewById(R.id.delete_item);
        paynow=findViewById(R.id.paynow);
        listView = findViewById(R.id.listview);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("orderinfo");
        total = findViewById(R.id.tprice);
        sharedPreferences = getSharedPreferences("myprf",MODE_PRIVATE);
        final String id = sharedPreferences.getString("id",null);

        setTitle("Cart");

        databaseReference.child(id).child("currentOrders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d("in on data set changed", "onDataChange: " + dataSnapshot.hasChildren());
                if(dataSnapshot.hasChildren()){
                    paynow.setVisibility(View.VISIBLE);
                }else{
                    paynow.setVisibility(View.GONE);
                }
                sum=0;
                arrayList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    OrderInfo orderInfo = dataSnapshot1.getValue(OrderInfo.class);
                    arrayList.add(orderInfo);
                    int s = Integer.parseInt(String.valueOf(orderInfo.getPrice1()));
                    sum = sum + s;
                }
                CustomAdapterOrder customAdapterOrder = new CustomAdapterOrder(finalcart_activity.this,R.layout.order_listview,arrayList);
                listView.setAdapter(customAdapterOrder);
                total.setText("Total: " + sum);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(finalcart_activity.this,""+databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        paynow.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                moveRecord(FirebaseDatabase.getInstance().getReference("orderinfo").child(id).child("currentOrders"),
                        FirebaseDatabase.getInstance().getReference("orderinfo").child(id).child("previousOrders").push());

                Intent intent=new Intent(finalcart_activity.this, paying_activity.class);
                startActivity(intent);
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
    private void moveRecord(final DatabaseReference fromPath, final DatabaseReference toPath) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                toPath.setValue(dataSnapshot.getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isComplete()) {
                            Log.d(TAG, "Success!");
                            fromPath.removeValue();
                        } else {
                            Log.d(TAG, "Copy failed!");
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        fromPath.addListenerForSingleValueEvent(valueEventListener);
    }


}
