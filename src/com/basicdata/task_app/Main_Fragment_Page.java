package com.basicdata.task_app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by jky on 15-7-22.
 */
public class Main_Fragment_Page extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainfragmentpage);

        setFragment(INIT_FRAGMENT, null);
        findViews();
        setOnclickListeners();


    }

    private static final int INIT_FRAGMENT = 1;
    private static final int REPLACE_FRAGMENT = 2;
    private static final int ADD_FRAGMENT = 3;
    private static final int REMOVE_FRAGMENT = 4;


    /*设置对fragment的管理和关联*/
    private void setFragment(int flag, Fragment setFragment) {

        /*开始对其进行管理*/
        switch (flag) {
            case INIT_FRAGMENT: {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                first_fragment fragment = new first_fragment();
                fragmentTransaction.add(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                break;
            }
            case REPLACE_FRAGMENT: {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,setFragment);
                fragmentTransaction.commit();
                break;
            }
            default:
                System.out.println("default");
        }
    }

    private Button button_1;
    private Button button_2;

    private void findViews() {
        button_1 = (Button) findViewById(R.id.Button_1);
        button_2 = (Button) findViewById(R.id.Button_2);
    }

    private void setOnclickListeners() {
        button_1.setOnClickListener(bt_1_listener);
        button_2.setOnClickListener(bt_2_listener);
    }

    private Button.OnClickListener bt_1_listener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            second_fragment setFragment = new second_fragment();
            setFragment(REPLACE_FRAGMENT, setFragment);
        }
    };

    private Button.OnClickListener bt_2_listener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            first_fragment setFragment = new first_fragment();
            setFragment(REPLACE_FRAGMENT, setFragment);
        }
    };
}