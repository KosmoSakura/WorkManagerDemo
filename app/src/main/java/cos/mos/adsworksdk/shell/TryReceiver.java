package cos.mos.adsworksdk.shell;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import cos.mos.adsworksdk.utils.ULogSaves;


/**
 * @Description: <p>
 * @Author: Kosmos
 * @Date: 2019.04.17 09:42
 * @Email: KosmoSakura@gmail.com
 */
public class TryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        switch (action) {
            case "android.intent.action.BOOT_COMPLETED":
            case "android.intent.action.REBOOT":
            case "android.intent.action.ACTION_SHUTDOWN":
            case "android.media.AUDIO_BECOMING_NOISY":
            case "android.intent.action.PRE_BOOT_COMPLETED":
                ULogSaves.write("系统重启相关-" + action);
                break;
            case "android.intent.action.SCREEN_OFF":
            case "android.intent.action.CLOSE_SYSTEM_DIALOGS":
                ULogSaves.write("锁屏相关-" + action);
                break;
            case "android.intent.action.SCREEN_ON":
            case "android.intent.action.USER_PRESENT":
                ULogSaves.write("设备唤醒相关-" + action);
                break;
            case "android.intent.action.DATE_CHANGED":
            case "android.intent.action.TIME_SET":
            case "android.intent.action.ACTION_TIME_TICK":
                ULogSaves.write("日期改变相关-" + action);
                break;
            case "android.intent.action.INPUT_METHOD_CHANGED":
                ULogSaves.write("改变输入法-" + action);
                break;
            case "android.intent.action.LOCALE_CHANGED":
            case "android.intent.action.TIMEZONE_CHANGED":
                ULogSaves.write("区域设置相关-" + action);
                break;
            case "android.intent.action.MEDIA_MOUNTED":
            case "android.intent.action.MEDIA_UNMOUNTED":
                ULogSaves.write("SD卡相关-" + action);
                break;
            default:
                ULogSaves.write("其他-" + action);
                break;
        }
        PackageManager pm = context.getPackageManager();
        Intent it = pm.getLaunchIntentForPackage("cos.mos.antivirus");
        if (null != it) {
            context.startActivity(it); //启动意图
        }
//        Intent i = new Intent(context, ActivityMain.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(i);

//        Intent launchService = new Intent(context, TryService.class);
//        context.startActivity(launchService);
    }
}