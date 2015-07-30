package com.basicdata.task_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by jky on 15-7-30.
 * @文件名 RingPhone.java
 * @包含类名列表 RingPhone
 * @版本号 1.0
 * @创建日期 15-7-30
 *
 * */

/**
 * @CalssName RingPhone
 * @Author 郭文帅
 * @Function 在上下文中提供打电话的功能
 * @Date 15-7-30
 * @Modified
 * */
public class RingPhone {

    /**
     * 电话号码
     * */
    private String mPhoneNumber;

    /**
     * Context上下文
     * */
    private Context mContext;

    /**
     * RingPhone的构造函数
     * @param phoneNumber 要拨打的电话号码
     * @param context 当前环境的上下文
     * */
    public RingPhone(String phoneNumber, Context context) {
        this.mPhoneNumber = phoneNumber;
        this.mContext = context;
    }

    /**
     *  拨打电话
     *  <p>使用Intent传送打电话的意图</p>
     *  @param
     *  @return
     * */
    public boolean callTheNumber() {
        Intent dialIntent = null;

        /**
         * 检测电话号码是否为空
         * 空则调用其他合适的可代替的UI显示
         * 电话号码不空则拨打电话*/
        if(!TextUtils.isEmpty(mPhoneNumber)) {
            dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mPhoneNumber));
            dialIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            dialIntent = new Intent(Intent.ACTION_CALL_BUTTON);
        }
        mContext.startActivity(dialIntent);
        return true;
    }
}
