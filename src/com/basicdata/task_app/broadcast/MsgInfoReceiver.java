package com.basicdata.task_app.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/*过滤短信接受的广播接收器*/
public class MsgInfoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Log.d("MsgInfoReceiver", "接收到短信");
        }
    }
}
