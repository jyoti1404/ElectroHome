package com.example.hp.electrician.activity.user.user;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.electrician.R;
import com.example.hp.electrician.pojo.CustomerPojo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class details_activity extends AppCompatActivity {
//    private static final String TAG = "cart_activity";
    Button confirm;
    boolean flag= true;
    EditText name,mobileno,town,state,street,pincode;
    FirebaseDatabase database;
    DatabaseReference databaseReference,databaseReference1;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activity);

        confirm=(Button)findViewById(R.id.confirm);
        name = findViewById(R.id.name);
        mobileno = findViewById(R.id.mobileno);
        town = findViewById(R.id.town);
        state = findViewById(R.id.state);
        street = findViewById(R.id.street);
        pincode = findViewById(R.id.pincode);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("customerinfo").child("pendingRequests");
        databaseReference1 = database.getReference();

        setTitle("Details");

        confirm.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                String id = databaseReference.push().getKey();
                String cname = name.getText().toString();
                String cmobileno = mobileno.getText().toString();
                String ctown = town.getText().toString();
                String cstreet = street.getText().toString();
                String cstate = state.getText().toString();
                String cpincode = pincode.getText().toString();

                if (name.getText().toString().equals("")){
                    Toast.makeText(details_activity.this,"Please Enter Name",Toast.LENGTH_SHORT).show();
                    flag =false;
                }
                if (mobileno.getText().toString().equals("") ) {
                    Toast.makeText(details_activity.this, "Please Enter Mobile No", Toast.LENGTH_LONG).show();
                    flag = false;
                }
                if (mobileno.length()<10){
                    Toast.makeText(details_activity.this, "Number should not be less than 10  ", Toast.LENGTH_LONG).show();
                    flag = false;

                }
                if (town.getText().toString().equals("")){
                    Toast.makeText(details_activity.this,"Please Enter Your Address",Toast.LENGTH_SHORT).show();
                    flag =false;
                }
                if (street.getText().toString().equals("")){
                    Toast.makeText(details_activity.this,"Please Enter Your Address",Toast.LENGTH_SHORT).show();
                    flag =false;
                }if (state.getText().toString().equals("")){
                    Toast.makeText(details_activity.this,"Please Enter Your Address",Toast.LENGTH_SHORT).show();
                    flag =false;
                }
                if (pincode.getText().toString().equals("") ){
                    Toast.makeText(details_activity.this,"Please Enter Valid Pincode",Toast.LENGTH_SHORT).show();
                    flag =false;
                }
                if (pincode.length() < 6){
                    Toast.makeText(details_activity.this,"Please Enter Valid Pincode",Toast.LENGTH_SHORT).show();
                    flag =false;
                }

                if (flag == true) {
                    CustomerPojo customerPojo = new CustomerPojo();
                    customerPojo.setId(id);
                    customerPojo.setName(cname);
                    customerPojo.setMobileno(cmobileno);
                    customerPojo.setTown(ctown);
                    customerPojo.setStreet(cstreet);
                    customerPojo.setState(cstate);
                    customerPojo.setPincode(cpincode);
                    // customerPojo.setStatus(false);
                    databaseReference.child(id).setValue(customerPojo);
                    Toast.makeText(details_activity.this, "Details Stored", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent();
                    PendingIntent pendingIntent = PendingIntent.getActivity(details_activity.this, 0, intent, 0);
                    Notification noti = new Notification.Builder(details_activity.this)
                            .setTicker("Electrician")
                            .setContentTitle("Confirmation")
                            .setContentText("Your order has been accepted...")
                            .setSmallIcon(R.drawable.electricianlogo)
                            .setContentIntent(pendingIntent).getNotification();

                    noti.flags = Notification.FLAG_AUTO_CANCEL;
                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(0, noti);
                    Intent intent1=new Intent(details_activity.this,success_activity.class);
                    startActivity(intent1);
                    finish();

                }
//                sharedPreferences = getSharedPreferences("myprf",MODE_PRIVATE);
//                String oid = sharedPreferences.getString("id",null);

//                if (cname != null && cmobileno != null && (!ctown.equals(null)) && (!cstate.equals(null )) && (!cstreet.equals(null))  && (!cpincode.equals(null))) {
//
//                }
//                {
//                    Toast.makeText(details_activity.this,"plaese fill all the details",Toast.LENGTH_LONG).show();
//                }

            }
        });
    }

}
