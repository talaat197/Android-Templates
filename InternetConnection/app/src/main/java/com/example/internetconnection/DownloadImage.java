package com.example.internetconnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImage extends AsyncTask<String , Void , Bitmap> {
    private Context context;
    public DownloadImage(Context context)
    {
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap image = null;
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(20000);
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream response = connection.getInputStream();

            image = BitmapFactory.decodeStream(response);
        } catch (Exception e) {
            Log.i("exception" , e.getMessage());
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void onPostExecute(Bitmap image)
    {
        MainActivity.imageView.setImageBitmap(image);
    }
}
