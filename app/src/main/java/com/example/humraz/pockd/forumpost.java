package com.example.humraz.pockd;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import co.intentservice.chatui.ChatView;


public class forumpost extends ActionBarActivity {
    String fname;
    String url;
    private EditText editTextName;
    private EditText editTextAddress;
    private TextView textViewPersons;
    private TextView textViewPersons2;
    ChatView chatView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forumpost);
        fname= getIntent().getStringExtra("fnamee");
        Firebase.setAndroidContext(this);
        url="https://pockeddmo-7e844.firebaseio.com/ALLFORUMS/"+fname;
        Firebase ref2 = new Firebase(url);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);

        textViewPersons = (TextView) findViewById(R.id.textView);

        textViewPersons2 = (TextView) findViewById(R.id.tv3);
read();
    }

    public void read() {

        final Firebase ref = new Firebase(url);
        //Value event listener for realtime data update
        final StringBuilder finalTotal = new StringBuilder();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot usersSnapshot) {

                for (DataSnapshot userSnapshot : usersSnapshot.getChildren()) {
                    forumdata user = userSnapshot.getValue(forumdata.class);

                    finalTotal.append(user.getPost());
                    finalTotal.append("---------");
                    finalTotal.append(user.getUser());
                    finalTotal.append('\n');

                    textViewPersons.setText(finalTotal.toString());


                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }


    public void onlick(View v){
        //Creating firebase object
        Firebase ref = new Firebase(url);
        SharedPreferences pref3=null;
        pref3= getSharedPreferences("userdets",MODE_PRIVATE);
        String user=pref3.getString("username", null);
        //Getting values to store
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        //Creating Person object
        forumdata person = new forumdata();

        //Adding values
        person.setPost(name);
person.setUser(user);

        //Storing values to firebase
        ref.push().setValue(person);
        editTextAddress.setText("");
        editTextName.setText("");
        Toast.makeText(this, "Enter next Record", Toast.LENGTH_LONG).show();
        read();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forumpost, menu);
        return true;
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
