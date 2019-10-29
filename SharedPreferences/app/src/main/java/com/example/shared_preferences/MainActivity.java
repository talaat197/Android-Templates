package com.example.shared_preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String sharedFileName = "com.example.shared_preferences.tmt";
    EditText name , age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.name_editText);
        age = (EditText)findViewById(R.id.age_editText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences( sharedFileName, MODE_APPEND);
        String keyName = sharedPreferences.getString("name" , "");
        int keyAge = sharedPreferences.getInt("age" , 0);

        name.setText(keyName);
        age.setText(String.valueOf(keyAge));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences(sharedFileName , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name" , name.getText().toString());
        editor.putInt("age" , Integer.parseInt(age.getText().toString()));
        editor.apply(); // commit your transaction
    }
}
