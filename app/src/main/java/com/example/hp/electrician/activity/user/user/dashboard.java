package com.example.hp.electrician.activity.user.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.hp.electrician.R;
import com.example.hp.electrician.activity.user.Cart.finalcart_activity;
import com.example.hp.electrician.activity.user.feedback.feedback_activity;
import com.example.hp.electrician.activity.user.help.help_activity;
import com.example.hp.electrician.activity.user.pcservices.pcservices_activty;
import com.example.hp.electrician.activity.user.repairs.repair_activity;
import com.example.hp.electrician.activity.user.services.service_activity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dashboard extends AppCompatActivity {

    LinearLayout dashboard_services, dashboard_repair, dashboard_pcservices, dashboard_bookings, dashboard_help, dashboard_feedback;
    SharedPreferences sharedPreferences;
    ViewFlipper viewFlipper;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        viewFlipper = findViewById(R.id.img);
        SharedPreferences sharedPreferences = getSharedPreferences("myprf",MODE_PRIVATE);
        //String profile = sharedPreferences.getString("profile", null);
        dashboard_services = findViewById(R.id.dashboard_services);
        dashboard_repair = findViewById(R.id.dashboard_repair);
        dashboard_pcservices = findViewById(R.id.dashboard_pcservices);
        dashboard_bookings = findViewById(R.id.dashboard_bookings);
        dashboard_help = findViewById(R.id.dashboard_help);
        dashboard_feedback = findViewById(R.id.dashboard_feedback);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("orderinfo");

        int image[]={R.drawable.ele4,R.drawable.e6,R.drawable.e11,R.drawable.e13,R.drawable.e7};
        for(int slide:image)
        {
            flipperImages(slide);
        }

        dashboard_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, service_activity.class );
                startActivity(intent);
            }
        });
        dashboard_repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, repair_activity.class );
                startActivity(intent);
            }
        });

        dashboard_pcservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, pcservices_activty.class );
                startActivity(intent);
            }
        });

        dashboard_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, finalcart_activity.class );
                startActivity(intent);
            }
        });

        dashboard_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, help_activity.class );
                startActivity(intent);
            }
        });

        dashboard_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, feedback_activity.class );
                startActivity(intent);
            }
        });
    }

    public void flipperImages(int image)
    {
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this,android.R.anim.fade_in);
        viewFlipper.setOutAnimation(this,android.R.anim.fade_out);

    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dashboard_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.menu_profile:
                //Toast.makeText(dashboard.this,"View Profile",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(dashboard.this, viewprofile.class);
                startActivity(intent);
                break;

//            case R.id.menu_cpassword:
//                //Toast.makeText(dashboard.this,"Change Password",Toast.LENGTH_SHORT).show();
//                Intent intent1 = new Intent(dashboard.this, changepassword.class);
//                startActivity(intent1);
//                break;

            case R.id.view_orders:
                Intent intent6 = new Intent(dashboard.this, vieworders.class);
                startActivity(intent6);
                break;


            case R.id.menu_aboutus:
                //Toast.makeText(dashboard.this,"About Us",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(dashboard.this, aboutus.class);
                startActivity(intent2);
                break;

            case R.id.logout:
                //databaseReference.removeValue();
                SharedPreferences sharedPreferences = getSharedPreferences("myprf",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                //Toast.makeText(dashboard.this,"Log out",Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(dashboard.this, login_activity.class);
                startActivity(intent3);
                finish();
                break;
        }

        return true;
    }
}
