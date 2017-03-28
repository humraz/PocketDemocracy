package com.example.humraz.pockd.notifications;

import android.Manifest;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.humraz.pockd.MainActivity;
import com.example.humraz.pockd.R;
import com.example.humraz.pockd.forum;
import com.example.humraz.pockd.notif;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MyTestService extends IntentService {
    //public int notificationFlag = 0;

    public MyTestService() {
        super("MyTestService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println("started");
        Firebase.setAndroidContext(this);

find();

    }
String f;
    String g;


    public void find() {

        final Firebase ref = new Firebase("https://pockeddmo-7e844.firebaseio.com/notif");
        //Value event listener for realtime data update


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot usersSnapshot) {

                for (DataSnapshot userSnapshot : usersSnapshot.getChildren()) {
                    notif noo = userSnapshot.getValue(notif.class);

                    f = noo.getNotif().toString();
                    g= noo.getMsg().toString();

                }

                if(f.equals("true"))
                {
                    sendNotification(g);
                    update();

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }

    public void update()
    {


        Firebase ref = new Firebase("https://pockeddmo-7e844.firebaseio.com/notif");
        notif person = new notif();
        person.setMsg("End");
        person.setNotif("false");
        ref.push().setValue(person);

    }
        private NotificationManager mNotificationManager;
    public static final int NOTIFICATION_ID = 1;


    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("New Notification")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg)
                            //    .setSound(Uri.parse("android.resource://"
                              //          + getApplicationContext().getPackageName() + "/" + R.raw.s))
                                        //  .setSound(R.raq)
                        .setSmallIcon(R.drawable.chat);



     //   AudioManager am;
       // am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        //am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        //int volume = am.getStreamVolume(AudioManager.STREAM_ALARM);
       // am.setStreamVolume(AudioManager.STREAM_ALARM, 6,AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

}
