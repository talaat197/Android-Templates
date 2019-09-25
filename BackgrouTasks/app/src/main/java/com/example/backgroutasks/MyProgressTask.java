package com.example.backgroutasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class MyProgressTask extends AsyncTask<String , Integer , String> {
    Context global_ref;
    ProgressDialog loader;
    public MyProgressTask(Context ref)
    {
        global_ref = ref;
    }

    @Override
    protected void onPreExecute()
    {
        loader = new ProgressDialog(global_ref);
        loader.setTitle("Loading...");
        loader.setMessage("PLease Wait");
        loader.setMax(10);
        loader.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        loader.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                cancel(true);
            }
        });
        loader.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        for(int counter = 1; counter <= 10 ; counter++)
        {
            try {
                Thread.sleep(5000);
                // let's talk to onProgressUpdate
                publishProgress(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.i("exception",e.getMessage());
                return "failure";
            }
        }
        return "Done!";
    }

    @Override
    protected void onPostExecute(String message)
    {
        Toast.makeText(global_ref , message , Toast.LENGTH_LONG).show();
        loader.dismiss();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        loader.setProgress(values[0]);
    }
}
