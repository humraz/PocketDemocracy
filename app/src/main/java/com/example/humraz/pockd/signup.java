package com.example.humraz.pockd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;
/*import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;*/

import cn.pedant.SweetAlert.SweetAlertDialog;


public class signup extends ActionBarActivity {
    SharedPreferences pref3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
      pref3= getSharedPreferences("userdets", MODE_PRIVATE);

    }
    public void signup(View view) {

        //cool button stuff
        final ActionProcessButton btnSignIn = (ActionProcessButton) findViewById(R.id.sign);

        btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
        btnSignIn.setProgress(1);


        ////code starts here
        EditText ed1 = (EditText) findViewById(R.id.user1);
        String user1 = ed1.getText().toString();
        pref3.edit().putString("user", user1).commit();
        EditText ed2 = (EditText) findViewById(R.id.pass);
/*

        ParseUser user = new ParseUser();
        user.setUsername(ed1.getText().toString());
        user.setPassword(ed2.getText().toString());
        user.setEmail(ed1.getText().toString());

// other fields can be set just like with ParseObject
        // user.put("phone", "650-253-0000");
        // final Intent in = new Intent(this, hey.class);
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {

                    success();
                    btnSignIn.setProgress(100);

                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
                    error();
                    btnSignIn.setProgress(0);
                }
            }
        });



    }

    public void pers()
    {
        Intent in= new Intent(this, personalinfo.class);
        startActivity(in);
    }
    public void error()
    {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("An Error Occurred. Please Try Again.")
                .show();
    }

    public void success()
    {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Successfully Registered")
                .setContentText("Please Verify Your Email Address")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        pers();

                    }
                })
                .show();

    }


    public void start(View view)
    {
        Intent in = new Intent(this, personalinfo.class);
        startActivity(in);
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
*/
    }}