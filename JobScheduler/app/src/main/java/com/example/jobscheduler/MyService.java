package com.example.jobscheduler;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class MyService extends JobService {
    public MyService() {
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i("JOB"  , "STARTED");
        Toast.makeText(this , "Job Started" , Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i("JOB"  , "STOPPED");
        Toast.makeText(this , "Job STOPPED" , Toast.LENGTH_SHORT).show();
        return false;
    }
}
