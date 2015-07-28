package com.basicdata.task_app.broadcast;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.basicdata.task_app.R;

import java.util.Iterator;
import java.util.List;

/**
 * Created by jky on 15-7-27.
 */

public class Broadcast extends Activity {

    MyBroadcastReceiver myBroadcastReceiver = null;
    Intent intent = null;
    private static final String TAG = "Broadcast";
    private static boolean FLAG_BROADCAST = true;

    /*过滤电量改变的广播接受器*/
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                int intLevel = intent.getIntExtra("level", 0);
                int intScale =intent.getIntExtra("scale", 100);
                updateView(intLevel, intScale);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast);

        findViews();
        setListeners();
    }

    @Override
    protected void onStart() {
        registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        List<ActivityManager.RunningServiceInfo> serviceList = getRunningServicesList(this);
        Iterator<ActivityManager.RunningServiceInfo> l = serviceList.iterator();
        String str = "";
        while (l.hasNext()) {
            ActivityManager.RunningServiceInfo serviceInfo = (ActivityManager.RunningServiceInfo) l.next();
            str = str + "\n" + serviceInfo.service.getClassName();
            text_show_running_service.setText(str);
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(mBatInfoReceiver);
        super.onStop();
    }

    private void updateView(int intLevel, int intScale) {
        int percent = intLevel * 100 /intScale;
        if (percent < 5) {
            view_battery.setImageResource(R.drawable.stat_sys_battery_0);
        } else if(percent < 10) {
            view_battery.setImageResource(R.drawable.stat_sys_battery_10);
        } else if (percent < 20) {
            view_battery.setImageResource(R.drawable.stat_sys_battery_20);
        } else if (percent < 40) {
            view_battery.setImageResource(R.drawable.stat_sys_battery_40);
        } else if (percent < 60) {
            view_battery.setImageResource(R.drawable.stat_sys_battery_60);
        } else if (percent < 80) {
            view_battery.setImageResource(R.drawable.stat_sys_battery_80);
        } else if (percent <= 100) {
            view_battery.setImageResource(R.drawable.stat_sys_battery_100);
        }
        text_msg_battery.setText("电量剩余：" + String.valueOf(percent) + "%");
    }

    private Button btn_register_broadcast;
    private Button btn_send_broadcast;
    private Button btn_unregister_broadcast;
    private Button btn_send_broadcast_asy;
    private Button btn_cancel_broadcast;
    private ImageView view_battery;
    private TextView text_msg_battery;
    private TextView text_show_running_service;


    private void findViews() {
        btn_register_broadcast = (Button) findViewById(R.id.button_register_broadcast);
        btn_send_broadcast = (Button) findViewById(R.id.button_send_broadcast);
        btn_unregister_broadcast = (Button) findViewById(R.id.button_unregister_broadcast);
        btn_send_broadcast_asy = (Button) findViewById(R.id.button_send_broadcast_asynchronous);
        btn_cancel_broadcast = (Button) findViewById(R.id.button_cancel_broadcast);
        view_battery = (ImageView) findViewById(R.id.image_battery);
        text_msg_battery = (TextView) findViewById(R.id.text_msg_battery);
        text_show_running_service = (TextView) findViewById(R.id.text_show_running_service);
        text_show_running_service.setMovementMethod(new ScrollingMovementMethod());
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

    private static List<ActivityManager.RunningServiceInfo> getRunningServicesList(Context ctx) {
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> servicesList = activityManager.getRunningServices(Integer.MAX_VALUE);
        return servicesList;
    }

}

