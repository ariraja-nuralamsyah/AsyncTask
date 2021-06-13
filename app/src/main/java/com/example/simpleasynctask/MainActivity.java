package com.example.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private TextView mTextView2;
    private ProgressBar mProgressBar;
    Integer count =1;
    private static final String TEXT_STATE = "currentText";
    private static final String PROGRESS_STATE = "0";
    private static final String TEXT_PROGRESS_STATE = "progressText";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView1);
        mTextView2 = findViewById(R.id.persentase);
        mProgressBar = findViewById(R.id.progress);
        mProgressBar.setMax(100);

        if(savedInstanceState!=null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
            mTextView2.setText(savedInstanceState.getString(TEXT_PROGRESS_STATE));
            mProgressBar.setProgress(savedInstanceState.getInt(PROGRESS_STATE));
        }
    }

    public void startTask(View view) {
        count = 1;
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setProgress(0);
        mTextView.setText("Loading...");
        new SimpleAsyncTask(mTextView, mTextView2, mProgressBar).execute(100);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(TEXT_STATE,
                mTextView.getText().toString());
        outState.putString(TEXT_PROGRESS_STATE,
                mTextView2.getText().toString());
        outState.putInt(PROGRESS_STATE, mProgressBar.getProgress());
    }
}