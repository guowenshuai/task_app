package com.basicdata.task_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jky on 15-7-22.
 */
public class second_fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return (inflater.inflate(R.layout.second_fragment, container, false));

//        return null;
    }
}