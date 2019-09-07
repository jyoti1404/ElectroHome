package com.example.hp.electrician.activity.user.feedback;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.electrician.R;
import com.example.hp.electrician.activity.user.electrician.eregister_activity;
import com.example.hp.electrician.pojo.FeedbackPojo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedback_activity extends AppCompatActivity {

    Button save;
    EditText editText1,editText2,editText3,editText4;
    FirebaseDatabase database;
    //boolean flag=false;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_activity);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        database = FirebaseDatabase.getInstance();
        SharedPreferences sharedPreferences = getSharedPreferences("myprf", MODE_PRIVATE);
        final String id = sharedPreferences.getString("id", null);
        databaseReference = database.getReference("feedbackinfo").child(id);
        save = findViewById(R.id.save);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Feedback");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String edit1 = editText1.getText().toString();
                final String edit2 = editText2.getText().toString();
                final String edit3 = editText3.getText().toString();
                final String edit4 = editText4.getText().toString();

                if (editText1.getText().toString().equals("")){
                    Toast.makeText(feedback_activity.this,"Please Fill All the Details",Toast.LENGTH_SHORT).show();
                   // flag =false;
                }
                else if (editText2.getText().toString().equals("")){
                    Toast.makeText(feedback_activity.this,"Please Fill All the Details",Toast.LENGTH_SHORT).show();
                    //flag =false;
                }
                else if (editText2.getText().toString().equals("")){
                    Toast.makeText(feedback_activity.this,"Please Fill All the Details",Toast.LENGTH_SHORT).show();
                  //  flag =false;
                }
                else if (editText3.getText().toString().equals("")){
                    Toast.makeText(feedback_activity.this,"Please Fill All the Details",Toast.LENGTH_SHORT).show();
                  //  flag =false;
                }
                else if (editText4.getText().toString().equals("")){
                    Toast.makeText(feedback_activity.this,"Please Fill All the Details",Toast.LENGTH_SHORT).show();
                  //  flag =false;
                }
               else {
                    String id =databaseReference.push().getKey();
                    FeedbackPojo feedbackPojo = new FeedbackPojo();
                    feedbackPojo.setEdit1(edit1);
                    feedbackPojo.setEdit2(edit2);
                    feedbackPojo.setEdit3(edit3);
                    feedbackPojo.setEdit4(edit4);
                    feedbackPojo.setFid(id);
                    databaseReference.child(id).setValue(feedbackPojo);
                    Toast.makeText(feedback_activity.this, "Details Stored", Toast.LENGTH_SHORT).show();
                }
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
