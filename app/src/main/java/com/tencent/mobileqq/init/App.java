package com.tencent.mobileqq.init;


import android.app.Activity;
import android.os.Bundle;

import com.tencent.mobileqq.utils.ULog;
import com.tencent.mobileqq.utils.ULogSaves;
import com.tencent.mobileqq.utils.UProcess;
import com.tencent.mobileqq.utils.USP;
import com.tencent.mobileqq.utils.UWork;

import androidx.multidex.MultiDexApplication;

/**
 * @Description: <p>
 * @Author: Kosmos
 * @Date: 2018.10.29 18:58
 * @Email: KosmoSakura@foxmail.com
 */
public class App extends MultiDexApplication {
    public static App instances;
    public static int actCount = 0;

    public static App getInstance() {
        return instances;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        USP.instance().init(this, "antivirus");
        String processName = UProcess.instance().getProcessName(getApplicationContext());
        ULog.commonD("当前进程名：" + processName);
        UWork.init(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                actCount++;
                Code.Alive.set(true);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                Code.Alive.set(true);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Code.Alive.set(false);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Code.Alive.set(false);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                actCount--;
//                UWork.runWork1();
                Code.Alive.set(false);
                ULogSaves.write("应用被销毁，启动服务");
            }
        });
    }


}

