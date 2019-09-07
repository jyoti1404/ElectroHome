package com.example.hp.electrician.activity.user.electrician;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.electrician.R;
import com.example.hp.electrician.activity.user.user.changepassword;
import com.example.hp.electrician.activity.user.user.dashboard;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class echangepassword extends AppCompatActivity {

    EditText oldpassword, newpassword, confirmpassword;
    Button changepasswordbutton;
    String passwordpattern = ("^" + "(?=.*[a-zA-Z])" +
            "(?=.*[@#$%^&+=])" +    //at least 1 special character\n" +
            "(?=.*[0-9])" +           //no white spaces\n" +
            ".{8,}");

    FirebaseDatabase database;
    boolean flag=true;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echangepassword);

        oldpassword = findViewById(R.id.eoldpassword);
        newpassword = findViewById(R.id.enewpassword);
        confirmpassword = findViewById(R.id.econfirmpassword);
        changepasswordbutton = findViewById(R.id.echangepasswordbutton);
        sharedPreferences = getSharedPreferences("myprf", Context.MODE_PRIVATE);
//        sharedPreferences = getSharedPreferences("myprf", Context.MODE_PRIVATE);
        final String pass = sharedPreferences.getString("password",null);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("electricianinfo");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Change Password");

//        changepasswordbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (oldpassword.getText().toString().equals("")){
//                    Toast.makeText(echangepassword.this, "Please Complete the information", Toast.LENGTH_SHORT).show();
//
//                }
////                if (!oldpassword.getText().toString().equals(pass)){
////                    Toast.makeText(echangepassword.this, "Please Enter Correct Current Password", Toast.LENGTH_SHORT).show();
////                }
//                if (!newpassword.getText().toString().equals(passwordpattern)){
//                    Toast.makeText(echangepassword.this, "Invalid Password", Toast.LENGTH_SHORT).show();
//                }
//
//
//                if (confirmpassword.getText().toString().equalsIgnoreCase("") | oldpassword.getText().toString().equalsIgnoreCase("") | newpassword.getText().toString().equalsIgnoreCase("")) {
//
//                    Toast.makeText(echangepassword.this, "Please Complete the information", Toast.LENGTH_SHORT).show();
//                } else if (!(newpassword.getText().toString()).equalsIgnoreCase(confirmpassword.getText().toString())) {
//                    Toast.makeText(echangepassword.this, "Passwords dont match", Toast.LENGTH_SHORT).show();
//                } else {
//                    final String newpass = newpassword.getText().toString();
//                    HashMap<String, Object> hashMap = new HashMap<>();
//                    hashMap.put("passwordreg", newpass);
//                    hashMap.put("cpassword",newpass);
//                    String id = sharedPreferences.getString("id", null);
//                    databaseReference.child(id).updateChildren(hashMap);
//                    Toast.makeText(echangepassword.this,"Password has been Updated",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(echangepassword.this, edashboard_activity.class);
//                    startActivity(intent);
//                }
//
//            }
//
//        });

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
