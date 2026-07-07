import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatParseDemo {
    public static void main(String[] args) {
        /*
         * parse(String source)
         * 作用：把字符串解析成 Date 对象。
         *
         * 注意：
         * 字符串内容必须尽量符合 SimpleDateFormat 指定的格式。
         * 如果格式不匹配，parse 方法会抛出 ParseException。
         */

        parseFullDateTime();
        parseOnlyDate();
        parseWithWrongFormat();
        parseStrictDate();
    }

    private static void parseFullDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String text = "2026-07-04 16:20:30";

        try {
            Date date = formatter.parse(text);
            System.out.println("完整日期时间字符串: " + text);
            System.out.println("解析后的 Date 对象: " + date);
            System.out.println("重新格式化输出: " + formatter.format(date));
            System.out.println();
        } catch (ParseException e) {
            System.out.println("解析失败: " + e.getMessage());
        }
    }

    private static void parseOnlyDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String text = "2026/07/04";

        try {
            Date date = formatter.parse(text);
            System.out.println("只有日期的字符串: " + text);
            System.out.println("解析后的 Date 对象: " + date);
            System.out.println("重新格式化输出: " + formatter.format(date));
            System.out.println();
        } catch (ParseException e) {
            System.out.println("解析失败: " + e.getMessage());
        }
    }

    private static void parseWithWrongFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String text = "2026/07/04";

        try {
            Date date = formatter.parse(text);
            System.out.println("解析后的 Date 对象: " + date);
        } catch (ParseException e) {
            System.out.println("格式不匹配示例");
            System.out.println("要求格式: yyyy-MM-dd");
            System.out.println("实际字符串: " + text);
            System.out.println("异常信息: " + e.getMessage());
            System.out.println();
        }
    }

    private static void parseStrictDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        // 关闭宽松解析，避免把 2026-13-40 解析成另一个合法日期。
        formatter.setLenient(false);

        String text = "2026-13-40";

        try {
            Date date = formatter.parse(text);
            System.out.println("解析后的 Date 对象: " + date);
        } catch (ParseException e) {
            System.out.println("严格解析示例");
            System.out.println("要求格式: yyyy-MM-dd");
            System.out.println("实际字符串: " + text);
            System.out.println("异常信息: " + e.getMessage());
        }
    }
}
