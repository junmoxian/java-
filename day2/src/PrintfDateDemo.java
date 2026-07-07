import java.util.Date;

public class PrintfDateDemo {
    public static void main(String[] args) {
        Date now = new Date();

        /*
         * printf 格式化日期的基本写法：
         * %t + 日期时间转换符
         *
         * 常用转换符：
         * %tY  四位年份，例如 2026
         * %ty  两位年份，例如 26
         * %tm  两位月份，例如 07
         * %td  两位日期，例如 04
         * %tH  24小时制小时，例如 15
         * %tI  12小时制小时，例如 03
         * %tM  分钟，例如 30
         * %tS  秒，例如 05
         * %tL  毫秒，例如 123
         * %tA  星期全称，例如 星期六
         * %ta  星期简称，例如 周六
         * %tp  上午/下午
         * %tF  ISO 日期，等价于 yyyy-MM-dd
         * %tT  24小时制时间，等价于 HH:mm:ss
         */

        System.out.printf("当前 Date 对象: %s%n", now);

        // 每一个格式符都单独传入同一个 Date 参数
        System.out.printf("年月日: %tY-%tm-%td%n", now, now, now);
        System.out.printf("时分秒: %tH:%tM:%tS%n", now, now, now);

        // 使用 < 复用前一个参数，写法更简洁
        System.out.printf("完整时间: %tY-%<tm-%<td %<tH:%<tM:%<tS%n", now);

        // printf 内置的日期和时间快捷格式
        System.out.printf("ISO日期: %tF%n", now);
        System.out.printf("24小时制时间: %tT%n", now);
        System.out.printf("日期 + 时间: %tF %<tT%n", now);

        // 星期、上午下午、毫秒
        System.out.printf("星期: %tA%n", now);
        System.out.printf("12小时制: %tI:%<tM:%<tS %<tp%n", now);
        System.out.printf("带毫秒: %tH:%<tM:%<tS.%<tL%n", now);

        // 也可以使用毫秒时间戳作为参数
        long millis = now.getTime();
        System.out.printf("时间戳格式化: %tF %<tT%n", millis);
    }
}
