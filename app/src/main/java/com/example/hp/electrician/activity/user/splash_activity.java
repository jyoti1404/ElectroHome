package com.example.hp.electrician.activity.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import com.example.hp.electrician.R;
import com.example.hp.electrician.activity.user.electrician.edashboard_activity;
import com.example.hp.electrician.activity.user.user.dashboard;
import com.example.hp.electrician.activity.user.user.login_activity;


public class splash_activity extends AppCompatActivity {

    SharedPreferences sharedPreferences,sharedPreferences1;
    String eemail,epassword,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);

        sharedPreferences = getSharedPreferences("myprf",MODE_PRIVATE);
        //sharedPreferences1 = getSharedPreferences("myprf1",MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);
        password = sharedPreferences.getString("password",null);
//        eemail = sharedPreferences1.getString("email",null);
//        epassword = sharedPreferences1.getString("password",null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (email!=null && password!=null){
                    Intent intent = new Intent(splash_activity.this,dashboard.class);
                    startActivity(intent);
                    finish();
                }
                  else if (email!= null && password!=null){
                    Intent intent = new Intent(splash_activity.this,edashboard_activity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(splash_activity.this,panel_activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },3000);


    }
}
