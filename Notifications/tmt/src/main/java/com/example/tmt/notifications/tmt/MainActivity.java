package com.example.tmt.notifications.tmt;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final int NOTIFICATION_ID = 0;
    private static final String ACTION_UPDATE_NOTIFICATION = "com.example.android.tmt.ACTION_UPDATE_NOTIFICATION";
    private NotificationManager notifyManager;
    private  NotificationReceiver notificationReceiver;
    private Button btn_notify , btn_update , btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationReceiver = new NotificationReceiver();
        registerReceiver(notificationReceiver , new IntentFilter(ACTION_UPDATE_NOTIFICATION));


        createNotificationChannel();
        btn_notify = findViewById(R.id.notify_btn);
        btn_cancel = findViewById((R.id.cancel_btn));
        btn_update = findViewById(R.id.update_btn);

        setNotificationButtonState(true , false, false);
    }

    public void cancelNotify(View view) {
        notifyManager.cancel(NOTIFICATION_ID);
        setNotificationButtonState(true , false, false);
    }

    public void notify(View view) {
        sendNotification();
        setNotificationButtonState(false , true, true);
    }

    public void updateMe(View view) {
        updateNotification();
    }

    public void updateNotification()
    {
        Bitmap tmtImage = BitmapFactory.decodeResource(getResources() , R.drawable.tmt_1);

        NotificationCompat.Builder notify = getNotificationBuilder();
        notify.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(tmtImage)
                .setBigContentTitle("Notification Update")
        );

        notifyManager.notify(NOTIFICATION_ID , notify.build());
        setNotificationButtonState(false , false, true);
    }
    /**
     * send notification
     */
    public void sendNotification() {
        Intent broadIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        // to make sure the pending intent is send and used one time use FLAG_ONE_SHOT FLAG
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this , NOTIFICATION_ID , broadIntent , PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();

        notifyBuilder.addAction(R.drawable.ic_update , "Update Notify" ,pendingIntent);

        notifyManager.notify(NOTIFICATION_ID , notifyBuilder.build());
    }

    /**
     * create a channel
     */
    public void createNotificationChannel() {
        notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // check on version
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "TMT Notify", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from TMT");
            notifyManager.createNotificationChannel(notificationChannel);
        }

    }

    /**
     * create a notification builder
     * @return
     */
    private NotificationCompat.Builder getNotificationBuilder()
    {
        Intent intent = new Intent(this , MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this , NOTIFICATION_ID , intent , PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this , PRIMARY_CHANNEL_ID)
                .setContentTitle("you have been notified")
                .setContentText("Notification setting")
                .setSmallIcon(R.drawable.ic_tmt)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        return notifyBuilder;
    }

    void setNotificationButtonState (Boolean isNotifyEnabled , Boolean isUpdateEnabled , Boolean isCancelEnabled)
    {
        btn_notify.setEnabled(isNotifyEnabled);
        btn_update.setEnabled(isUpdateEnabled);
        btn_cancel.setEnabled(isCancelEnabled);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(notificationReceiver);
        super.onDestroy();
    }

    public class NotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            updateNotification();
        }
    }
}




















