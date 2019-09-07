package com.example.hp.electrician.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hp.electrician.R;
import com.example.hp.electrician.pojo.CustomerPojo;
import com.example.hp.electrician.pojo.OrderInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomAdapterReq extends ArrayAdapter {

    Context context;
    String type;
    int resource;
   ArrayList<CustomerPojo> arrayList;
   // ArrayList<OrderInfo> arrayList;
    DatabaseReference databaseReference;

    public CustomAdapterReq( Context context, int resource, ArrayList<CustomerPojo> arrayList) {
        super(context, resource, arrayList);

        this.type =type;
        this.context= context;
        this.resource = resource;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

//        OrderInfo orderInfo = arrayList.get(position);
//        String userId = context.getSharedPreferences("myprf",Context.MODE_PRIVATE).getString("id",null);
//        databaseReference = FirebaseDatabase.getInstance().getReference("orderinfo").child(userId);
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource, null, false);
//
//        TextView cust_name = view.findViewById(R.id.cust_name);
//        TextView cust_mobile = view.findViewById(R.id.cust_mobile);
//
//        cust_name.setText(orderInfo.getService1());
//        cust_mobile.setText(orderInfo.getPrice1() + "");

        TextView cust_name = view.findViewById(R.id.cust_name);
        TextView cust_mobile = view.findViewById(R.id.cust_mobile);
        TextView cust_town = view.findViewById(R.id.cust_town);
        TextView cust_state = view.findViewById(R.id.cust_state);
        TextView cust_street = view.findViewById(R.id.cust_street);
        TextView cust_pincode = view.findViewById(R.id.cust_pincode);

        CustomerPojo customerPojo = arrayList.get(position);
        cust_name.setText("Customer Name: " + customerPojo.getName());
        cust_mobile.setText("Customer Mobile: " + customerPojo.getMobileno());
        cust_town.setText("Customer City: " + customerPojo.getTown());
        cust_state.setText("Customer State: " + customerPojo.getState());
        cust_street.setText("Customer Street: " + customerPojo.getStreet());
        cust_pincode.setText("Customer Pin: " + customerPojo.getPincode());

        return view;
    }
}
