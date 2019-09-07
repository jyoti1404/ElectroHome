package com.example.hp.electrician.activity.user.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hp.electrician.R;
import com.example.hp.electrician.pojo.UserPojo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class viewprofile extends AppCompatActivity {

    TextView vpname,vpmobile,vpemail,vppassword;
    CircleImageView vpprofile;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    ArrayList<UserPojo> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);

        SharedPreferences sharedPreferences = getSharedPreferences("myprf",MODE_PRIVATE);
        String profile = sharedPreferences.getString("profile", null);
        String name = sharedPreferences.getString("name", null);
        String mobile = sharedPreferences.getString("mobile", null);
        String email = sharedPreferences.getString("email", null);
        //String password = sharedPreferences.getString("password", null);

        vpname = findViewById(R.id.vpname);
        vpemail = findViewById(R.id.vpemail);
        vpmobile = findViewById(R.id.vpmobile);
        //vppassword = findViewById(R.id.vppassword);
        vpprofile = findViewById(R.id.vpuser);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("userinfo");

        vpname.setText("Name : "+name);
        vpmobile.setText("Mobile : " + mobile);
        vpemail.setText("Email : " + email);
        //vppassword.setText("Password : " + password);
     //   Log.d("1234", "onCreate: "+user);
        Glide.with(viewprofile.this).load(profile).into(vpprofile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Profile");


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
