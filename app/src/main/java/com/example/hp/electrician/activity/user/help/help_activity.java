package com.example.hp.electrician.activity.user.help;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.hp.electrician.R;

public class help_activity extends AppCompatActivity {

    Button help_button1,help_button2,help_button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Help");

        help_button1=(Button)findViewById(R.id.help_button1);
        help_button2=(Button)findViewById(R.id.help_button2);
        help_button3=(Button)findViewById(R.id.help_button3);

        help_button1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(help_activity.this,questions_activity.class);
                startActivity(intent);
            }
        });
        help_button2.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(help_activity.this,payques_activity.class);
                startActivity(intent);
            }
        });
        help_button3.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(help_activity.this,guide_activity.class);
                startActivity(intent);
            }
        });
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
