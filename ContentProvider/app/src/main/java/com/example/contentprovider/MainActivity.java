package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name , profession;
    ContentValues contentValues = new ContentValues();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);
        profession = (EditText)findViewById(R.id.profession);
    }

    public void onSave(View view) {
        contentValues.put("name" , name.getText().toString());
        contentValues.put("profession" , profession.getText().toString());

        Uri uri = getContentResolver().insert(UserProvider.CONTENT_URI_1 , contentValues);
        Toast.makeText(this , uri.toString() , Toast.LENGTH_SHORT).show();
    }

    public void onLoad(View view) {
        Cursor cr = getContentResolver().query(UserProvider.CONTENT_URI_1 , null , null , null , null);
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
