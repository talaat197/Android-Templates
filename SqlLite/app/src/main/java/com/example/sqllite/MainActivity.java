package com.example.sqllite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name , profession;
    CoreDatabase coreDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.name);
        profession = (EditText)findViewById(R.id.profession);
        coreDatabase = new CoreDatabase(this);
    }

    public void onSave(View view) {
        coreDatabase.insertData(name.getText().toString() , profession.getText().toString());
    }

    public void onLoad(View view) {
        String userData[] = coreDatabase.getAll();
        Toast.makeText(this , userData[0]+"    "+userData[1] , Toast.LENGTH_LONG).show();
        name.setText(userData[0]);
        profession.setText(userData[1]);
    }
}
