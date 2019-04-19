package cos.mos.adsworksdk.utils;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import cos.mos.adsworksdk.work.KNetWork;
import cos.mos.adsworksdk.work.KWork;

/**
 * @Description: <p>
 * @Author: Kosmos
 * @Date: 2019.04.19 15:23
 * @Email: KosmoSakura@gmail.com
 */
public class UWork {
    public static void init(Context context) {
        Configuration configuration = new Configuration.Builder()
            .setMinimumLoggingLevel(Log.VERBOSE)
            .build();
        WorkManager.initialize(context, configuration);
    }

    public static void runWork2() {
        Constraints.Builder builder = new Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED);  // 网络状态
            .setRequiresCharging(true); //在充电时执行
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            builder.setRequiresDeviceIdle(true); // 在待机状态下执行，需要 API 23
//        }
        //数据传递：和Bundle差不多
        Data data = new Data.Builder().putString("time", UDate.getDateNow()).build();
        //周期任务：指定让哪个Woker执行
        PeriodicWorkRequest request = new PeriodicWorkRequest
            .Builder(KNetWork.class, 10, TimeUnit.SECONDS)
            .setConstraints(builder.build())
            .setInputData(data)
            .build();
        //4.放入执行队列
        WorkManager.getInstance().enqueue(request);
    }

    public static void runWork1() {
        OneTimeWorkRequest requestA = new OneTimeWorkRequest
            .Builder(KNetWork.class)
            .setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
            .build();
        OneTimeWorkRequest requestB = new OneTimeWorkRequest
            .Builder(KNetWork.class)
            .setConstraints(new Constraints.Builder().setRequiresCharging(true).build())
            .build();
        WorkManager.getInstance().enqueue(requestB);
    }

    private void runWork() {
        //1.约束条件
        Constraints.Builder builder = new Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)  // 网络状态
            .setRequiresBatteryNotLow(true)//不在电量不足时执行
            .setRequiresCharging(true) //在充电时执行
            .setRequiresStorageNotLow(true);//不在存储容量不足时执行
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            builder.setRequiresDeviceIdle(true); // 在待机状态下执行，需要 API 23
        }
        //2.传入参数
        Data data = new Data.Builder().putString("time", UDate.getDateNow()).build();
        //3.构造work:任务只会执行一次
        OneTimeWorkRequest request = new OneTimeWorkRequest
            .Builder(KWork.class)
            .setConstraints(builder.build())
            .setInputData(data)
            .build();
        //4.放入执行队列
        WorkManager.getInstance().enqueue(request);
//        WorkManager.getInstance().beginWith(request1).then(request).enqueue();
    }
}
