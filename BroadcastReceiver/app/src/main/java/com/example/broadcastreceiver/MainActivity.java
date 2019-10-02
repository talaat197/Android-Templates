package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(new BatteryReceiver() , new IntentFilter(Intent.ACTION_BATTERY_LOW));
        registerReceiver(new CustomBroad() , new IntentFilter("com.example.broadcast2.TMT.ACTION_CUSTOM"));
    }

    /**
     * must invoke the broadcast receiver
     * @param view
     */
    public void onPressBroad(View view) {
        Intent firstIntent = new Intent();
        firstIntent.setAction("com.example.broadcast2.TMT.ACTION_CUSTOM");
        sendBroadcast(firstIntent);
    }
}
