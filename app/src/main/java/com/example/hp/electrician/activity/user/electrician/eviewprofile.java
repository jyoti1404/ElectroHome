package com.example.hp.electrician.activity.user.electrician;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hp.electrician.R;
import com.example.hp.electrician.activity.user.user.viewprofile;
import com.example.hp.electrician.pojo.ElectricianPojo;
import com.example.hp.electrician.pojo.UserPojo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class eviewprofile extends AppCompatActivity {

    TextView vpname, vpmobile, vpemail, vppassword, vpfulladdress, vpshopaddress, vpage;
    CircleImageView vpprofile;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    ArrayList<ElectricianPojo> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eviewprofile);
        setTitle("View Profile");

        SharedPreferences sharedPreferences = getSharedPreferences("myprf", MODE_PRIVATE);
        String profile = sharedPreferences.getString("profile", null);
        String name = sharedPreferences.getString("name", null);
        String mobile = sharedPreferences.getString("mobile", null);
        String email = sharedPreferences.getString("email", null);
        String password = sharedPreferences.getString("password", null);
        String fulladdress = sharedPreferences.getString("full address", null);
        String shopaddress = sharedPreferences.getString("shop address", null);
        String age = sharedPreferences.getString("age", null);


        vpname = findViewById(R.id.evpname);
        vpemail = findViewById(R.id.evpemail);
        vpmobile = findViewById(R.id.evpmobile);
        vpprofile = findViewById(R.id.evpuser);
        vpage = findViewById(R.id.evpage);
        vpfulladdress = findViewById(R.id.evpfulladdress);
        vpshopaddress = findViewById(R.id.evpshopaddress);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("electricianinfo");

        vpname.setText("Name : " + name);
        vpmobile.setText("Mobile : " + mobile);
        vpemail.setText("Email : " + email);
        vpshopaddress.setText("Shop Address: " + shopaddress);
        vpfulladdress.setText("Full Address: " + fulladdress);
        vpage.setText("Age: " + age);
        //   Log.d("1234", "onCreate: "+user);
        Glide.with(eviewprofile.this).load(profile).into(vpprofile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
