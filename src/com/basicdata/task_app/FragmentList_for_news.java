package com.basicdata.task_app;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.xmlpull.v1.XmlPullParser;

/**
 * Created by jky on 15-7-23.
 */
public class FragmentList_for_news extends ListFragment {

    public static final String TAG = "FragmentList";
    private LayoutInflater minflater = null;
    private Context context = null;

    /*第一步  提供一个供宿主Activity调用的接口*/
    public interface getNewsTitle {
        public void showMessage(int index);
    }

    /*第二步  自己这边实例化接口*/
    private getNewsTitle get_news_title;

    /*第三步  在fragment附属Activity时候，检测Activity是否实现了接口的方法
    *
    * 如果实现则拿到这个借口，否则抛出异常
    *       */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            get_news_title = (getNewsTitle) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement getNewsTitle");
        }
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test, container, false);
//        return inflater.inflate(R.layout.test, container, false);
        minflater = inflater;
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        this.setListAdapter(new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, cities));

//        myListView = getListView();

        /*设置适配器*/
        myListAdapter = new MyListAdapter();
        setListAdapter(myListAdapter);

//        setListOnclickListeners();

    }


    /*第四步   在需要的时候调用Activity端实现的方法*/
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        get_news_title.showMessage(position);
    }

//    /*设置 listview 按钮监听*/
//    private void setListOnclickListeners() {
//        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                get_news_title.showMessage(position);
//            }
//        });
//    }

    /*
    * 图片地址
    * */
    private int[] pictures = {R.drawable.th_bosstool, R.drawable.th_bosstool, R.drawable.th_bosstool,
                        R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};



    /*
    * 定义适配器
    *
    * */
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
                context = (Context) getActivity();
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