package com.example.humraz.pockd.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.humraz.pockd.R;

public class adminlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
    }


    public void signin(View view)
    {
        Intent in = new Intent(this, adminmenu.class);
        startActivity(in);
    }
}
