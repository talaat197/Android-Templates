package com.example.contentprovider_2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Uri CONTENT_URI = Uri.parse("content://com.tmt.my.user.provider/user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLoad(View view) {
        Cursor cr = getContentResolver().query(CONTENT_URI , null , null , null , null);
        StringBuilder stringBuilder = new StringBuilder();
        while(cr.moveToNext())
        {
            int id = cr.getInt(0);
            String name = cr.getString(1);
            String profession = cr.getString(2);
            stringBuilder.append(id+"   "+name+"      "+profession+"\n");
        }
        Toast.makeText(this , stringBuilder , Toast.LENGTH_LONG).show();
    }
}
