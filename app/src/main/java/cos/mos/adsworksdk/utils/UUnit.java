package cos.mos.adsworksdk.utils;

import java.util.Locale;

import cos.mos.adsworksdk.init.App;


/**
 * @Description: 单位换算工具类
 * @Author: Kosmos
 * @Date: 2018.10.31 14:02
 * @Email: KosmoSakura@foxmail.com
 */
public class UUnit {
    private static final float scale = App.getInstance().getResources().getDisplayMetrics().density;


    /**
     * @param size 单位：字节
     * @apiNote 格式化字节单位
     */
    public static String sizeFormatbit(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return fotmat(size) + "\tB";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            return fotmat(kiloByte) + "\tKB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            return fotmat(megaByte) + "\tMB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            return fotmat(gigaByte) + "\tGB";
        }
        return fotmat(teraBytes) + "\tTB";
    }

    private static String fotmat(double digit) {
        return String.format(Locale.CHINA, "%.2f", digit);
    }

    public static String sizeFormatbitTime(float millisecond) {
        float second = millisecond / 1000;
        if (second < 1) {
            return fotmatMax(millisecond) + "ms";
        }
        float min = second / 60;
        if (min < 1) {
            return fotmatMax(second) + "s";
        }
        float h = min / 60;
        if (h < 1) {
            return fotmatMax(min) + "min";
        }
        float day = h / 24;
        if (day < 1) {
            return fotmatMax(h) + "h";
        }
        float mon = day / 30;
        if (mon < 1) {
            return fotmatMax(day) + "D";
        }
        float year = mon / 12;
        if (year < 1) {
            return fotmatMax(mon) + "M";
        }
        return fotmatMax(year) + "Y";
    }

    private static float fotmatMax(float digit) {
        int n = (int) (digit * 100f);//截断2位以上的小数
        return (float) n / 100f;
    }
}
