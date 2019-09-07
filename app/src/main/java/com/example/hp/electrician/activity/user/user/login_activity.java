package com.example.hp.electrician.activity.user.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.electrician.R;
import com.example.hp.electrician.pojo.UserPojo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_activity extends AppCompatActivity {

    EditText email,password;
    Button login;
    TextView register;
    SharedPreferences sharedPreferences;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    //FirebaseAuth auth;
    boolean flag = false;

    String emailPattern = "[a-zA-Z0-9]+@[a-z]+.+[a-z]";
    String passwordpattern = ("^" + "(?=.*[a-zA-Z])" +
            "(?=.*[@#$%^&+=])" +    //at least 1 special character\n" +
            "(?=.*[0-9])" +           //no white spaces\n" +
            ".{8,}");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        database=FirebaseDatabase.getInstance();
        databaseReference = database.getReference("userinfo");
       // auth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("myprf",Context.MODE_PRIVATE);

        boolean loginstatus = sharedPreferences.getBoolean("LoginStatus",false);
        if (loginstatus == true){

            Intent intent = new Intent(login_activity.this,dashboard.class);
            startActivity(intent);
            finish();
        }


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_activity.this,register_activity.class);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("")){
                    Toast.makeText(login_activity.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    flag =false;
                }
                if (password.getText().toString().equals("")){
                    Toast.makeText(login_activity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    flag =false;

                }

                if (!(email.getText().toString().matches(emailPattern))) {
                    Toast.makeText(login_activity.this, "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                } else if (!(password.getText().toString().matches(passwordpattern))) {
                    Toast.makeText(login_activity.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                } else {
                    final SharedPreferences sharedPreferences = getSharedPreferences("myprf", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    final String mail = email.getText().toString();
                    final String pa = password.getText().toString();

                /*    auth.signInWithEmailAndPassword(mail, pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (auth.getCurrentUser().isEmailVerified()) {

                                databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                            UserPojo userPojo = dataSnapshot1.getValue(UserPojo.class);

                                            String fname = userPojo.getName();
                                            String fmobile = userPojo.getMobile();
                                            String femail = userPojo.getEmailreg();
                                            String fpassword = userPojo.getPasswordreg();

                                            if ((mail.equals(femail)) && (pa.equals(fpassword))) {

                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString("name", userPojo.getName());
                                                editor.putString("id", userPojo.getId());
                                                editor.putString("mobile", userPojo.getMobile());
                                                editor.putString("email", userPojo.getEmailreg());
                                                editor.putString("password", userPojo.getPasswordreg());
                                                editor.putString("profile", userPojo.getImageurl());
                                                editor.putBoolean("LoginStatus", true);
                                                editor.commit();
                                                flag = true;
                                                break;
                                            }
                                        }
                                        if (flag == true) {
                                            Intent intent = new Intent(login_activity.this, dashboard.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            password.setText("");
                                            Toast.makeText(login_activity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toast.makeText(login_activity.this, "Database Error", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                Toast.makeText(login_activity.this, "Not Verified" , Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(login_activity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                  */  databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                UserPojo userPojo = dataSnapshot1.getValue(UserPojo.class);

                                String fname = userPojo.getName();
                                String fmobile = userPojo.getMobile();
                                String femail = userPojo.getEmailreg();
                                String fpassword = userPojo.getPasswordreg();

                                if (( mail.equals(femail)) && (pa.equals(fpassword))) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("name",userPojo.getName());
                                    editor.putString("id",userPojo.getId());
                                    editor.putString("mobile",userPojo.getMobile());
                                    editor.putString("email", userPojo.getEmailreg());
                                    editor.putString("password", userPojo.getPasswordreg());
                                    editor.putString("profile",userPojo.getImageurl());
                                    editor.putBoolean("LoginStatus",true);
                                    editor.commit();
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag == true) {
                                Intent intent = new Intent(login_activity.this, dashboard.class);
                                startActivity(intent);
                                finish();
                            } else {
                                password.setText("");
                                Toast.makeText(login_activity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(login_activity.this, "Database Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
}
