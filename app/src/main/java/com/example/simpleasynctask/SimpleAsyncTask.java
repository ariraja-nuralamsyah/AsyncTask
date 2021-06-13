package com.example.simpleasynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Integer,Integer,String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;
    private WeakReference<TextView> mTextView2;
    SimpleAsyncTask(TextView tv, TextView tv2, ProgressBar pb) {
        mTextView = new WeakReference<>(tv);
        mTextView2 = new WeakReference<>(tv2);
        mProgressBar = new WeakReference<>(pb);
    }
    @Override
    protected String doInBackground(Integer... integers) {

        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int maxWait = n * 1000;
        int pWait = maxWait / integers[0];
        // Sleep for the random amount of time
        for(int count = 0; count <= integers[0]; count++){
            try {
                Thread.sleep(pWait);
                publishProgress(count);;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Return a String result
        return "Task Complete after waiting " + maxWait/1000 + " seconds!";
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
        mProgressBar.get().setVisibility(View.GONE);
        mTextView2.get().setText("Task Complete");
    }

    protected void onProgressUpdate(Integer... progress) {
        mTextView2.get().setText(progress[0]+"%");
        mProgressBar.get().setProgress(progress[0]);
    }

    @Override
    protected void onPreExecute() {
        mTextView2.get().setText("Task Starting...");
    }
}
