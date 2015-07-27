package com.basicdata.task_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by jky on 15-7-27.
 */
public class MyBroadcastReceiverForBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent mBootIntent = new Intent(context, Main.class);
        mBootIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mBootIntent);
    }
}
