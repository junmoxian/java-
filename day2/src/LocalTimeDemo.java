import java.time.LocalTime;

public class LocalTimeDemo {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        LocalTime time = LocalTime.of(14, 30, 45);  // 14:30:45
        int hour = time.getHour();  // 14
        int minute = time.getMinute();  // 30
        LocalTime nextHour = time.plusHours(1);
        System.out.println(hour +":" + minute);
    }
}
