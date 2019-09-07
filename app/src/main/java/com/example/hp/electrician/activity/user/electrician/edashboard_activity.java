package com.example.hp.electrician.activity.user.electrician;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.electrician.R;
import com.example.hp.electrician.activity.user.user.aboutus;
import com.example.hp.electrician.activity.user.user.cart_activity;
import com.example.hp.electrician.activity.user.user.changepassword;
import com.example.hp.electrician.activity.user.user.dashboard;
import com.example.hp.electrician.activity.user.user.details_activity;
import com.example.hp.electrician.activity.user.user.login_activity;
import com.example.hp.electrician.activity.user.user.viewprofile;
import com.example.hp.electrician.adapter.CustomAdapterOrder;
import com.example.hp.electrician.adapter.CustomAdapterReq;
import com.example.hp.electrician.pojo.CustomerPojo;
import com.example.hp.electrician.pojo.OrderInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class edashboard_activity extends AppCompatActivity {

    String type;
    private static final String TAG = "edashboard_activity";
    ListView request_list;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
   ArrayList<CustomerPojo> arrayList = new ArrayList<>();
    //ArrayList<OrderInfo> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edashboard_activity);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        request_list = findViewById(R.id.request_list);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("customerinfo");
      //  SharedPreferences sharedPreferences = getSharedPreferences("myprf", MODE_PRIVATE);
        //final String id = sharedPreferences.getString("id",null);


        databaseReference.child("pendingRequests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

//                    OrderInfo orderInfo = dataSnapshot1.getValue(OrderInfo.class);
//                    arrayList.add(orderInfo);
                    CustomerPojo customerPojo = dataSnapshot1.getValue(CustomerPojo.class);
                    arrayList.add(customerPojo);
                }

                CustomAdapterReq customAdapterReq = new CustomAdapterReq(edashboard_activity.this,R.layout.request_list,arrayList);
                request_list.setAdapter(customAdapterReq);
                //Toast.makeText(edashboard_activity.this,"Requests",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(edashboard_activity.this,"Error",Toast.LENGTH_LONG).show();

            }
        });
        request_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                final CustomerPojo customerPojo = arrayList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(edashboard_activity.this);
                builder.setTitle("Accept Request");
                builder.setMessage("Are You Sure You want to Accept?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //customerPojo.setStatus(true);
                        moveRecord(FirebaseDatabase.getInstance().getReference("customerinfo").child("pendingRequests"),
                                FirebaseDatabase.getInstance().getReference("customerinfo").child("acceptRequests").push());

                       // databaseReference.child(customerPojo.getId()).setValue(null);
                        //startActivity(new Intent(edashboard_activity.this,.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));

                        Intent intent = new Intent();
                        PendingIntent pendingIntent = PendingIntent.getActivity(edashboard_activity.this,0,intent,0);
                        Notification noti = new Notification.Builder(edashboard_activity.this)
                                .setTicker("Electrician")
                                .setContentTitle("Accept Request")
                                .setContentText("Request has been accepted by you. Now you are ready to deliver services..")
                                .setSmallIcon(R.drawable.electricianlogo)
                                .setContentIntent(pendingIntent).getNotification();

                        noti.flags = Notification.FLAG_AUTO_CANCEL;
                        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                        nm.notify(0,noti);
                        Toast.makeText(edashboard_activity.this,"Request Accepted",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(edashboard_activity.this,"Not Accepted",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

            }
        });


    }
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.electrician_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.menu_profile:
                //Toast.makeText(edashboard_activity.this,"View Profile",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(edashboard_activity.this, eviewprofile.class);
                startActivity(intent);
                break;

            case R.id.view_requests:
                //Toast.makeText(edashboard_activity.this,"Change Password",Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(edashboard_activity.this, eviewrequests.class);
                startActivity(intent4);
                break;


//            case R.id.menu_cpassword:
//                //Toast.makeText(edashboard_activity.this,"Change Password",Toast.LENGTH_SHORT).show();
//                Intent intent1 = new Intent(edashboard_activity.this, echangepassword.class);
//                startActivity(intent1);
//                break;

            case R.id.logout:
                //databaseReference.removeValue();
                SharedPreferences sharedPreferences = getSharedPreferences("myprf",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(edashboard_activity.this,"Log out",Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(edashboard_activity.this, elogin_activity.class);
                startActivity(intent3);
                finish();
                break;
        }

        return true;
    }
    @Override
    public void onBackPressed() {
        finish();
        //startActivity(new Intent(edashboard_activity.this,dashboard.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
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
