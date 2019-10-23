package com.example.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    public static final int JOB_ID = 1;
    ToggleButton tg_btn;
    JobScheduler jobScheduler;
    JobInfo jobInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jobScheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);

        tg_btn = (ToggleButton)findViewById(R.id.tg_btn);
        tg_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    JobInfo.Builder jobInfoBuilder = new JobInfo.Builder(JOB_ID , new ComponentName(getPackageName() , MyService.class.getName()))
                            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                            .setRequiresDeviceIdle(false);
                    jobInfo = jobInfoBuilder.build();
                    jobScheduler.schedule(jobInfo);
                }
                else
                {
                    jobScheduler.cancelAll();
                }
            }
        });
    }

}
