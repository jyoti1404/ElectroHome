package com.example.hp.electrician.activity.user.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hp.electrician.R;

public class paying_activity extends AppCompatActivity {

    Button cash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paying_activity);

        setTitle("Pay");
        cash=(Button)findViewById(R.id.cash);
        cash.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(paying_activity.this,details_activity.class);
                startActivity(intent);
            }
        });
    }
}
