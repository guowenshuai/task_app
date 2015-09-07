package com.basicdata.task_app.broadcast;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.basicdata.task_app.phoneCall.InterceptPhoneCall_View;

/**
 * Created by jky on 15-8-4.
 */
public class PhoneBroadcastReceiver extends BroadcastReceiver {

    private final static String TAG = "PhoneBroadCastReceiver";
    private static String incoming_number = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "[Broadcast]" + action);

        TelephonyManager telephonyManager
                = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
        switch (telephonyManager.getCallState()) {
            case TelephonyManager.CALL_STATE_RINGING: {
                incoming_number = intent.getStringExtra("incoming_number");
                abortBroadcast();
                myPhoneProcess(context, incoming_number);

                break;
            }
            default:
                Log.d(TAG, "no phone");
                break;
        }
    }

    private void myPhoneProcess(Context mContext, String inComingNumber) {
        Intent intent = new Intent();
        intent.setClass(mContext, InterceptPhoneCall_View.class);
        intent.putExtra("PHONE_NUMBER", inComingNumber);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Log.d("来电话了", "电话电话");
        mContext.startActivity(intent);
    }
}
