package com.basicdata.task_app.phoneCall;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by jky on 15-7-31.
 */
public class InterceptPhoneCall_Listener extends PhoneStateListener{

    private Context mContext;
    private static String inComingNumber;

    public InterceptPhoneCall_Listener(Context context) {
        this.mContext = context;
    }

    String result = "";
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        this.inComingNumber = incomingNumber;
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                result += "手机空闲起来了";
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                result += "手机铃声响了，来电号码：" +
                        incomingNumber;

                myPhoneProcess();
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                result += "电话被挂起了";
                break;
            default:
                break;
        }
        super.onCallStateChanged(state, incomingNumber);
    }

    private void myPhoneProcess() {
        Intent intent = new Intent();
        intent.setClass(mContext, InterceptPhoneCall_View.class);
        intent.putExtra("PHONE_NUMBER", inComingNumber);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Log.d("来电话了", "电话电话");
        mContext.startActivity(intent);
    }
}
