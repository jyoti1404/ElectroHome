package com.example.hp.electrician.activity.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hp.electrician.R;
import com.example.hp.electrician.activity.user.electrician.elogin_activity;
import com.example.hp.electrician.activity.user.user.login_activity;

public class panel_activity extends AppCompatActivity {

    Button button_user, button_electrician;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_activity);

        button_user = findViewById(R.id.button_user);
        button_electrician = findViewById(R.id.button_electrician);
        setTitle("Panel");

        button_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(panel_activity.this, login_activity.class);
                startActivity(intent);
               finish();
            }
        });

        button_electrician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(panel_activity.this, elogin_activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
