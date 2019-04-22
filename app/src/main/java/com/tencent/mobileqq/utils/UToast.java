package com.tencent.mobileqq.utils;

import android.widget.Toast;

import com.tencent.mobileqq.init.App;


/**
 * @Description: 避免Toast消息提示按照队列来重复提示
 * @Author: Kosmos
 * @Date: 2017年2月14日 16:24
 * @Email: KosmoSakura@foxmail.com
 * @eg 最新修改日期：2018-11-6
 */
public class UToast {

    public static void ShortMessage(String msg) {
        Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public void LongMessage(String msg) {
        Toast.makeText(App.getInstance(), msg, Toast.LENGTH_LONG).show();
    }
}
