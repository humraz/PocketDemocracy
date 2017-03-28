package com.example.humraz.pockd.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.humraz.pockd.R;
import com.example.humraz.pockd.forum;
import com.example.humraz.pockd.forumnew;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;

public class deleteposts extends AppCompatActivity {
    String name2;
    ArrayList<Card> cards = new ArrayList<Card>();
    ArrayList<String> cardslis = new ArrayList<String>();
    ArrayList<String> cardsdes = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteposts);
        Firebase.setAndroidContext(this);
        read();
    }
    public void cardpop()
    {
        for(int i=0;i<cardsdes.size();i++)
        {
            Card card = new Card(getApplicationContext());
            final String name= cardslis.get(i);
            final String jame=cardsdes.get(i);


            String s= name.concat("Asdasdasd");
            // blah(s);
            //Create a CardHeader
            CardHeader header = new CardHeader(getApplicationContext());
            header.setTitle(name);
            card.setTitle(jame);
            //Add Header to card

            header.setOtherButtonVisible(true);
            header.setOtherButtonDrawable(R.drawable.pencil);
            //Add a callback
            header.setOtherButtonClickListener(new CardHeader.OnClickCardHeaderOtherButtonListener() {
                @Override
                public void onButtonItemClick(Card card, View view) {
                    name2=name;
                    find(name);
                }
            });
            card.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    name2=name;
                    find(name);
                }
            });
            card.addCardHeader(header);
            cards.add(card);
        }
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getApplicationContext(),cards);

        CardListView listView = (CardListView) findViewById(R.id.myList);
        if (listView!=null){
            listView.setAdapter(mCardArrayAdapter);
        }
    }

    public void find(final String name3)
    {

        final Firebase ref = new Firebase("https://pockeddmo-7e844.firebaseio.com/FORUMLISTS");
        //Value event listener for realtime data update


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot usersSnapshot) {

                for (DataSnapshot userSnapshot : usersSnapshot.getChildren()) {
                    forum forums = userSnapshot.getValue(forum.class);

                    String firedata = forums.getName().toString();
                    if (firedata.equals(name3)) {
                        move(firedata);


                    }


                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


    }
    public void  pers()
    {
        Intent in = new Intent(this, adminmenu.class);

        startActivity(in);
    }
    public void  move(String firedata)
    {
        Intent in = new Intent(this, deletepostsmain.class);
        in.putExtra("fnamee",firedata);
        startActivity(in);
    }
    public void read()
    {
        final Firebase ref = new Firebase("https://pockeddmo-7e844.firebaseio.com/FORUMLISTS");
        //Value event listener for realtime data update


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot usersSnapshot) {

                for (DataSnapshot userSnapshot : usersSnapshot.getChildren()) {
                    forum forums = userSnapshot.getValue(forum.class);
                    String forumname = forums.getName().toString();
                    String forumdesc = forums.getDesc().toString();
                    cardslis.add(forumname);
                    cardsdes.add(forumdesc);

                }
                cardpop();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }
}
