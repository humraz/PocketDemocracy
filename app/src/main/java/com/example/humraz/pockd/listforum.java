package com.example.humraz.pockd;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.view.MaterialListView;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;


import it.gmariotti.cardslib.library.internal.CardHeader;


public class listforum extends ActionBarActivity {
   ArrayList<Card> cards = new ArrayList<Card>();
    ArrayList<String> cardslis = new ArrayList<String>();
    ArrayList<String> cardsdes = new ArrayList<String>();
    String name2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listforum);
        Firebase.setAndroidContext(this);
        read();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listforum, menu);
        return true;
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

    public void  move(String firedata)
    {
        Intent in = new Intent(this, forumnew.class);
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
public void blah(String forumname)
{
    Toast.makeText(this,forumname,Toast.LENGTH_LONG).show();
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
