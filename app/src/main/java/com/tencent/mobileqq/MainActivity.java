package com.tencent.mobileqq;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.tencent.mobileqq.utils.ULog;
import com.tencent.mobileqq.utils.ULogSaves;
import com.tencent.mobileqq.utils.UWork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        findViewById(R.id.btttn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UWork.runWork1();
                ULogSaves.write("手动启动服务");
            }
        });
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
//        Bundle bundle = new Bundle();
//        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
//        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
//        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
//        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        FirebaseInstanceId.getInstance().getInstanceId()
            .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                @Override
                public void onComplete(@NonNull Task<InstanceIdResult> task) {
                    if (!task.isSuccessful()) {
                        log("获取令牌失败：" + task.getException());
                        return;
                    }
                    InstanceIdResult result = task.getResult();
                    if (result == null) {
                        log("result为空");
                        return;
                    }
                    // Get new Instance ID token
                    String token = task.getResult().getToken();
                    log("获取的Token为：" + token);
                    // Log and toast
                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void log(String str) {
        ULog.commonD("Main:" + str);
    }
}
