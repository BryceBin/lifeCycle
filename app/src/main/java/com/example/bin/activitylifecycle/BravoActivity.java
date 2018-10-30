package com.example.bin.activitylifecycle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BravoActivity extends AppCompatActivity {
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
    public static final String TAG = "Bravo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置背景颜色
        Resources resources = getResources();
        Drawable drawable = resources.getDrawable(R.drawable.bgcolor_A);
        this.getWindow().setBackgroundDrawable(drawable);
        setContentView(R.layout.activity_alpha);

        //设置LifeCycle Method List
        mMethodListTextView = findViewById(R.id.methodList);
        mMethodListTextView.setBackgroundResource(R.color.White);//背景颜色设置
        mMethodListTextView.setMovementMethod(ScrollingMovementMethod.getInstance());

        //设置Activity Status
        mStatusTextView = findViewById(R.id.status);
        mStatusTextView.setBackgroundResource(R.color.White);

        mNameTextView = findViewById(R.id.nameTextView);
        mNameTextView.setText(R.string.bravoActivity);

        mStartBuuton1 = findViewById(R.id.startButton1);
        mStartBuuton1.setText(R.string.startA);
        mStartBuuton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BravoActivity.this,AlphaActivity.class);
                startActivity(intent);
            }
        });

        mStartButton2 = findViewById(R.id.startButton2);
        mStartButton2.setText(R.string.startC);
        mStartButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BravoActivity.this,CharlieActivity.class);
                startActivity(intent);
            }
        });

        mFinishButton = findViewById(R.id.finishButton);
        mFinishButton.setText(R.string.finishB);
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
                startActivity(new Intent(BravoActivity.this,DialogActivity.class));
            }
        });

        mContext = getApplicationContext();
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.bin.activityliftcyvle");
        mMessageReceiver = new messageReceiver();
        registerReceiver(mMessageReceiver, intentFilter);
        Log.i(TAG, "onCreate: create bravo");
        AlphaActivity.status[1]=1;
        sentMessage(TAG+".onCreate()\n");
    }

    private class messageReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            //mMethodListTextView.append(intent.getExtras().get("key")+"");
            mMethodListTextView.setText(AlphaActivity.msg);
            String tmp=setStatus();
            mStatusTextView.setText(tmp);
        }
    }

    public static String setStatus(){
        String tmp="";
        if(AlphaActivity.status[0]!=0)tmp+=(AlphaActivity.TAG+AlphaActivity.statusMsg[AlphaActivity.status[0]]);
        if(AlphaActivity.status[1]!=0)tmp+=(BravoActivity.TAG+AlphaActivity.statusMsg[AlphaActivity.status[1]]);
        if(AlphaActivity.status[2]!=0)tmp+=(CharlieActivity.TAG+AlphaActivity.statusMsg[AlphaActivity.status[2]]);
        return tmp;
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
        AlphaActivity.status[1]=2;
        sentMessage(TAG+".onStart()\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: resume bravo");
        AlphaActivity.status[1]=3;
        sentMessage(TAG+".onResume()\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: pause bravo");
        AlphaActivity.status[1]=4;
        sentMessage(TAG+".onPause()\n");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: stop bravo");
        AlphaActivity.status[1]=5;
        sentMessage(TAG+".onStop()\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: destroy bravo");
        AlphaActivity.status[1]=0;
        sentMessage(TAG+".onDestroy()\n");
        unregisterReceiver(mMessageReceiver);
    }
}
