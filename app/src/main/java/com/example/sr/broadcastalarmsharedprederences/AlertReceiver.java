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

import static android.R.attr.id;

/**
 * Created by sr on 2017-06-27.
 */

public class AlertReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        createNotification(context);

    }

    public void createNotification(Context context){

        SharedPreferences values = PreferenceManager.
                getDefaultSharedPreferences(context);

        if (values.getBoolean("notification_pref", false)) {

            String msg = "";

            if(values.getBoolean("make_it_Rain", false)){
                msg = "MAKE IT RAIN!  ";
            }


            int id = randomId();

            PendingIntent notificIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, MainActivity.class), 0);

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle("Repeating")
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
        int  n = rand.nextInt(100000) + 1000;
        return n;

    }
}
