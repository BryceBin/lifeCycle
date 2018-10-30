package com.example.bin.activitylifecycle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
        builder.setMessage("Simple Dialog");
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        //在dialog区域外点击不会返回
        builder.setCancelable(false);
        builder.show();
    }
}
