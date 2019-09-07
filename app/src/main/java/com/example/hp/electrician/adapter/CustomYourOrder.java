package com.example.hp.electrician.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hp.electrician.R;
import com.example.hp.electrician.pojo.OrderInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomYourOrder extends ArrayAdapter {

    Context context;
    int resource;
    //String type;
    ArrayList<OrderInfo> arrayList;
    //int t;
    DatabaseReference databaseReference;


    public CustomYourOrder(Context context, int resource, ArrayList<OrderInfo> arrayList) {
        super(context, resource, arrayList);

        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final OrderInfo orderInfo = arrayList.get(position);
        final String userId = context.getSharedPreferences("myprf", Context.MODE_PRIVATE).getString("id", null);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource, null, false);
       // databaseReference = FirebaseDatabase.getInstance().getReference("orderinfo").child(userId).child("previousOrders");
        TextView itemsselected = view.findViewById(R.id.itemsselected);
        TextView price1 = view.findViewById(R.id.price1);
        TextView no_of_items = view.findViewById(R.id.no_of_items);
        // ImageButton delete_items = view.findViewById(R.id.delete_item);

        itemsselected.setText(orderInfo.getService1());
        price1.setText(String.valueOf(orderInfo.getPrice1()));
        no_of_items.setText(String.valueOf(orderInfo.getNo_of_items()));

//        4
        return view;
    }
}
