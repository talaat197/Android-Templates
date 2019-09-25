package com.example.backgroutasks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fireTask(View view) {
        MyProgressTask progress = new MyProgressTask(this);
        progress.execute();
    }
}
