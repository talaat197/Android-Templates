package com.example.alarms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton alarmToggle;
    NotificationManager notificationManager;

    public static final int NOTIFICATION_ID = 0;
    public static final String PRIMARY_CHANNEL = "primary_notification_channel";

    PendingIntent pendingAlarmRecevier;
    AlarmManager alarmManager;
    Intent notifyIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notifyIntent = new Intent(this , AlarmReceiver.class);
        pendingAlarmRecevier = PendingIntent.getBroadcast(this , NOTIFICATION_ID , notifyIntent , PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmToggle = findViewById(R.id.enable_alarm);

        boolean alarmUp = (PendingIntent.getBroadcast(this , NOTIFICATION_ID , notifyIntent , PendingIntent.FLAG_NO_CREATE) != null);
        alarmToggle.setChecked(alarmUp);

        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String toastMessage = "";
                if(isChecked)
                {
                    toastMessage = "Alarm Is On";
                    long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
                    long triggerTime = SystemClock.elapsedRealtime();
                    if(alarmManager != null)
                    {
                        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP , triggerTime , repeatInterval ,pendingAlarmRecevier);
                    }
                }
                else{
                    toastMessage = "Alarm Is Off";
                    notificationManager.cancelAll();
                    if(alarmManager != null)
                    {
                        alarmManager.cancel(pendingAlarmRecevier);
                    }

                }
                Toast.makeText(MainActivity.this , toastMessage , Toast.LENGTH_SHORT).show();
            }
        });

        createNotificationChannel();
    }

    public void createNotificationChannel()
    {
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notify = new NotificationChannel(PRIMARY_CHANNEL , "Stand up notify" , NotificationManager.IMPORTANCE_HIGH);
            notify.enableLights(true);
            notify.setLightColor(Color.BLUE);
            notify.enableVibration(true);
            notify.setDescription("Notifies Every 15 min to stand up and walk");

            notificationManager.createNotificationChannel(notify);
        }
    }
}
