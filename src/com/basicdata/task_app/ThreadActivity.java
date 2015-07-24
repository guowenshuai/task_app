package com.basicdata.task_app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Message;
//import java.util.logging.Handler;
import java.util.logging.LogRecord;
import android.os.Handler;

/**
 * Created by jky on 15-7-24.
 */
public class ThreadActivity extends Activity {

    private Handler handler;
    private static final int IF_SET_NUMBER = 0;
    private Thread mThread;
    private TextView view_set_number;
    private Button button_set_number;
    private boolean IS_RUNNING = true;
    private int number = 0;
    private static String TAG = "THREAD";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threadactivity);

        findViews();
        setListeners();
        threadForSetNumber();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case IF_SET_NUMBER:
                        Log.d(TAG, "set arg");
                        view_set_number.setText("SET" + msg.arg1);
                        break;
                    default:
                        view_set_number.setText("default");
                        Log.d(TAG, "set default");
                }
            }
        };


       /**************/

    }



    private void findViews() {
        view_set_number = (TextView) findViewById(R.id.set_number);
        button_set_number = (Button) findViewById(R.id.button_setnumber);
    }

    private void setListeners() {
        button_set_number.setOnClickListener(bt_set_number);
    }

    private Button.OnClickListener bt_set_number = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

            System.out.println("thread state: " + mThread.getState());
            if (IS_RUNNING) {
                threadForSetNumber();
                Log.d(TAG, "click start thread");
            } else {
                IS_RUNNING = true;
                threadForSetNumber();
                Log.d(TAG, "set true");
            }
        }
    };

    private void threadForSetNumber() {
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (IS_RUNNING) {
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    number++;
                    if(number % 10 == 0) {
                        IS_RUNNING = false;
                    }
                    Message message = new Message();
                    message.arg1 = number;
                    message.what = IF_SET_NUMBER;
                    handler.sendMessage(message);
                    System.out.println(number);
                }
            }
        });
        mThread.start();
        Log.v(TAG, "thread new start");

    }
}