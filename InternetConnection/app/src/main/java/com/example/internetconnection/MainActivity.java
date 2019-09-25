package com.example.internetconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static TextView viewWebPage;
    public static ImageView imageView;
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        viewWebPage = (TextView)findViewById(R.id.view_html);
        imageView = (ImageView)findViewById(R.id.downloadedIMage);
    }

    public void connectWebPage(View view) {
        InternetConnection internetConnection = new InternetConnection(this);
        internetConnection.execute("https://picwpost.com/");
    }

    public void downloadImage(View view) {
        if(networkInfo != null && networkInfo.isConnected())
        {
            DownloadImage downloadImage = new DownloadImage(this);
            downloadImage.execute("https://www.w3schools.com/w3css/img_lights.jpg");
        }
        else
        {
            Toast.makeText(this , "Check Your Connection" , Toast.LENGTH_LONG);
        }
    }
}
