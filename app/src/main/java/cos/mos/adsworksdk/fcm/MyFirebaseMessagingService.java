package cos.mos.adsworksdk.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.core.app.NotificationCompat;
import cos.mos.adsworksdk.MainActivity;
import cos.mos.adsworksdk.R;
import cos.mos.adsworksdk.utils.ULog;

/**
 * @Description: <p>
 * @Author: Kosmos
 * @Date: 2019.04.22 10:55
 * @Email: KosmoSakura@gmail.com
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        log("收到推送-来自:" + remoteMessage.getFrom());
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            log("收到推送-数据:" + remoteMessage.getData());
        }
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            log("收到推送-通知:" + remoteMessage.getNotification().getBody());
        }
        if (remoteMessage.getNotification() != null && remoteMessage.getNotification().getBody() != null) {
            sendNotification(getApplicationContext(), remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        } else {
            sendNotification(getApplicationContext(), remoteMessage.getData().get("title"), remoteMessage.getData().get("body"));
        }
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        log("删除通知");
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
        log("发送通知");
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
        log("通知生成失败");
    }

    private void sendNotification(Context iContext, String messageTitle, String messageBody) {
        NotificationManager notificationManager = (NotificationManager) iContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, MainActivity.class); // 接收到通知后，点击通知，启动 MessageActivity

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long[] pattern = {500, 500, 500, 500, 500};
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "-1")
            .setTicker(messageTitle)
            .setSmallIcon(R.drawable.ic_launcher)
            .setContentTitle("push 通知 标题")
            .setAutoCancel(true)
            .setContentText(messageBody)
            .setWhen(System.currentTimeMillis())
            .setVibrate(pattern)
            .setLights(Color.BLUE, 1, 1);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND | NotificationCompat.DEFAULT_VIBRATE);

        builder.setContentIntent(pendingIntent);
//        builder.setFullScreenIntent(pendingIntent, true);//将一个Notification变成悬挂式Notification

        if (notificationManager != null) {
            notificationManager.notify(0, builder.build());
        }
    }

    private void log(String str) {
        ULog.commonD("FCM_MSG:" + str);
    }
}
