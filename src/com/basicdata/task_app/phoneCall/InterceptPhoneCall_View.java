package com.basicdata.task_app.phoneCall;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.basicdata.task_app.R;

/**
 * Created by jky on 15-7-31.
 */
public class InterceptPhoneCall_View extends Activity {

    private static String inComingNumber;
    private static final String TAG = "CALL_VIEW";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interceptphonecall_view);

        Bundle extras = getIntent().getExtras();
        inComingNumber = extras != null ? extras.getString("PHONE_NUMBER") : "10086";
        Log.d(TAG, "Get Phone Number" +
                        inComingNumber);

        findView();
        setListeners();
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

    private void getPhoneInterface() {

    }

    private void setListeners() {
        btn_end_call.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}