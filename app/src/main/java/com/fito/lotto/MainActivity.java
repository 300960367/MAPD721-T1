package com.fito.lotto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final String mBroadcastStringAction = "com.fito.lotto.string";
    public static final String mBroadcastIntegerAction = "com.fito.lotto.integer";
    public static final String mBroadcastArrayListAction = "com.fito.lotto.arraylist";
    private TextView mTextView;
    private IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(mBroadcastStringAction);
        mIntentFilter.addAction(mBroadcastIntegerAction);
        mIntentFilter.addAction(mBroadcastArrayListAction);
    }

    // When Generate Number button is clicked, calls startClicked()
    public void startClicked(View view) {
        Toast.makeText(MainActivity.this, "Service Started - Your number: 300960367.",Toast.LENGTH_LONG).show();
        Intent serviceIntent = new Intent(this, BroadcastService.class);
        startService(serviceIntent);
    }

    // When Stop Service button is clicked, calls stopClicked()
    public void stopClicked(View view) {
        Intent stopIntent = new Intent(MainActivity.this,
                BroadcastService.class);
        stopService(stopIntent);
        Toast.makeText(MainActivity.this, "Service Stopped.",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Random r = new Random();
            int i1 = (r.nextInt(49 - 1) + 1);

            mTextView.setText(mTextView.getText()
                    + "Your number: 300960367\n"
                    + "Generated number: " + i1 + "\n\n");
/*            if (intent.getAction().equals(mBroadcastStringAction)) {
                mTextView.setText(mTextView.getText()
                        + intent.getStringExtra("Data") + "\n\n");
            } else if (intent.getAction().equals(mBroadcastIntegerAction)) {
                mTextView.setText(mTextView.getText().toString()
                        + intent.getIntExtra("Data", 0) + "\n\n");
            } else if (intent.getAction().equals(mBroadcastArrayListAction)) {
                mTextView.setText(mTextView.getText()
                        + intent.getStringArrayListExtra("Data").toString()
                        + "\n\n");
            }*/
        }
    };

    @Override
    protected void onPause() {
        unregisterReceiver(mReceiver);
        super.onPause();
    }
}
