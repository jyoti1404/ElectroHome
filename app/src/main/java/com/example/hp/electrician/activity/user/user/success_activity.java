package com.example.hp.electrician.activity.user.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.hp.electrician.R;

public class success_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Success");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(success_activity.this, dashboard.class);
            startActivity(intent);

        }

        return true;
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(success_activity.this,dashboard.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

}
