package com.basicdata.task_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by jky on 15-7-16.
 */
public class Main extends Activity {

    private static final String TAG = "Main";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.v(TAG, "main activity");

        findViews();
        setListeners();

    }
    private Button button_contact;
    private Button button_message;
    private Button button_call_history;

    private void findViews() {
        button_contact = (Button) findViewById(R.id.button_contact);
        button_message = (Button) findViewById(R.id.button_message);
        button_call_history = (Button) findViewById(R.id.button_call_history);
    }

    private void setListeners() {
        button_contact.setOnClickListener(bt_contact);
        button_message.setOnClickListener(bt_message);
        button_call_history.setOnClickListener(bt_call_history);
    }

    private Button.OnClickListener bt_contact = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Main.this, ContactActivity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener bt_message = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Main.this, Message.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener bt_call_history = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Main.this, DummyNote.class);
            Log.v("Main", "before");
            startActivity(intent);
            Log.v("Main", "after");
        }
    };

    /*twice back key finish*/

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                show_Toast();
                exitTime = System.currentTimeMillis();
            } else {
                this.finish();
                System.exit(0);/*System.exit(0)导致当前线程停止并使其他线程终止*/
            }
        }
        /*如果return 父进程的onKeyDown则会执行父进程的退出动作 所以然会true*/
        return true;
//        return super.onKeyDown(keyCode, event);
    }
    private void show_Toast() {
        Toast.makeText(Main.this, getString(R.string.will_finish),Toast.LENGTH_SHORT).show();
    }
}