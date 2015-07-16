package com.basicdata.task_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by gws on 15-7-16.
 */
public class Message extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        findView();
    }

    private TextView view_show_text;

    private void findView() {
        view_show_text = (TextView) findViewById(R.id.show_text);
    }


}