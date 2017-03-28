package com.example.humraz.pockd;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.firebase.client.Firebase;
/*import com.parse.ParseObject;*/
import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.R.attr.value;


public class createforum extends ActionBarActivity {
    BootstrapButton c1;
    BootstrapButton c2;
    BootstrapButton m1;;
    BootstrapButton m2;
    String choice="";
    int value   ;
    SwipeSelector swipeSelector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createforum);
        Firebase.setAndroidContext(this);
       swipeSelector = (SwipeSelector) findViewById(R.id.swipeSelector);
        swipeSelector.setItems(
                new SwipeItem(0, "MLA1", "CHOOSE MLA1"),
                new SwipeItem(1, "MLA2", "CHOOSE MLA1"),
                new SwipeItem(2, "COL 1", "CHOOSE MLA1")
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_createforum, menu);
        return true;
    }


    public void click(View v)
    {
        SwipeItem selectedItem = swipeSelector.getSelectedItem();

        value = (Integer) selectedItem.value;

        switch(value)
        {
            case 1:choice="c1";
                break;
            case 2:choice="c2";
                break;
            case 3:choice="m1";
                break;
            case 0:choice="m2";
                break;
            default:choice="";
        }
        if(choice.equals("")) {
        Toast.makeText(this, "Choose a collector first",Toast.LENGTH_LONG).show();

        }
        else {
            EditText ed = (EditText) findViewById(R.id.fname);
            EditText ed1 = (EditText) findViewById(R.id.fdesc);
            EditText ed2 = (EditText) findViewById(R.id.farea);
            String fname = ed.getText().toString();
            String fdesc = ed1.getText().toString();
            String farea = ed2.getText().toString();
            String f = fname + "  " + farea;
            Firebase ref = new Firebase("https://pockeddmo-7e844.firebaseio.com/FORUMLISTS");
            Firebase ref2 = new Firebase("https://pockeddmo-7e844.firebaseio.com/ALLFORUMS/" + fname);
            //Getting values to store
            forumdata p2 = new forumdata();
            p2.setPost("testpostfirst");
            //Creating Person object
            forum person = new forum();

            //Adding values
            person.setName(fname);
            person.setDesc(fdesc);
            person.setPincode(farea);
            person.setC(choice);
            //Storing values to firebase
            ref.push().setValue(person);
            ref2.push().setValue(p2);
            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Successfully Created")
                    .setContentText("")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            pers();

                        }
                    })
                    .show();
        }
    }

    public void swipe()
    {




    }
    public  void pers()
    {
        Intent in = new Intent(this , listforum.class);
        startActivity(in);
    }
    public void c1(View view)
    {/*
        c2.setEnabled(false);
        m1.setEnabled(false);
        m2.setEnabled(false);*/
        choice="c1";
    }
    public void c2(View view)
    {/*
        c1.setEnabled(false);
        m1.setEnabled(false);
        m2.setEnabled(false);*/
        choice="c2";
    }
    public void m1(View view)
    {
      /*  c2.setEnabled(false);
        m1.setEnabled(false);
        c1.setEnabled(false);*/
        choice="m1";
    }
    public void m2(View view)
    {
     /*   c2.setEnabled(false);
        m1.setEnabled(false);
        c1.setEnabled(false);*/
        choice="m2";
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
