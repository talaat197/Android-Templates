package com.example.broadcast2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        registerReceiver(new CustomBroad02() , new IntentFilter("com.example.broadcast2.TMT.ACTION_CUSTOM"));
    }
}
