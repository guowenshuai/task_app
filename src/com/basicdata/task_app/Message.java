package com.basicdata.task_app;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gws on 15-7-16.
 */
public class Message extends ListActivity {

    Context mContext = null;

    private static final String[] PHONE_MSG_PROJECTION = new String[] { "_id", "address", "person",
            "body", "date", "type"};

    private static final int PHONE_ID = 0;

    private static final int PHONE_ADDRESS = 1;

    private static final int PHONE_PERSON = 2;

    private static final int PHONE_BODY = 3;

    private static final int PHONE_DATE = 4;

    private static final int PHONE_TYPE = 5;

    private ArrayList<String> mPhone_Address = new ArrayList<String>();

    private ArrayList<String> mPhone_Person = new ArrayList<String>();

    private ArrayList<String> mPhone_Body = new ArrayList<String>();

    private ArrayList<String> mPhone_Date = new ArrayList<String>();

    private ArrayList<String> mPhone_Type = new ArrayList<String>();

    @Override
     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        findView();
    }

    private TextView view_number;
    private TextView view_message;

    private void findView() {
        view_number = (TextView) findViewById(R.id.show_number);
        view_message = (TextView) findViewById(R.id.show_message);
    }


}