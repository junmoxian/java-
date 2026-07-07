import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * GregorianCalendar 常用方法演示。
 *
 * <p>GregorianCalendar 是 Calendar 的子类，主要用来处理公历日期时间。
 * 现在新项目更推荐使用 java.time 包，但老项目里仍然经常能看到 Calendar / Date。</p>
 */
public class GregorianCalendarDemo {

    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws ParseException {
        demoCreate();
        demoGet();
        demoSet();
        demoAdd();
        demoRoll();
        demoMaxDay();
        demoLeapYear();
        demoDateConvert();
        demoMillis();
        demoCompare();
        demoFormatAndParse();
    }

    /**
     * 1. 创建 GregorianCalendar 对象。
     */
    public static void demoCreate() {
        printTitle("1. 创建对象");

        // 创建当前系统时间对应的日历对象
        GregorianCalendar calendar1 = new GregorianCalendar();
        System.out.println("当前时间：" + format(calendar1));

        // 创建指定日期：2026 年 7 月 5 日
        // 注意：Calendar.JULY 表示 7 月，底层值其实是 6。
        GregorianCalendar calendar2 = new GregorianCalendar(2026, Calendar.JULY, 5);
        System.out.println("指定日期：" + format(calendar2));

        // 创建指定日期时间：2026 年 7 月 5 日 15:20:30
        GregorianCalendar calendar3 = new GregorianCalendar(2026, Calendar.JULY, 5, 15, 20, 30);
        System.out.println("指定日期时间：" + format(calendar3));
    }

    /**
     * 2. 获取日期时间字段。
     */
    public static void demoGet() {
        printTitle("2. 获取年月日时分秒");

        GregorianCalendar calendar = new GregorianCalendar(2026, Calendar.JULY, 5, 15, 20, 30);

        int year = calendar.get(Calendar.YEAR);

        // 月份从 0 开始，所以真实月份要 +1。
        int month = calendar.get(Calendar.MONTH) + 1;

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int week = calendar.get(Calendar.DAY_OF_WEEK);

        System.out.println("年：" + year);
        System.out.println("月：" + month);
        System.out.println("日：" + day);
        System.out.println("时：" + hour);
        System.out.println("分：" + minute);
        System.out.println("秒：" + second);
        System.out.println("星期字段：" + week + "，其中 Sunday=1，Monday=2");
    }

    /**
     * 3. 设置日期时间字段。
     */
    public static void demoSet() {
        printTitle("3. 设置日期时间");

        GregorianCalendar calendar = new GregorianCalendar();

        calendar.set(Calendar.YEAR, 2026);
        calendar.set(Calendar.MONTH, Calendar.JULY);
        calendar.set(Calendar.DAY_OF_MONTH, 5);
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 20);
        calendar.set(Calendar.SECOND, 30);
        calendar.set(Calendar.MILLISECOND, 0);

        System.out.println("设置后的时间：" + format(calendar));

        // 也可以一次性设置年月日。这里只改了年月日，时分秒会保留原值。
        calendar.set(2030, Calendar.JANUARY, 1);
        System.out.println("重新设置年月日：" + format(calendar));
    }

    /**
     * 4. add 方法：日期加减，会自动进位或退位。
     */
    public static void demoAdd() {
        printTitle("4. add 日期加减");
        /*Calendar.DECEMBER 表示“十二月”，值是11 */
        GregorianCalendar calendar = new GregorianCalendar(2026, Calendar.DECEMBER, 31);
        System.out.println("原日期：" + format(calendar));

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println("加 1 天：" + format(calendar));

        calendar.add(Calendar.MONTH, -1);
        System.out.println("减 1 个月：" + format(calendar));

        calendar.add(Calendar.YEAR, 2);
        System.out.println("加 2 年：" + format(calendar));
    }

    /**
     * 5. roll 方法：只滚动当前字段，一般不会影响更大的字段。
     */
    public static void demoRoll() {
        printTitle("5. roll 字段滚动");

        GregorianCalendar calendar = new GregorianCalendar(2026, Calendar.DECEMBER, 31);
        System.out.println("原日期：" + format(calendar));
        /*roll 只会让“指定字段”在它自己的有效范围内滚动，通常不会影响比它更大的字段*/
        /*例如一个月是28-31天，当前是最后一天，最后一天没有数字了，重置为1重新开始*/
        /*add 会影响整体的日期和时间进位*/
        calendar.roll(Calendar.DAY_OF_MONTH, 1);
        System.out.println("roll 天数 +1：" + format(calendar));

        GregorianCalendar addCalendar = new GregorianCalendar(2026, Calendar.DECEMBER, 31);
        addCalendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println("add 天数 +1：" + format(addCalendar));

        System.out.println("对比：roll 不会从 2026 年滚到 2027 年，add 会自动进位。");
    }

    /**
     * 6. 获取某个月的最大天数。
     */
    public static void demoMaxDay() {
        printTitle("6. 获取某月最大天数");

        GregorianCalendar calendar = new GregorianCalendar(2026, Calendar.FEBRUARY, 1);

        int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        System.out.println("2026 年 2 月最小日期：" + minDay);
        System.out.println("2026 年 2 月最大日期：" + maxDay);
    }

    /**
     * 7. 判断闰年。
     */
    public static void demoLeapYear() {
        printTitle("7. 判断闰年");

        GregorianCalendar calendar = new GregorianCalendar();

        System.out.println("2024 是否闰年：" + calendar.isLeapYear(2024));
        System.out.println("2025 是否闰年：" + calendar.isLeapYear(2025));
        System.out.println("2026 是否闰年：" + calendar.isLeapYear(2026));
    }

    /**
     * 8. Date 和 GregorianCalendar 互转。
     */
    public static void demoDateConvert() {
        printTitle("8. Date 互转");

        Date date = new Date();

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        Date result = calendar.getTime();

        System.out.println("Date 转 GregorianCalendar：" + format(calendar));
        System.out.println("GregorianCalendar 转 Date：" + DATE_TIME_FORMAT.format(result));
    }

    /**
     * 9. 毫秒时间戳和 GregorianCalendar 互转。
     */
    public static void demoMillis() {
        printTitle("9. 毫秒时间戳");

        GregorianCalendar calendar = new GregorianCalendar(2026, Calendar.JULY, 5, 15, 20, 30);

        long millis = calendar.getTimeInMillis();
        System.out.println("转成毫秒时间戳：" + millis);

        GregorianCalendar newCalendar = new GregorianCalendar();
        newCalendar.setTimeInMillis(millis);
        System.out.println("时间戳转回日期：" + format(newCalendar));
    }

    /**
     * 10. 日期比较。
     */
    public static void demoCompare() {
        printTitle("10. 日期比较");

        GregorianCalendar start = new GregorianCalendar(2026, Calendar.JULY, 5);
        GregorianCalendar end = new GregorianCalendar(2026, Calendar.JULY, 10);

        GregorianCalendar test = new GregorianCalendar(2026, Calendar.JULY, 1);

        System.out.println("start 在 end 之前：" + start.before(end));
        System.out.println("start 在 end 之后：" + start.after(end));
        System.out.println("start 和 end 比较：" + start.compareTo(end));

        System.out.println("start 和 test 比较" + start.compareTo(test));
        if (start.compareTo(test) < 0) {
            System.out.println("compareTo 结果小于 0，说明 start 更早。");
        }else{
            System.out.println("compareTo 结果大于 0，说明 start 更晚。");
        }
    }

    /**
     * 11. 格式化和解析字符串。
     */
    public static void demoFormatAndParse() throws ParseException {
        printTitle("11. 格式化和解析");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // false 表示严格解析，例如 2026-02-31 这种非法日期会报错。
        sdf.setLenient(false);

        GregorianCalendar calendar = new GregorianCalendar(2026, Calendar.JULY, 5, 15, 20, 30);

        String text = sdf.format(calendar.getTime());
        System.out.println("日期格式化成字符串：" + text);

        Date parsedDate = sdf.parse("2026-07-05 15:20:30");

        GregorianCalendar parsedCalendar = new GregorianCalendar();
        parsedCalendar.setTime(parsedDate);

        System.out.println("字符串解析成年：" + parsedCalendar.get(Calendar.YEAR));
        System.out.println("字符串解析成月：" + (parsedCalendar.get(Calendar.MONTH) + 1));
        System.out.println("字符串解析成日：" + parsedCalendar.get(Calendar.DAY_OF_MONTH));
    }

    public static void printTitle(String title) {
        System.out.println();
        System.out.println("========== " + title + " ==========");
    }

    public static String format(GregorianCalendar calendar) {
        return DATE_TIME_FORMAT.format(calendar.getTime());
    }
}
