package com.basicdata.task_app;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by jky on 15-7-23.
 */
public class FragmentList_for_news extends ListFragment {

    public static final String TAG = "FragmentList";
    private LayoutInflater minflater = null;

    MyListAdapter myListAdapter = null;
    ListView myListView = null;
    private static final String[] newsType = new String[] {
            "IT",
            "Android",
            "Inter",
            "International",
            "Novel",
            "Games"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        this.setListAdapter(new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, cities));
        myListAdapter = new MyListAdapter();
        setListAdapter(myListAdapter);

    }

    private int[] pictures = {R.drawable.th_bosstool, R.drawable.th_bosstool, R.drawable.th_bosstool,
                        R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        View view = inflater.inflate(R.layout.test, container, false);
//        minflater = inflater;

//        return null;
        return inflater.inflate(R.layout.test, container, false);
    }

    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return newsType.length;
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

            if (convertView == null) {

                holder = new ViewHolder();
//                convertView = minflater.inflate(R.layout.fragmentlist_for_news, null);
                Context context = getActivity();
                convertView = LayoutInflater.from(context).inflate(R.layout.fragmentlist_for_news, null);

                holder.imageView = (ImageView) convertView.findViewById(R.id.image_flag);
                holder.textView = (TextView) convertView.findViewById(R.id.news_type);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.imageView.setImageResource(pictures[position]);
            holder.textView.setText(newsType[position]);
            return convertView;
        }
    }

    public static class ViewHolder {
        public ImageView imageView;
        public TextView textView;
    }
}