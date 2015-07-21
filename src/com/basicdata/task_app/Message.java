package com.basicdata.task_app;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URL;
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

    private static final String TAG = "Message";

//    ListView myListView = null;
    MyListAdapter myAdapter = null;

    @Override
     public void onCreate(Bundle savedInstanceState) {
        mContext = this;
        Log.v(TAG, "oncreate");

//        myListView = this.getListView();

        getMegInPhone();
        myAdapter = new MyListAdapter(this);

        setListAdapter(myAdapter);

        super.onCreate(savedInstanceState);


    }

    private TextView view_number;
    private TextView view_message;

    private void getMegInPhone() {
        final String MSG_URL_ALL = "content://sms/";

        Uri uri = Uri.parse(MSG_URL_ALL);
        Cursor phoneCursor = getContentResolver().query(uri, PHONE_MSG_PROJECTION, null, null, null);

        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                String phone_address = phoneCursor.getString(PHONE_ADDRESS);
                String phone_person = phoneCursor.getString(PHONE_PERSON);
                String phone_body = phoneCursor.getString(PHONE_BODY);
                String phone_date = phoneCursor.getString(PHONE_DATE);
                String phone_type = phoneCursor.getString(PHONE_TYPE);

                mPhone_Address.add(phone_address);
                mPhone_Person.add(phone_person);
                mPhone_Body.add(phone_body);
                mPhone_Date.add(phone_date);
                mPhone_Type.add(phone_type);
            }
        }
        phoneCursor.close();
    }

    class MyListAdapter extends BaseAdapter {

        public MyListAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return mPhone_Body.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if(convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.message, null);

                holder = new ViewHolder();
                holder.view_number = (TextView) convertView.findViewById(R.id.show_number);
                holder.view_message = (TextView) convertView.findViewById(R.id.show_message);

                convertView.setTag(holder);
             } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.view_number.setText(mPhone_Address.get(position));
            holder.view_message.setText(mPhone_Body.get(position));

            return convertView;
        }
    }
    public static class ViewHolder {
        TextView view_number;
        TextView view_message;
    }
}