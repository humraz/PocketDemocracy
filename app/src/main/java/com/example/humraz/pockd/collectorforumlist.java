package com.example.humraz.pockd;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;


public class collectorforumlist extends ActionBarActivity {
    String choice;
    BootstrapButton m1;;
    ArrayList<Card> cards = new ArrayList<Card>();
    ArrayList<String> cardslis = new ArrayList<String>();
    ArrayList<String> cardsdes = new ArrayList<String>();
    String name2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collectorforumlist);
        choice= getIntent().getStringExtra("coise");
        Firebase.setAndroidContext(this);
        read();
    }
public void clickkk(View v)
{
    Intent in= new Intent(this, createforum.class);
    startActivity(in);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_collectorforumlist, menu);
        return true;
    }
    public void  move(String firedata)
    {
        Intent in = new Intent(this, forumpost.class);
        in.putExtra("fnamee",firedata);
        startActivity(in);
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

    public void cardpop()
    {
        for(int i=0;i<cardsdes.size();i++)
        {
            Card card = new Card(getApplicationContext());
            final String name= cardslis.get(i);
            final String jame=cardsdes.get(i);



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
            card.addCardHeader(header);
            cards.add(card);
        }
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getApplicationContext(),cards);

        CardListView listView = (CardListView) findViewById(R.id.myList);
        if (listView!=null){
            listView.setAdapter(mCardArrayAdapter);
        }
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
                    String c = forums.getC().toString();
                    if (c.equals(choice)) {
                        String forumname = forums.getName().toString();
                        String forumdesc = forums.getC().toString();
                        cardslis.add(forumname);
                        cardsdes.add(forumdesc);
                    }
                }
                cardpop();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

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
