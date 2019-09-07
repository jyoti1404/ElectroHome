package com.example.hp.electrician.activity.user.electrician;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hp.electrician.Helper.SelectImageHelper;
import com.example.hp.electrician.R;
import com.example.hp.electrician.activity.user.user.login_activity;
import com.example.hp.electrician.activity.user.user.register_activity;
import com.example.hp.electrician.pojo.ElectricianPojo;
import com.example.hp.electrician.pojo.UserPojo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class eregister_activity extends AppCompatActivity {

    CircleImageView euser;
    EditText ename, emobile, eemailreg, eage, efulladdress, eshopadress ,epasswordreg, ecpassword;
    Button eregbutton;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    boolean flag = true;
    SelectImageHelper selectImageHelper;
    StorageReference mStorageRef;
    ProgressDialog dialog;
    String emailPattern = "[a-zA-Z0-9]+@[a-z]+.+[com]";
    String passwordpattern = ("^" + "(?=.*[a-zA-Z])" +
            "(?=.*[@#$%^&+=])" +    //at least 1 special character\n" +
            "(?=.*[0-9])" +           //no white spaces\n" +
            ".{8,}");


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eregister_activity);

        euser = findViewById(R.id.euser);
        ename = findViewById(R.id.ename);
        eemailreg = findViewById(R.id.eemailreg);
        emobile = findViewById(R.id.emobile);
        eage = findViewById(R.id.eage);
        efulladdress = findViewById(R.id.efulladdress);
        eshopadress = findViewById(R.id.eshopaddress);
        epasswordreg = findViewById(R.id.epasswordreg);
        ecpassword = findViewById(R.id.ecpassword);
        eregbutton = findViewById(R.id.eregbutton);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("electricianinfo");
        mStorageRef = FirebaseStorage.getInstance().getReference();
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        selectImageHelper = new SelectImageHelper(this, euser);
        setTitle("Register");

        euser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImageHelper.selectImageOption();

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eregbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.setTitle("Please Wait...");
                dialog.show();
                final String rid = databaseReference.push().getKey();
                final String rname = ename.getText().toString();
                final String rmobile = emobile.getText().toString();
                final String remail = eemailreg.getText().toString();
                final String rfulladdress = efulladdress.getText().toString();
                final String rshopaddress = eshopadress.getText().toString();
                final String rage = eage.getText().toString();
                final String rpassword = epasswordreg.getText().toString();
                final String rcpassword = ecpassword.getText().toString();
                Uri uri = selectImageHelper.getURI_FOR_SELECTED_IMAGE();
                if (uri != null) {
                    flag = true;
                }

                if (euser.equals("")){
                    Toast.makeText(eregister_activity.this,"Please insert a picture",Toast.LENGTH_SHORT).show();
                    flag =false;
                    dialog.cancel();

                }
                if (ename.getText().toString().equals("")){
                    Toast.makeText(eregister_activity.this,"Please Enter Name",Toast.LENGTH_SHORT).show();
                    flag =false;
                    dialog.cancel();
                }
                if (emobile.getText().toString().equals("") ) {
                    Toast.makeText(eregister_activity.this, "Please Enter Mobile No", Toast.LENGTH_LONG).show();
                    flag = false;
                    dialog.cancel();
                }
                if (emobile.length()<10){
                    Toast.makeText(eregister_activity.this, "Number should not be less than 10  ", Toast.LENGTH_LONG).show();
                    flag = false;
                    dialog.cancel();

                }
                if (eemailreg.getText().toString().equals("")){
                    Toast.makeText(eregister_activity.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    flag =false;
                    dialog.cancel();
                }
                if (!(eemailreg.getText().toString().matches(emailPattern))) {
                    Toast.makeText(eregister_activity.this, "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                    flag = false;
                    dialog.cancel();
                }
                if (epasswordreg.getText().toString().equals("")){
                    Toast.makeText(eregister_activity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    flag =false;
                    dialog.cancel();
                }
                if (!(epasswordreg.getText().toString().matches(passwordpattern))) {
                    Toast.makeText(eregister_activity.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                    flag = false;
                    dialog.cancel();
                }

                if (ecpassword.getText().toString().equals("")){
                    Toast.makeText(eregister_activity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    flag =false;
                    dialog.cancel();
                }
                if (!(ecpassword.getText().toString().matches(epasswordreg.getText().toString()))) {
                    Toast.makeText(eregister_activity.this, "Please Retype Valid Password", Toast.LENGTH_SHORT).show();
                    flag = false;
                    dialog.cancel();
                }
                if (efulladdress.getText().toString().equals("")){
                    Toast.makeText(eregister_activity.this, "Please Enter Full Address", Toast.LENGTH_SHORT).show();
                    flag = false;
                    dialog.cancel();

                }
                if (eshopadress.getText().toString().equals("")){
                    Toast.makeText(eregister_activity.this, "Please Enter Shop Address", Toast.LENGTH_SHORT).show();
                    flag = false;
                    dialog.cancel();

                }
                if (eage.getText().toString().equals("")){
                    Toast.makeText(eregister_activity.this, "Please Enter Age", Toast.LENGTH_SHORT).show();
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
                                    ElectricianPojo electricianPojo = new ElectricianPojo();
                                    electricianPojo.setEname(rname);
                                    electricianPojo.setEmobile(rmobile);
                                    electricianPojo.setEemailreg(remail);
                                    electricianPojo.setEfulladdress(rfulladdress);
                                    electricianPojo.setEshopaddress(rshopaddress);
                                    electricianPojo.setEage(rage);
                                    electricianPojo.setEpasswordreg(rpassword);
                                    electricianPojo.setEcpasswordreg(rcpassword);
                                    electricianPojo.setEid(rid);
                                    electricianPojo.setEimageurl(uri.toString());
                                    databaseReference.child(rid).setValue(electricianPojo);

                                    Toast.makeText(eregister_activity.this, "Registered Successfully", Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(eregister_activity.this, elogin_activity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            dialog.cancel();
                            Toast.makeText(eregister_activity.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
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


