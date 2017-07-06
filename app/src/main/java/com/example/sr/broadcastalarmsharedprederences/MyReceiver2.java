package com.example.sr.broadcastalarmsharedprederences;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import java.util.Random;


public class MyReceiver2 extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences values = PreferenceManager.
                getDefaultSharedPreferences(context);

        if (values.getBoolean("warning", false)) {

            String msg = "";

            if (values.getBoolean("makeItRain", false)) {
                msg = "MAKE IT RAIN new APP!!  ";
            }


            int id = randomId();

            PendingIntent notificIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, MainActivity.class), 0);

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle("Repeating new APP: "+ msg)
                            .setTicker("Ticker")
                            .setContentText(msg + id);

            mBuilder.setContentIntent(notificIntent);

            mBuilder.setAutoCancel(true);

            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(id, mBuilder.build());

        }
    }

    private int randomId(){

        Random rand = new Random();
        return  (rand.nextInt(100000) + 1000);


    }


}


