package com.example.humraz.pockd.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.humraz.pockd.Collectorforums;
import com.example.humraz.pockd.R;
import com.example.humraz.pockd.createforum;
import com.example.humraz.pockd.forum;
import com.example.humraz.pockd.listforum;
import com.example.humraz.pockd.notific;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;

public class adminmenu extends AppCompatActivity {
    String name2;
    ArrayList<Card> cards = new ArrayList<Card>();
    ArrayList<String> cardslis = new ArrayList<String>();
    ArrayList<String> cardsdes = new ArrayList<String>();
    static int[] imageResources = new int[]{
            R.drawable.drawing,
            R.drawable.clipboard,
            R.drawable.funnel,
            R.drawable.smartphone,
            R.drawable.chat


    };
    static int[] Strings = new int[]{
            R.string.deleteforum,
            R.string.list,
            R.string.deleteposts,
            R.string.sendnotif,




    };
    public static int getString() {
        if (str >= Strings.length) str = 0;
        return Strings[str++];
    }
    static int str = 0;
    static int imageResourceIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmenu);
        bmb();
        Firebase.setAndroidContext(this);
        read();
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

          //  header.setOtherButtonVisible(true);
            header.setOtherButtonDrawable(R.drawable.pencil);
            //Add a callback
            header.setOtherButtonClickListener(new CardHeader.OnClickCardHeaderOtherButtonListener() {
                @Override
                public void onButtonItemClick(Card card, View view) {
                 //   name2=name;
                   // find(name);
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
    public void bmb()
    {
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalTextRes(getString())
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {


                            if (index == 0) {
                                start();

                            }
                            if (index == 1) {
                                start2();
                            }
                            if (index == 2) {
                                start3();

                            }
                            if (index == 3) {
                                showfors();
                            }

                        }
                    })

                    .normalImageRes(getImageResource());
            bmb.addBuilder(builder);
        }
    }
    public static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }
    public  void start()
    {

        Intent in = new Intent(this, deleteforum.class);
        startActivity(in);
    }
    public  void start2()
    {

        Intent in = new Intent(this, deleteposts.class);
        startActivity(in);
    }
    public  void start3()
    {

        Intent in = new Intent(this, Collectorforums.class);
        startActivity(in);
    }

    public  void showfors()
    {

        Intent in = new Intent(this, Main2Activity.class);
        startActivity(in);
    }
    public  void list()
    {

        Intent in = new Intent(this, notific.class);
        startActivity(in);
    }
}
