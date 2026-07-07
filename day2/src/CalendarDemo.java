import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarDemo {
    public static void main(String[] args) {
        /*
         * 一、Calendar 和 Date 的关系
         *
         * Date：
         * 1. Date 主要表示一个“具体时间点”。
         * 2. 例如：2026-07-04 16:30:00 这一刻。
         * 3. Date 本身不擅长做日期字段操作，比如加 3 天、获取月份、设置小时。
         *
         * Calendar：
         * 1. Calendar 表示“日历”，它更擅长操作日期中的某个字段。
         * 2. 例如：获取年、月、日、小时、分钟、秒。
         * 3. 例如：日期加 7 天、月份加 1、设置为某一天的 0 点。
         *
         * 它们之间可以互相转换：
         * Date -> Calendar：calendar.setTime(date)
         * Calendar -> Date：calendar.getTime()
         *
         * 简单理解：
         * Date 更像一个时间结果。
         * Calendar 更像一个可以拆分、修改、计算日期字段的工具。
         *
         * 注意：
         * Calendar 是旧版日期时间 API。
         * 目前 JDK 17 实际开发中更推荐 java.time 包，例如 LocalDate、LocalDateTime。
         * 但是很多老项目、老代码、第三方接口里仍然会看到 Date 和 Calendar。
         */
        dateAndCalendarConvertDemo();
        getDateFieldDemo();
        setDateFieldDemo();
        addDateFieldDemo();
        compareDateDemo();
        monthAttentionDemo();
        commonBusinessDemo();
    }

    private static void dateAndCalendarConvertDemo() {
        System.out.println("======== 1. Date 和 Calendar 互相转换 ========");

        Date nowDate = new Date();
        System.out.println("当前 Date: " + format(nowDate));

        // Calendar 不能直接 new，因为 Calendar 是抽象类。
        // 获取 Calendar 对象的常用方式是 Calendar.getInstance()。
        Calendar calendar = Calendar.getInstance();

        // Date 转 Calendar：
        // setTime(Date date) 表示让 Calendar 使用这个 Date 的时间。
        calendar.setTime(nowDate);
        System.out.println("Date 转 Calendar 后的年份: " + calendar.get(Calendar.YEAR));

        // Calendar 转 Date：
        // getTime() 会把 Calendar 当前保存的时间转成 Date 对象。
        Date dateFromCalendar = calendar.getTime();
        System.out.println("Calendar 转回 Date: " + format(dateFromCalendar));
        System.out.println();
    }

    private static void getDateFieldDemo() {
        System.out.println("======== 2. 获取日期中的年、月、日、时、分、秒 ========");

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);

        // Calendar.MONTH 比较特殊：
        // 一月是 0，二月是 1，三月是 2，以此类推。
        // 所以展示给用户看时，通常要 + 1。
        int month = calendar.get(Calendar.MONTH) + 1;

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        System.out.println("年: " + year);
        System.out.println("月: " + month);
        System.out.println("日: " + day);
        System.out.println("小时: " + hour);
        System.out.println("分钟: " + minute);
        System.out.println("秒: " + second);
        System.out.println();
    }

    private static void setDateFieldDemo() {
        System.out.println("======== 3. 设置指定日期时间 ========");

        Calendar calendar = Calendar.getInstance();

        // 设置日期时间。
        // 注意：Calendar 设置月份时，7 月要写 Calendar.JULY 或者数字 6。
        // 推荐写 Calendar.JULY，可读性更好，也不容易写错。
        calendar.set(Calendar.YEAR, 2026);
        calendar.set(Calendar.MONTH, Calendar.JULY);
        calendar.set(Calendar.DAY_OF_MONTH, 4);
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);

        Date date = calendar.getTime();
        System.out.println("设置后的时间: " + format(date));

        // 一次性设置年月日时分秒。
        // 这里的 Calendar.DECEMBER 表示 12 月。
        calendar.set(2026, Calendar.DECEMBER, 31, 23, 59, 59);
        System.out.println("一次性设置后的时间: " + format(calendar.getTime()));
        System.out.println();
    }

    private static void addDateFieldDemo() {
        System.out.println("======== 4. 日期加减：add 方法 ========");

        Calendar calendar = Calendar.getInstance();
        System.out.println("当前时间: " + format(calendar.getTime()));

        // add(Calendar.DAY_OF_MONTH, 7)
        // 表示在当前日期基础上加 7 天。
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        System.out.println("7 天后: " + format(calendar.getTime()));

        // 负数表示往前减。
        calendar.add(Calendar.MONTH, -1);
        System.out.println("再减 1 个月: " + format(calendar.getTime()));

        calendar.add(Calendar.HOUR_OF_DAY, 2);
        System.out.println("再加 2 小时: " + format(calendar.getTime()));
        System.out.println();
    }

    private static void compareDateDemo() {
        System.out.println("======== 5. 日期比较 ========");

        Calendar start = Calendar.getInstance();
        start.set(2026, Calendar.JULY, 4, 10, 0, 0);

        Calendar end = Calendar.getInstance();
        end.set(2026, Calendar.JULY, 4, 18, 0, 0);

        System.out.println("开始时间: " + format(start.getTime()));
        System.out.println("结束时间: " + format(end.getTime()));

        System.out.println("start 是否在 end 之前: " + start.before(end));
        System.out.println("end 是否在 start 之后: " + end.after(start));

        // getTimeInMillis() 返回毫秒时间戳。
        // 两个时间戳相减，可以得到两个时间点之间相差多少毫秒。
        long diffMillis = end.getTimeInMillis() - start.getTimeInMillis();
        long diffHours = diffMillis / 1000 / 60 / 60;
        System.out.println("两个时间相差小时数: " + diffHours);
        System.out.println();
    }

    private static void monthAttentionDemo() {
        System.out.println("======== 6. Calendar 月份从 0 开始，这点很容易错 ========");

        Calendar calendar = Calendar.getInstance();

        // 这里写 6，实际表示 7 月。
        calendar.set(2026, 6, 4);
        System.out.println("set(2026, 6, 4) 实际表示: " + format(calendar.getTime()));

        // 推荐使用 Calendar.JULY，避免记数字。
        calendar.set(2026, Calendar.JULY, 4);
        System.out.println("set(2026, Calendar.JULY, 4) 表示: " + format(calendar.getTime()));
        System.out.println();
    }

    private static void commonBusinessDemo() {
        System.out.println("======== 7. 实际开发常见场景：获取今天开始和结束时间 ========");

        Calendar calendar = Calendar.getInstance();

        // 今天开始时间：00:00:00.000
        // 后端查询数据库时经常会用到，例如查询今天的订单、今天的登录记录。
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startOfToday = calendar.getTime();

        // 今天结束时间：23:59:59.999
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date endOfToday = calendar.getTime();

        System.out.println("今天开始时间: " + formatWithMillis(startOfToday));
        System.out.println("今天结束时间: " + formatWithMillis(endOfToday));

        // 获取本月最大天数。
        // 例如 2 月可能是 28 天，也可能是 29 天。
        int maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("本月最大天数: " + maxDayOfMonth);
        System.out.println();
    }

    private static String format(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    private static String formatWithMillis(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return formatter.format(date);
    }
}
