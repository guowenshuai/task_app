package com.basicdata.task_app;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jky on 15-7-23.
 */
public class FragmentCommunications extends Activity implements FragmentList_for_news.getNewsTitle {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentcommunications);
        setMessage(null, "");

    }

    private static final String TAG = "FragmentCommunications";


    @Override
    public void showMessage(int index) {
        switch (index) {
            case 0:
                Log.v(TAG, "000");
                setMessage(null, "000000");
                break;
            case 1:
                Log.v(TAG, "111");
                setMessage(null, "111111");
                break;
            case 2:
                Log.v(TAG, "222");
                setMessage(null, "222222");
                break;
            default:
                Log.v(TAG, "default");
                setMessage(null, "");
        }
    }

    public void setMessage(View view, String str) {
        fragement_show_news arg = new fragement_show_news();
        Bundle bundle = new Bundle();
        bundle.putString("arg", str);
        arg.setArguments(bundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.news_container, arg);
        ft.commit();
    }
}