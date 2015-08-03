package com.basicdata.task_app.phoneCall;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.basicdata.task_app.R;
import com.basicdata.task_app.aidl.ITelephony;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jky on 15-7-31.
 */
public class InterceptPhoneCall_View extends Activity{

    private static String inComingNumber;
    private static final String TAG = "CALL_VIEW";
    private static Context mContext = null;
    ITelephony iTelephony = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interceptphonecall_view);

        Bundle extras = getIntent().getExtras();
        inComingNumber = extras != null ? extras.getString("PHONE_NUMBER") : "10086";

        mContext = this;
        findView();
        setListeners();
//        try {
//            iTelephony = getPhoneInterface(mContext);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        try {
            getPhoneInterface(mContext);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private Button btn_end_call;
    private Button btn_answer_call;
    private Button btn_res_message;

    private void findView() {

        btn_end_call = (Button)
                findViewById(R.id.button_end_call);
        btn_answer_call = (Button)
                findViewById(R.id.button_answer_call);
        btn_res_message = (Button)
                findViewById(R.id.button_res_message);
    }

    private static ITelephony getPhoneInterface(Context context) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Log.d(TAG, "Get Phone Number" +
                inComingNumber);
        TelephonyManager mTelephonyManger = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String IMEL = mTelephonyManger.getDeviceId();
        Toast.makeText(mContext, "取得设备的IMEI：" + IMEL, Toast.LENGTH_LONG).show();

        Class c = TelephonyManager.class;
        Method getITelephonyMethod = null;
        try {
            getITelephonyMethod = c.getDeclaredMethod("getITelephony", (Class[])null);
            getITelephonyMethod.setAccessible(true);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        ITelephony iTelephony = null;
        try {
            iTelephony = (ITelephony) getITelephonyMethod.invoke(mTelephonyManger, (Object[])null);
            return iTelephony;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
     /*   Method getITelephonyMethod = mTelephonyManger.getClass().getDeclaredMethod("getITelephony");
        getITelephonyMethod.setAccessible(true);*/

        return iTelephony;
    }

    private void setListeners() {
        btn_end_call.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iTelephony.endCall();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_answer_call.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iTelephony.answerRingingCall();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_res_message.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}