import javax.print.DocFlavor;
import java.time.LocalDate;
import java.time.Month;

public class LocalDateDemo {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate date = LocalDate.of(2023, Month.JUNE, 15);
        int year = date.getYear();  // 2023
        Month month = date.getMonth();  // JUNE
        int day = date.getDayOfMonth();  // 15
        LocalDate nextWeek = today.plusWeeks(1);
        boolean isLeap = date.isLeapYear();  // 是否闰年
        System.out.println(year +"年" +month + "月" + day + "日" + (isLeap ? "是闰年" : "不是闰年"));
        String text = isLeap ? "是闰年" : "不是闰年";
        /* 格式化输出*/
        System.out.printf("%d年%s月%d日-%s",year,month,day,text);
        System.out.println();
        System.out.printf("%d年%s月%d日-%s".formatted(year,month,day,text));
    }
}
