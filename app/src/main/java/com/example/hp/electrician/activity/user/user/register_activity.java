package com.example.hp.electrician.activity.user.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.electrician.Helper.SelectImageHelper;
import com.example.hp.electrician.R;
import com.example.hp.electrician.pojo.UserPojo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class register_activity extends AppCompatActivity {

    CircleImageView user;
    EditText name, mobile, emailreg, passwordreg, cpassword;
    Button regbutton;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
  //  FirebaseAuth auth;
    boolean flag = true;
    SelectImageHelper selectImageHelper;
    StorageReference mStorageRef;
    ProgressDialog dialog;
    String emailPattern = "[a-zA-Z0-9]+@[a-z]+.+[a-z]";
    String passwordpattern = ("^" + "(?=.*[a-zA-Z])" +
            "(?=.*[@#$%^&+=])" +    //at least 1 special character\n" +
            "(?=.*[0-9])" +           //no white spaces\n" +
            ".{8,}");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);

        setTitle("Register");
        user = findViewById(R.id.user);
        name = findViewById(R.id.name);
        emailreg = findViewById(R.id.emailreg);
        mobile = findViewById(R.id.mobile);
        passwordreg = findViewById(R.id.passwordreg);
        cpassword = findViewById(R.id.cpassword);
        regbutton = findViewById(R.id.regbutton);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("userinfo");
    //    auth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        selectImageHelper = new SelectImageHelper(this, user);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImageHelper.selectImageOption();

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.setTitle("Please Wait...");
                dialog.show();
                final String id = databaseReference.push().getKey();
                final String rname = name.getText().toString();
                final String rmobile = mobile.getText().toString();
                final String remail = emailreg.getText().toString();
                final String rpassword = passwordreg.getText().toString();
                final String rcpassword = cpassword.getText().toString();
                Uri uri = selectImageHelper.getURI_FOR_SELECTED_IMAGE();
                if (uri != null) {
                    flag = true;
                }
/*                auth.createUserWithEmailAndPassword(remail,rpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(register_activity.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(register_activity.this, login_activity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(register_activity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(register_activity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
*/

                if (name.getText().toString().equals("")){
                    Toast.makeText(register_activity.this,"Please Enter Name",Toast.LENGTH_SHORT).show();
                    flag =false;
                    dialog.cancel();
                }
                if (mobile.getText().toString().equals("") ) {
                    Toast.makeText(register_activity.this, "Please Enter Mobile No", Toast.LENGTH_LONG).show();
                    flag = false;
                    dialog.cancel();
                }
                if (mobile.length()<10){
                    Toast.makeText(register_activity.this, "Number should not be less than 10  ", Toast.LENGTH_LONG).show();
                    flag = false;
                    dialog.cancel();

                }
                if (emailreg.getText().toString().equals("")){
                    Toast.makeText(register_activity.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    flag =false;
                    dialog.cancel();
                }
                if (!(emailreg.getText().toString().matches(emailPattern))) {
                    Toast.makeText(register_activity.this, "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                    flag = false;
                    dialog.cancel();
                }
                if (passwordreg.getText().toString().equals("")){
                    Toast.makeText(register_activity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    flag =false;
                    dialog.cancel();
                }
                if (!(passwordreg.getText().toString().matches(passwordpattern))) {
                    Toast.makeText(register_activity.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                    flag = false;
                    dialog.cancel();
                }

                if (cpassword.getText().toString().equals("")){
                    Toast.makeText(register_activity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    flag =false;
                    dialog.cancel();
                }
                if (!(cpassword.getText().toString().matches(passwordreg.getText().toString()))) {
                    Toast.makeText(register_activity.this, "Please Retype Valid Password", Toast.LENGTH_SHORT).show();
                    flag = false;
                    dialog.cancel();
                }
                if (flag == true) {
                    final StorageReference storageReference = mStorageRef.child("image/" + remail);
                    storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {

                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    dialog.cancel();
                                    UserPojo userPojo = new UserPojo();
                                    userPojo.setName(rname);
                                    userPojo.setMobile(rmobile);
                                    userPojo.setEmailreg(remail);
                                    userPojo.setPasswordreg(rpassword);
                                    userPojo.setCpassword(rcpassword);
                                    userPojo.setImageurl(uri.toString());
                                    userPojo.setId(id);
                                    databaseReference.child(id).setValue(userPojo);

                                    Toast.makeText(register_activity.this, "Registered Successfully", Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(register_activity.this, login_activity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            dialog.cancel();
                            Toast.makeText(register_activity.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        selectImageHelper.handleResult(requestCode, resultCode, result);  // call this helper class method
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        selectImageHelper.handleGrantedPermisson(requestCode, grantResults);   // call this helper class method
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