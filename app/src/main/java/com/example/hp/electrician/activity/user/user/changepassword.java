package com.example.hp.electrician.activity.user.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.electrician.R;
import com.example.hp.electrician.pojo.UserPojo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class changepassword extends AppCompatActivity {

    String passwordpattern = ("^" + "(?=.*[a-zA-Z])" +
            "(?=.*[@#$%^&+=])" +    //at least 1 special character\n" +
            "(?=.*[0-9])" +           //no white spaces\n" +
            ".{8,}");

    EditText  oldpassword,newpassword, confirmpassword;
    Button changepasswordbutton;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;

    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        oldpassword = findViewById(R.id.oldpassword);
        newpassword = findViewById(R.id.newpassword);
        confirmpassword = findViewById(R.id.confirmpassword);
        changepasswordbutton = findViewById(R.id.changepasswordbutton);
        sharedPreferences = getSharedPreferences("myprf", Context.MODE_PRIVATE);
//        pass = sharedPreferences.getString("password",null);
////        UserPojo userPojo = new UserPojo();
 //       final String pass = userPojo.getPasswordreg();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("userinfo");

//
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Change Password");

//        changepasswordbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                if (oldpassword.getText().toString().equals("")){
////                    Toast.makeText(changepassword.this, "Please Complete the information", Toast.LENGTH_SHORT).show();
////
////                }
//////                else if (!oldpassword.getText().toString().equals(pass)){
////                    Toast.makeText(changepassword.this, "Please Enter Correct Current Password", Toast.LENGTH_SHORT).show();
////
////                }
//                if (!newpassword.getText().toString().equals(passwordpattern)){
//                    Toast.makeText(changepassword.this, "Invalid Password", Toast.LENGTH_SHORT).show();
//
//                }
//
//                else if (confirmpassword.getText().toString().equalsIgnoreCase("") | newpassword.getText().toString().equalsIgnoreCase("")) {
//
//                    Toast.makeText(changepassword.this, "Please Complete the information", Toast.LENGTH_SHORT).show();
//                    //flag=false;
//                }  else if (!(newpassword.getText().toString()).equalsIgnoreCase(confirmpassword.getText().toString())) {
//                    Toast.makeText(changepassword.this, "Passwords dont match", Toast.LENGTH_SHORT).show();
//                    //flag=false;
//                }
//                else{
//                    final String newpass = newpassword.getText().toString();
//                    HashMap<String, Object> hashMap = new HashMap<>();
//                    hashMap.put("passwordreg", newpass);
//                    hashMap.put("cpassword",newpass);
//                    String id = sharedPreferences.getString("id", null);
//                    databaseReference.child(id).updateChildren(hashMap);
//                    Toast.makeText(changepassword.this,"Password has been Updated",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(changepassword.this, dashboard.class);
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

        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;


    }

}
