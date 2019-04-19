package cos.mos.adsworksdk;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import cos.mos.adsworksdk.utils.ULogSaves;
import cos.mos.adsworksdk.utils.UWork;

public class MainActivity extends AppCompatActivity {

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
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
