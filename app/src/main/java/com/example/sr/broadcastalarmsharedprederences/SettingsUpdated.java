package com.example.sr.broadcastalarmsharedprederences;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.util.Date;

public class SettingsUpdated extends AppCompatActivity {

    public CheckBox warning;
    public CheckBox makeItRain;
    private boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_updated);

        warning = (CheckBox) findViewById(R.id.checkBox);
        makeItRain = (CheckBox) findViewById(R.id.checkBox2);

        boolean warningCheck = PreferenceManager.getDefaultSharedPreferences(this).
                getBoolean("warning", false);
        boolean rainCheck = PreferenceManager.getDefaultSharedPreferences(this).
                getBoolean("makeItRain", false);
        warning.setChecked(warningCheck);
        makeItRain.setChecked(rainCheck);

    }

    public void setAlarm(View v){

        check = warning.isChecked();
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putBoolean("warning", check).apply();
        alarm();
    }

    public void MakeItRain(View v){
        check = makeItRain.isChecked();
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putBoolean("makeItRain", check).apply();

    }

    public void alarm(){
        Date time = new Date(System.currentTimeMillis());
        Intent alertIntent = new Intent(this, MyReceiver2.class);
        AlarmManager alarmManager =  (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // note this could be getActivity if you want to launch an activity
        PendingIntent pendingIntent = PendingIntent.getBroadcast( this, 0,  alertIntent,  PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                time.getTime(),
                60000,
                pendingIntent);

    }


}
