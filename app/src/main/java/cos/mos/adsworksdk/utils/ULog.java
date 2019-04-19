package cos.mos.adsworksdk.utils;

import android.util.Log;

/**
 * @Description: 一个精简、全面、方便的 Android LogInterceptor 库
 * @Author: Blankj
 * @Date: 2016年09月21日
 * @Email: https://github.com/Blankj
 * @Email: http://blankj.com
 * @eg 修改日期：2018-10-01
 */
public final class ULog {
    public static void commonD(String str) {
        Log.d("Kosmos", str);
    }

    public static void commonV(String str) {
        Log.v("Kosmos", str);
    }

    public static void commonE(String str) {
        Log.e("Kosmos", str);
    }

    public static void commonW(String str) {
        Log.w("Kosmos", str);
    }

    public static void commonI(String str) {
        Log.i("Kosmos", str);
    }
}