package com.example.internetconnection;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InternetConnection extends AsyncTask<String , Void , String> {
    private Context context;
    public InternetConnection(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String htmlPage = null;
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(20000);
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream response = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response));
            StringBuilder buildString = new StringBuilder();
            String line = "";
            while((line = bufferedReader.readLine()) != null)
            {
                buildString.append(line+"\n");
            }
            bufferedReader.close();
            response.close();

            htmlPage = buildString.toString();
        } catch (Exception e) {
            Log.i("exception" , e.getMessage());
            e.printStackTrace();
        }
        return htmlPage;
    }

    @Override
    protected void onPostExecute(String s)
    {
        MainActivity.viewWebPage.setText(s);
    }
}
