package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class DefaultService extends Service {
    public DefaultService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // long running code
        Toast.makeText(this , "Service Started" , Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this , "Service Stopped" , Toast.LENGTH_SHORT).show();

    }
}
