import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateDemo {
    public static void main(String[] args) throws ParseException {
        /*
         * java.util.Date 常用构造方法：
         * 1. Date()
         *    参数：无
         *    含义：创建对象时的当前日期和时间。
         *
         * 2. Date(long date)
         *    参数：long 类型的毫秒时间戳
         *    含义：从 1970-01-01 00:00:00 GMT 开始，经过 date 毫秒后的时间。
         */

        // 1. 获取当前日期和时间
        Date now = new Date();
        System.out.println("当前时间: " + now);

        // 2. 使用毫秒时间戳创建 Date 对象
        Date epoch = new Date(0L);
        Date oneHourAfterEpoch = new Date(60L * 60 * 1000);
        System.out.println("时间原点: " + epoch);
        System.out.println("时间原点后一小时: " + oneHourAfterEpoch);

        // getTime(): 返回 long 类型毫秒时间戳
        long nowMillis = now.getTime();
        System.out.println("当前毫秒时间戳: " + nowMillis);

        // setTime(long time): 修改 Date 对象保存的时间
        Date tomorrow = new Date();
        tomorrow.setTime(now.getTime() + 24L * 60 * 60 * 1000);
        System.out.println("明天此刻: " + tomorrow);

        // before(Date when)、after(Date when): 判断时间先后
        System.out.println("now 是否在 tomorrow 之前: " + now.before(tomorrow));
        System.out.println("tomorrow 是否在 now 之后: " + tomorrow.after(now));

        // compareTo(Date anotherDate): 比较两个时间
        // 返回负数表示早于参数，0 表示相等，正数表示晚于参数。
        int compareResult = now.compareTo(tomorrow);
        System.out.println("now.compareTo(tomorrow): " + compareResult);

        // equals(Object obj): 判断两个 Date 的毫秒时间戳是否相同
        Date sameAsNow = new Date(now.getTime());
        System.out.println("now 和 sameAsNow 是否相等: " + now.equals(sameAsNow));

        // toString(): 转成默认格式字符串，实际开发中通常会使用 SimpleDateFormat 自定义格式
        System.out.println("默认字符串: " + now.toString());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedNow = formatter.format(now);
        System.out.println("格式化后的当前时间: " + formattedNow);

        // SimpleDateFormat.parse(String source): 将字符串解析成 Date
        Date parsedDate = formatter.parse("2026-07-04 15:30:00");
        System.out.println("字符串解析后的时间: " + parsedDate);

        // Date 和 java.time.Instant 互转，常用于新旧时间 API 兼容
        Instant instant = now.toInstant();
        Date dateFromInstant = Date.from(instant);
        System.out.println("Date 转 Instant: " + instant);
        System.out.println("Instant 转 Date: " + dateFromInstant);
    }
}
