package com.example.hp.electrician.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.electrician.R;
import com.example.hp.electrician.activity.user.user.cart_activity;
import com.example.hp.electrician.pojo.OrderInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomAdapterOrder extends ArrayAdapter {

    int number;
    Context context;
    int resource;
    //String type;
    ArrayList<OrderInfo> arrayList;
    int t;
    DatabaseReference databaseReference;

    public CustomAdapterOrder(Context context, int resource, ArrayList<OrderInfo> arrayList) {
        super(context, resource, arrayList);
        //this.type = type;
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        final OrderInfo orderInfo = arrayList.get(position);
        final String userId = context.getSharedPreferences("myprf",Context.MODE_PRIVATE).getString("id",null);
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource,null,false);
        databaseReference = FirebaseDatabase.getInstance().getReference("orderinfo").child(userId).child("currentOrders");
        TextView itemsselected = view.findViewById(R.id.itemsselected);
        TextView price1 = view.findViewById(R.id.price1);
        TextView no_of_items = view.findViewById(R.id.no_of_items);
        ImageButton delete_items = view.findViewById(R.id.delete_item);

        if (orderInfo.getNo_of_items() > 0) {
            itemsselected.setText(orderInfo.getService1());
            price1.setText(String.valueOf(orderInfo.getPrice1()));
            no_of_items.setText(String.valueOf(orderInfo.getNo_of_items()));
        }
//        int i= orderInfo.getNo_of_items();
//        if(i==1) {
//            itemsselected.setText(orderInfo.getService1());
//            price1.setText(orderInfo.getPrice1());
//            no_of_items.setText(orderInfo.getNo_of_items());
//        }
//        else
//        {
//            itemsselected.setText(orderInfo.getService1());
//           p= Integer.parseInt(orderInfo.getPrice1());
//           t=p*i;
//           orderInfo.setPrice1("t");
//           price1.setText((p*i)+"");
//            no_of_items.setText(orderInfo.getNo_of_items());
//        }
        delete_items.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Item");
                builder.setMessage("Are You Sure You want to Delete?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference.child(orderInfo.getId()).setValue(null);
                        //orderInfo.setId(null);
//                        number = orderInfo.getNo_of_items();
//                        number = number - number;
//                        orderInfo.setNo_of_items(number);
                       // update(orderInfo.getId());
                        Toast.makeText(context,"Item Deleted",Toast.LENGTH_SHORT).show();
                       notifyDataSetChanged();
                }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"Not Deleted",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        return view;
    }
}
