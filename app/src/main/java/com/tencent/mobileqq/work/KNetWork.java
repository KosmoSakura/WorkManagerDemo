package com.tencent.mobileqq.work;

import android.content.Context;
import android.content.Intent;

import com.tencent.mobileqq.MainActivity;
import com.tencent.mobileqq.init.Code;
import com.tencent.mobileqq.utils.UDate;
import com.tencent.mobileqq.utils.ULogSaves;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * @Description: 任务的执行者
 * @Author: Kosmos
 * @Date: 2019.04.18 21:01
 * @Email: KosmoSakura@gmail.com
 */
public class KNetWork extends Worker {
    private Context context;

    public KNetWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }
    @NonNull
    @Override
    public Result doWork() {
        if (!Code.Alive.get()) {
            if (context != null) {
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                ULogSaves.write("Worker-WN:跳转主页【" + UDate.getDateNow() + "】");
            } else {
                ULogSaves.write("Worker-WN:跳转失败，context为空【" + UDate.getDateNow() + "】");
            }
        } else {
            ULogSaves.write("Worker-WN:应用还活着【" + UDate.getDateNow() + "】");
        }
        return Result.success();
    }
}
