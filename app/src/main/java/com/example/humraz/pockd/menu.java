package com.example.humraz.pockd;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import  com.example.humraz.pockd.notifications.MyTestService;
import  com.example.humraz.pockd.notifications.MyAlarmReceiver;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;


public class menu extends ActionBarActivity {
    ArrayList<Card> cards = new ArrayList<Card>();
    ArrayList<String> cardslis = new ArrayList<String>();
    ArrayList<String> cardsdes = new ArrayList<String>();
    String name2;
    static int[] imageResources = new int[]{
            R.drawable.drawing,
            R.drawable.clipboard,
            R.drawable.funnel,
            R.drawable.smartphone,
            R.drawable.chat


    };
    static int[] Strings = new int[]{
            R.string.create,
            R.string.listfroums,
            R.string.filterforums,
            R.string.notific,




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
        setContentView(R.layout.activity_menu);
        SharedPreferences pref3= getSharedPreferences("userdets", MODE_PRIVATE);
        String name= pref3.getString("username", null);
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
       // scheduleAlarm();
        bmb();
        Firebase.setAndroidContext(this);
        read();
        //bottom();
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
    public void scheduleAlarm() {
        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Setup periodic alarm every 5 seconds
        long firstMillis = System.currentTimeMillis(); // alarm is set right away
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        // First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
        // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                1, pIntent);

    }



/*
    public void bottom()
    {
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.t_fav) {
                    // swip();
                }
                if (tabId == R.id.t_near) {
                    // m();
                }
                if (tabId == R.id.t_fri) {
                    //news();
                }
            }
        });
    }*/
    public static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }
    public void m() { /*Intent in= new Intent(this, MenuScreen.class);
        startActivity(in);}*/
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
public  void start()
{

    Intent in = new Intent(this, createforum.class);
    startActivity(in);
}
    public  void start2()
    {

        Intent in = new Intent(this, listforum.class);
        startActivity(in);
    }
    public  void start3()
    {

        Intent in = new Intent(this, Collectorforums.class);
        startActivity(in);
    }

    public  void showfors()
    {

        Intent in = new Intent(this, notific.class);
        startActivity(in);
    }
    public  void list()
    {

        Intent in = new Intent(this, notific.class);
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
