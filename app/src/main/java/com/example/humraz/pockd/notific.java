package com.example.humraz.pockd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;

public class notific extends AppCompatActivity {
ArrayList<String> not= new ArrayList<String>();
    ArrayList<String> user = new ArrayList<String>();
    ArrayList<Card> cards = new ArrayList<Card>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notific);
        //= (TextView) findViewById(R.id.tv);
        makeJsonArrayRequest();
    }


    public void makeJsonArrayRequest() {

        final String URL = "http://192.168.43.30:8000/snippets/";

        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        try {
                            // Parsing json array response
                            // loop through each json object
                            String jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject cord = (JSONObject) response.get(i);


                                String use = cord.getString("code");
                                String cont = cord.getString("title");

                                user.add(use);
                                not.add(cont);

                                //jsonResponse += "lat: " + lat + "\n\n";
                                //jsonResponse += "long: " + longg + "\n\n";


                            }

                          //  tv.setText(jsonResponse);
                            cardpop();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }


    public void cardpop()
    {
        for(int i=0;i<not.size();i++)
        {
            Card card = new Card(getApplicationContext());
            final String name= user.get(i);
            final String jame=not.get(i);


            //String s= name.concat("Asdasdasd");
            // blah(s);
            //Create a CardHeader
            CardHeader header = new CardHeader(getApplicationContext());
            header.setTitle(jame);
            card.setTitle(name);
            //Add Header to card

            //header.setOtherButtonVisible(true);
            //header.setOtherButtonDrawable(R.drawable.pencil);
            //Add a callback
            header.setOtherButtonClickListener(new CardHeader.OnClickCardHeaderOtherButtonListener() {
                @Override
                public void onButtonItemClick(Card card, View view) {
                   // name2=name;
                    //find(name);
                }
            });
            card.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
               //     name2=name;
                 //   find(name);
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

}
