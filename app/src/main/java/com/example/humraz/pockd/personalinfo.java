package com.example.humraz.pockd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class personalinfo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);
        Firebase.setAndroidContext(this);
    }

public void getdata(View view)
{
    EditText ed= (EditText) findViewById(R.id.fname);
    EditText ed1= (EditText) findViewById(R.id.lname);
    EditText ed2= (EditText) findViewById(R.id.phno);
    EditText ed3= (EditText) findViewById(R.id.ahdno);
    EditText ed4= (EditText) findViewById(R.id.pincode);
    EditText ed5= (EditText) findViewById(R.id.dob);

    String fname= ed.getText().toString();
    String lname=ed1.getText().toString();
    String phno=ed2.getText().toString();
    String ahdno=ed3.getText().toString();
    String pincode=ed4.getText().toString();
    String dob=ed5.getText().toString();


    SharedPreferences pref3 = null;
    pref3= getSharedPreferences("userdets",MODE_PRIVATE);
    pref3.edit().putString("username", fname).commit();


            Firebase ref = new Firebase("https://pockeddmo-7e844.firebaseio.com/USERS");

            //Getting values to store
            persondet p2 = new persondet();
           p2.setFname(fname);
    p2.setLname(lname);
    p2.setAdharno(ahdno);
    p2.setPincode(pincode);
    p2.setDob(dob);
    p2.setPhonenumber(phno);
            //Creating Person object


            //Adding values


            //Storing values to firebase
            ref.push().setValue(p2);
    new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("Successfully Registered")
            .setContentText("Please Verify Your Email Address")
            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    Intent in = new Intent(personalinfo.this, menu.class);
                    startActivity(in);

                }
            })
            .show();


}


}
