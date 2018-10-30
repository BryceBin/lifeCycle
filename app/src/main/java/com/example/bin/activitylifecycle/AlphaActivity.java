package com.example.bin.activitylifecycle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.res.Resources;
import android.graphics.Color;
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
import org.w3c.dom.Text;

public class AlphaActivity extends AppCompatActivity {
    private static TextView mMethodListTextView;
    private TextView mStatusTextView;
    private TextView mNameTextView;
    private Button mStartBuuton1;
    private Button mStartButton2;
    private Button mFinishButton;
    private Button mDialogButton;
    private IntentFilter intentFilter;
    private messageReceiver mMessageReceiver;
    private Context mContext;
    public static final String TAG = "Alpha";

    public static String msg="";
    public static int[] status = {0,0,0};
    public static final String[] statusMsg={"",":Created\n",":Started\n",":Resumed\n",":Paused\n",":Stopped\n",":Destroyed\n"};

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置背景颜色
        this.getWindow().setBackgroundDrawableResource(R.color.colorAlpha);
        setContentView(R.layout.activity_alpha);

        //设置LifeCycle Method List
        mMethodListTextView = findViewById(R.id.methodList);
        mMethodListTextView.setBackgroundResource(R.color.White);//背景颜色设置
        mMethodListTextView.setMovementMethod(ScrollingMovementMethod.getInstance());

        //设置Activity Status
        mStatusTextView = findViewById(R.id.status);
        mStatusTextView.setBackgroundResource(R.color.White);

        mNameTextView = findViewById(R.id.nameTextView);
        mNameTextView.setText(R.string.alphaActivity);

        mStartBuuton1 = findViewById(R.id.startButton1);
        mStartBuuton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlphaActivity.this,BravoActivity.class);
                startActivity(intent);
            }
        });

        mStartButton2 = findViewById(R.id.startButton2);
        mStartButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlphaActivity.this,CharlieActivity.class);
                startActivity(intent);
            }
        });

        mFinishButton = findViewById(R.id.finishButton);
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
                startActivity(new Intent(AlphaActivity.this,DialogActivity.class));
            }
        });

        mContext = getApplicationContext();
        //注册广播接收器
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.bin.activityliftcyvle");
        mMessageReceiver = new messageReceiver();
        registerReceiver(mMessageReceiver, intentFilter);

        Log.i(TAG, "onCreate: create alpha");
        status[0]=1;
        sentMessage(TAG+".onCreate()\n");

    }

    private class messageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(!msg.isEmpty())mMethodListTextView.setText(msg);
            String tmp=BravoActivity.setStatus();
            mStatusTextView.setText(tmp);
            Log.i(TAG, "onReceive: testtest");
        }
    }

    private void sentMessage(String s){
        msg+=s;
        Intent intent = new Intent("com.example.bin.activityliftcyvle");
        //intent.putExtra("key",s);
        mContext.sendBroadcast(intent);
        Log.i(TAG, "sentMessage: test here");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: start alpha");
        status[0]=2;
        sentMessage(TAG+".onStart()\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: resume alpha");
        status[0]=3;
        sentMessage(TAG+".onResume()\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: pause alpha");
        status[0]=4;
        sentMessage(TAG+".onPause()\n");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: stop alpha");
        status[0]=5;
        sentMessage(TAG+".onStop()\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: destroy alpha");
        status[0]=0;
        sentMessage(TAG+".onDestroy()\n");
        unregisterReceiver(mMessageReceiver);//记得注销广播接收器
    }
}
