package com.basicdata.task_app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by jky on 15-7-16.
 */
public class Main extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViews();

    }
    private Button button_contact;
    private Button button_message;
    private Button button_call_history;

    private void findViews() {
        button_contact = (Button) findViewById(R.id.button_contact);
        button_message = (Button) findViewById(R.id.button_message);
        button_call_history = (Button) findViewById(R.id.button_call_history);
    }
}