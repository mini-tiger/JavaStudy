package basic;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class 日期时间 {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取前一个月第一天 加1秒
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, -1);
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        calendar1.add(Calendar.SECOND,1);
        String firstDay = sdf.format(calendar1.getTime());

        System.out.println(Date2TimeStamp(firstDay,"yyyy-MM-dd")+1000);
        //获取前一个月最后一天
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH, 0);
        String lastDay = sdf.format(calendar2.getTime());
        System.out.println(firstDay);
        System.out.println(lastDay);

    }

    // 当前时间
    public static String CurrentDate() {
        String tmpstr = "";
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        tmpstr = dateFormat.format(date);
        return tmpstr;
    }
    // 当前UNIX时间戳
    public static String Date2CurrTimeStamp() {
        try {
            long t = System.currentTimeMillis();
            return String.valueOf(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    // 时间转unix时间戳
    public static long Date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr).getTime() ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Long.valueOf(dateStr);
    }
}
