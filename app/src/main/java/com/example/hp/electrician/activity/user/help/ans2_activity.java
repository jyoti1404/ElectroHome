package com.example.hp.electrician.activity.user.help;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.hp.electrician.R;

public class ans2_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ans2_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Help");
    }
    @Override
    public  boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return true;
    }
}
