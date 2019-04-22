package com.tencent.mobileqq.work;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.tencent.mobileqq.utils.ULog;

/**
 * @Description: <p>
 * @Author: Kosmos
 * @Date: 2019.04.18 21:01
 * @Email: KosmoSakura@gmail.com
 */
public class KWork extends Worker {
    public KWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        //需要在任务中执行的业务逻辑
        ULog.commonD("WorkManager收到回调");
        write("WorkManager收到回调");
        return Result.success();
    }

    private File saveFile = new File(Environment.getExternalStorageDirectory(), "WM记录.txt");

    private String read() {
        byte Buffer[] = new byte[1024];
        //得到文件输入流
        FileInputStream in = null;
        ByteArrayOutputStream outputStream = null;
        try {
            in = new FileInputStream(saveFile);
            //读出来的数据首先放入缓冲区，满了之后再写到字符输出流中
            int len = in.read(Buffer);
            //创建一个字节数组输出流
            outputStream = new ByteArrayOutputStream();
            outputStream.write(Buffer, 0, len);
            //把字节输出流转String
            return new String(outputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    private void write(String str) {
        try {
            //新建文件
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
            String out = read() + "\n" + str;
            final FileOutputStream outStream = new FileOutputStream(saveFile);
            outStream.write(out.getBytes());
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
