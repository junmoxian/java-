import java.time.LocalDateTime;

public class LocalDateTimeDemo {
    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime dt = LocalDateTime.of(2023, 6, 15, 14, 30);
        LocalDateTime nextMonth = dt.plusMonths(1);
        String text = ldt.toString();
        System.out.println(ldt);
    }
}
