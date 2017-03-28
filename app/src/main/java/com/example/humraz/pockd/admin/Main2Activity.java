package com.example.humraz.pockd.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.humraz.pockd.R;
import com.example.humraz.pockd.notif;
import com.firebase.client.Firebase;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Firebase.setAndroidContext(this);

    }

    public void update(View view)
    {


        EditText ed= (EditText) findViewById(R.id.editText2);
        String msg= ed.getText().toString();

        Firebase ref = new Firebase("https://pockeddmo-7e844.firebaseio.com/notif");
        notif person = new notif();

        person.setNotif("true");
        person.setMsg(msg);
        ref.push().setValue(person);

    }

}
