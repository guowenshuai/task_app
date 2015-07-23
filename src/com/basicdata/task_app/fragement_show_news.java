package com.basicdata.task_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jky on 15-7-23.
 */
public class fragement_show_news extends Fragment {

    private TextView textView_showMessage;
    private View view;
    private String args;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_show_news, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {

        textView_showMessage = (TextView) view.findViewById(R.id.text_view_show_news);
        args = getArguments().getString("arg");
        set_news(args);
        super.onResume();
    }

    public void set_news (String str) {
        textView_showMessage.setText(str);
    }
}