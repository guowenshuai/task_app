package com.basicdata.task_app.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.android.myAction")) {
            Log.d("MyBroadcastClass", "myBroadcastClass");
            Toast.makeText(context, "接收到广播信息： " + intent.getStringExtra("DATA"), Toast.LENGTH_SHORT).show();
        }
    }
}
