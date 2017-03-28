package com.example.humraz.pockd;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;


public class forumnew extends ActionBarActivity {
    ChatView chatView;
    String fname;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forumm);

        fname= getIntent().getStringExtra("fnamee");
        Firebase.setAndroidContext(this);
        url="https://pockeddmo-7e844.firebaseio.com/ALLFORUMS/"+fname;
        chatView = (ChatView) findViewById(R.id.chat_view);
      read();


        chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener(){
            @Override
            public boolean sendMessage(ChatMessage chatMessage){
                update();
                Firebase ref = new Firebase(url);
                forumdata person = new forumdata();

                person.setPost(chatMessage.getMessage());
                ref.push().setValue(person);

                return true;
            }
        });
    }

    public void update()
    {


        Firebase ref = new Firebase("https://pockeddmo-7e844.firebaseio.com/notif");
        notif person = new notif();
        person.setMsg("New Post In Forum");
        person.setNotif("true");
        ref.push().setValue(person);

    }
    public void read()
    {

        final Firebase ref = new Firebase(url);
        //Value event listener for realtime data update

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot usersSnapshot) {

                for (DataSnapshot userSnapshot : usersSnapshot.getChildren()) {
                    forumdata user = userSnapshot.getValue(forumdata.class);

                   String post = user.getPost().toString();
                    chatView.addMessage(new ChatMessage(post, System.currentTimeMillis(), ChatMessage.Type.RECEIVED));

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }



}
