package com.tencent.mobileqq.fcm;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import com.tencent.mobileqq.utils.ULog;

/**
 * @Description: <p>
 * @Author: Kosmos
 * @Date: 2019.04.22 11:03
 * @Email: KosmoSakura@gmail.com
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        log("刷新Token:" + refreshedToken);
        //如果要向此应用程序实例发送消息或在服务器端管理此应用程序订阅
        // 将实例ID令牌发送到应用服务器。
        sendRegistrationToServer(refreshedToken);
    }

    /**
     * 将令牌保留到第三方服务器。
     * 修改此方法以将用户的fcm instanceID令牌与应用程序维护的任何服务器端帐户关联。
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    private void log(String str) {
        ULog.commonD("FCM_Instance:" + str);
    }
}
