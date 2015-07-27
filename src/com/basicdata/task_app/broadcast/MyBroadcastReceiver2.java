package com.basicdata.task_app.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by jky on 15-7-27.
 */
public class MyBroadcastReceiver2 extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.android.myAction")) {
            Toast.makeText(context, "来自其他程序的信息: " + intent.getStringExtra("DATA"), Toast.LENGTH_SHORT).show();
//            this.abortBroadcast();
        }
    }
}
