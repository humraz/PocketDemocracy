package com.example.humraz.pockd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.humraz.pockd.admin.adminlogin;
/*import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;*/

import cn.pedant.SweetAlert.SweetAlertDialog;


public class login extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      /*chec();*/
    }
  /*  public  void chec()
    {
        ParseObject gameScore = new ParseObject("GameScore");
        gameScore.put("score", 1337);
        gameScore.put("playerName", "Sean Plott");
        gameScore.put("cheatMode", false);
        gameScore.saveInBackground();
    }*/
    public void start(View view)
    {
        Intent in = new Intent(this, menu.class);
        startActivity(in);
    }
    public void admin(View view)
    {
        Intent in = new Intent(this, adminlogin.class);
        startActivity(in);
    }
    public  void log2(View view)
    {
        ////button cool
        final ActionProcessButton btnSignIn = (ActionProcessButton) findViewById(R.id.login);

        btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
        btnSignIn.setProgress(1);


        EditText ed1=(EditText) findViewById( R.id.user2);
        EditText ed2=(EditText) findViewById( R.id.user);
        final Intent in = new Intent(this, menu.class);
       /* ParseUser.logInInBackground(ed1.getText().toString(), ed2.getText().toString(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    if (user.getBoolean("emailVerified")) {
                        startActivity(in);
                    } else {
                        mailnotveri();
                        btnSignIn.setProgress(0);
                    }
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    error();
                    btnSignIn.setProgress(0);
                }
            }
        });*/
        btnSignIn.setProgress(0);
    }
public void signup(View view)
{
    Intent in= new Intent(this, personalinfo.class);
    startActivity(in);
}
    public void mailnotveri()
    {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Your E-mail Hasn't Been Verified Yet.Verify To Continue.")
                .show();
    }
public void log(View v)
{
    Intent in = new Intent(this, menu.class);
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
                .show();

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
