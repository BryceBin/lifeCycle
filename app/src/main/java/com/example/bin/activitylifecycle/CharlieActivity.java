package com.example.bin.activitylifecycle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CharlieActivity extends AppCompatActivity {
    private TextView mMethodListTextView;
    private TextView mStatusTextView;
    private TextView mNameTextView;
    private Button mStartBuuton1;
    private Button mStartButton2;
    private Button mFinishButton;
    private Button mDialogButton;
    private IntentFilter intentFilter;
    private messageReceiver mMessageReceiver;
    private Context mContext;
    public static final String TAG = "Charlie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setBackgroundDrawableResource(R.color.colorCharlie);
        setContentView(R.layout.activity_alpha);

        //设置LifeCycle Method List
        mMethodListTextView = findViewById(R.id.methodList);
        mMethodListTextView.setBackgroundResource(R.color.White);//背景颜色设置
        mMethodListTextView.setMovementMethod(ScrollingMovementMethod.getInstance());

        //设置Activity Status
        mStatusTextView = findViewById(R.id.status);
        mStatusTextView.setBackgroundResource(R.color.White);

        mNameTextView = findViewById(R.id.nameTextView);
        mNameTextView.setText(R.string.charlieActivity);

        mStartBuuton1 = findViewById(R.id.startButton1);
        mStartBuuton1.setText(R.string.startA);
        mStartBuuton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CharlieActivity.this,AlphaActivity.class);
                startActivity(intent);
            }
        });

        mStartButton2 = findViewById(R.id.startButton2);
        mStartButton2.setText(R.string.startB);
        mStartButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CharlieActivity.this,BravoActivity.class);
                startActivity(intent);
            }
        });

        mFinishButton = findViewById(R.id.finishButton);
        mFinishButton.setText(R.string.finishC);
        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDialogButton = findViewById(R.id.dialogButton);
        mDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CharlieActivity.this,DialogActivity.class));
            }
        });

        mContext = getApplicationContext();
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.bin.activityliftcyvle");
        mMessageReceiver = new messageReceiver();
        registerReceiver(mMessageReceiver, intentFilter);

        Log.i(TAG, "onCreate: create bravo");
        AlphaActivity.status[2]=1;
        sentMessage(TAG+".onCreate()\n");
    }

    private class messageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            mMethodListTextView.setText(AlphaActivity.msg);
            String tmp=BravoActivity.setStatus();
            mStatusTextView.setText(tmp);
        }
    }

    private void sentMessage(String s){
        AlphaActivity.msg += s;
        Intent intent = new Intent("com.example.bin.activityliftcyvle");
        mContext.sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: start bravo");
        AlphaActivity.status[2]=2;
        sentMessage(TAG+".onStart()\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: resume bravo");
        AlphaActivity.status[2]=3;
        sentMessage(TAG+".onResume()\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: pause bravo");
        AlphaActivity.status[2]=4;
        sentMessage(TAG+".onPause()\n");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: stop bravo");
        AlphaActivity.status[2]=5;
        sentMessage(TAG+".onPause()\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: destroy bravo");
        AlphaActivity.status[2]=0;
        sentMessage(TAG+".onDestroy()\n");
        unregisterReceiver(mMessageReceiver);
    }
}
