package com.example.sr.broadcastalarmsharedprederences;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button showNotificationButton, stopNotification, alertButton, set;

    NotificationManager nm;

    int notifyID = 33;

    boolean isNotActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showNotificationButton = (Button) findViewById(R.id.notificationButton);
        stopNotification = (Button) findViewById(R.id.stopButton);
        alertButton = (Button) findViewById(R.id.lateButton);
        set = (Button) findViewById(R.id.settingBttn);

    }

    public void showNotification (View v){

        NotificationCompat.Builder notificaion = new NotificationCompat.Builder(this).
                setContentTitle("Title").setContentText("Text").setTicker("ticker").
                setSmallIcon(R.drawable.ic_launcher);

        Intent page = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0,
                page, PendingIntent.FLAG_UPDATE_CURRENT);

        notificaion.setContentIntent(pendingIntent);

        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        nm.notify(notifyID, notificaion.build());

        isNotActive = true;

    }

    public void stopNotification(View v){
        if (isNotActive){nm.cancel(notifyID);}

    }

    public void setAlarm(View v){

        Date time = new Date(System.currentTimeMillis());
        Intent alertIntent = new Intent(this, AlertReceiver.class);
        AlarmManager alarmManager =  (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // note this could be getActivity if you want to launch an activity
        PendingIntent pendingIntent = PendingIntent.getBroadcast( this, 0,  alertIntent,  PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                time.getTime(),
                60000,
                pendingIntent);

    }

    public void setting(View v){

        setAlarm(v);
        Intent goToSetting = new Intent (this, Settings.class);
        startActivity(goToSetting);

    }

    public void newSettings(View v){

        Intent goToSetting = new Intent (this, SettingsUpdated.class);
        startActivity(goToSetting);

    }
}
