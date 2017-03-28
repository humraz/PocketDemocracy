package com.example.humraz.pockd.admin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.humraz.pockd.R;
import com.example.humraz.pockd.notif;
import com.firebase.client.Firebase;

public class notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);

        EditText ed= (EditText) findViewById(R.id.editText);
        String msg= ed.getText().toString();
        update(msg);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void update(String ms)
    {


        Firebase ref = new Firebase("https://pockeddmo-7e844.firebaseio.com/notif");
        notif person = new notif();

        person.setNotif("true");
        person.setMsg(ms);
        ref.push().setValue(person);

    }

}
