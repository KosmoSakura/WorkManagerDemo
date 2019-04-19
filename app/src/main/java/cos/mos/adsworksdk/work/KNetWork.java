package cos.mos.adsworksdk.work;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import cos.mos.adsworksdk.MainActivity;
import cos.mos.adsworksdk.init.Code;
import cos.mos.adsworksdk.utils.UDate;
import cos.mos.adsworksdk.utils.ULogSaves;

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
