package com.example.hp.electrician.activity.user.help;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.hp.electrician.R;

public class questions_activity extends AppCompatActivity {

    ImageView img1, img2, img3, img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_activity);

        setTitle("Booking Services");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        img1 =  findViewById(R.id.img1);
        img2 =  findViewById(R.id.img2);
        img3 =  findViewById(R.id.img3);
        img4 =  findViewById(R.id.img4);
        img1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(questions_activity.this, ans1_activity.class);
                startActivity(intent);

            }
        });
        img2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(questions_activity.this, ans2_activity.class);
                startActivity(intent);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(questions_activity.this, ans3_activity.class);
                startActivity(intent);


            }
        });
        img4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(questions_activity.this, ans4_activity.class);
                startActivity(intent);


            }
        });
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
