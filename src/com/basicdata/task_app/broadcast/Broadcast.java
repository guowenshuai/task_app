package com.basicdata.task_app.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.basicdata.task_app.R;

/**
 * Created by jky on 15-7-27.
 */

public class Broadcast extends Activity {

    MyBroadcastReceiver myBroadcastReceiver = null;
    Intent intent = null;
    private static final String TAG = "Broadcast";
    private static boolean FLAG_BROADCAST = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast);

        findViews();
        setListeners();
    }

    private Button btn_register_broadcast;
    private Button btn_send_broadcast;
    private Button btn_unregister_broadcast;
    private Button btn_send_broadcast_asy;
    private Button btn_cancel_broadcast;

    private void findViews() {
        btn_register_broadcast = (Button) findViewById(R.id.button_register_broadcast);
        btn_send_broadcast = (Button) findViewById(R.id.button_send_broadcast);
        btn_unregister_broadcast = (Button) findViewById(R.id.button_unregister_broadcast);
        btn_send_broadcast_asy = (Button) findViewById(R.id.button_send_broadcast_asynchronous);
        btn_cancel_broadcast = (Button) findViewById(R.id.button_cancel_broadcast);
    }

    private void setListeners() {


        /*注册广播接受器*/
        btn_register_broadcast.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (FLAG_BROADCAST) {
                    try {
                        FLAG_BROADCAST = false;
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction("com.android.myAction");
                        myBroadcastReceiver = new MyBroadcastReceiver();
                        registerReceiver(myBroadcastReceiver, intentFilter);

                        Log.d(TAG, "register_broadcast");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, "You already has a broadCastReceiver");
                }
            }
        });
        /*发送广播*/
        btn_send_broadcast.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    intent = new Intent("com.android.myAction");
                    intent.putExtra("DATA", "我是普通广播");
                    sendBroadcast(intent);
//                sendOrderedBroadcast(intent, null);
                    Log.d(TAG, "send_broadcast");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        /*取消广播接收器*/
        btn_unregister_broadcast.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!FLAG_BROADCAST) {
                    try {
                        unregisterReceiver(myBroadcastReceiver);
                        Log.d(TAG, "unregister_broadcast");
                        FLAG_BROADCAST = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, "You have no broadCastReceiver need to unregister");
                }
            }
        });

        btn_send_broadcast_asy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    intent = new Intent("com.android.myAction");
                    intent.putExtra("DATA", "我是异步广播");
                    Broadcast.this.sendStickyBroadcast(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn_cancel_broadcast.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    removeStickyBroadcast(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

