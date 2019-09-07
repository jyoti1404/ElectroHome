package com.example.hp.electrician.activity.user.electrician;

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
import com.example.hp.electrician.activity.user.user.dashboard;
import com.example.hp.electrician.activity.user.user.login_activity;
import com.example.hp.electrician.activity.user.user.register_activity;
import com.example.hp.electrician.pojo.ElectricianPojo;
import com.example.hp.electrician.pojo.UserPojo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class elogin_activity extends AppCompatActivity {

    EditText eemail,epassword;
    Button elogin;
    TextView eregister;

    SharedPreferences sharedPreferences;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    boolean flag = false;

    String emailPattern = "[a-zA-Z0-9]+@[a-z]+.+[a-z]";
    String passwordpattern = ("^" + "(?=.*[a-zA-Z])" +
            "(?=.*[@#$%^&+=])" +    //at least 1 special character\n" +
            "(?=.*[0-9])" +           //no white spaces\n" +
            ".{8,}");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elogin_activity);

        eemail = findViewById(R.id.eemail);
        epassword = findViewById(R.id.epassword);
        elogin = findViewById(R.id.elogin);
        eregister = findViewById(R.id.eregister);
        database=FirebaseDatabase.getInstance();
        databaseReference = database.getReference("electricianinfo");
        sharedPreferences = getSharedPreferences("myprf",Context.MODE_PRIVATE);

        boolean loginstatus = sharedPreferences.getBoolean("LoginStatus",false);
        if (loginstatus == true){

            Intent intent = new Intent(elogin_activity.this,edashboard_activity.class);
            startActivity(intent);
            finish();
        }

        eregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(elogin_activity.this,eregister_activity.class);
                startActivity(intent);
            }
        });
        elogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eemail.getText().toString().equals("")){
                    Toast.makeText(elogin_activity.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    flag =false;
                }
                if (epassword.getText().toString().equals("")){
                    Toast.makeText(elogin_activity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    flag =false;

                }
                if (!(eemail.getText().toString().matches(emailPattern))) {
                    Toast.makeText(elogin_activity.this, "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                } else if (!(epassword.getText().toString().matches(passwordpattern))) {
                    Toast.makeText(elogin_activity.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                } else {
                    final SharedPreferences sharedPreferences = getSharedPreferences("myprf",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    final String mail = eemail.getText().toString();
                    final String pa = epassword.getText().toString();

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                ElectricianPojo electricianPojo = dataSnapshot1.getValue(ElectricianPojo.class);

                                String femail = electricianPojo.getEemailreg();
                                String fpassword = electricianPojo.getEpasswordreg();

                                if (( mail.equals(femail)) && (pa.equals(fpassword))) {

                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("name",electricianPojo.getEname());
                                    editor.putString("id",electricianPojo.getEid());
                                    editor.putString("mobile",electricianPojo.getEmobile());
                                    editor.putString("email", electricianPojo.getEemailreg());
                                    editor.putString("age",electricianPojo.getEage());
                                    editor.putString("full address",electricianPojo.getEfulladdress());
                                    editor.putString("shop address",electricianPojo.getEshopaddress());
                                    editor.putString("password",electricianPojo.getEpasswordreg());
                                    editor.putString("profile",electricianPojo.getEimageurl());
                                    editor.putBoolean("LoginStatus",true);
                                    editor.commit();
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag == true) {
                                Intent intent = new Intent(elogin_activity.this, edashboard_activity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                epassword.setText("");
                                Toast.makeText(elogin_activity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(elogin_activity.this, "Database Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

    }
}
