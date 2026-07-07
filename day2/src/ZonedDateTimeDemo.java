import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeDemo {
    public static void main(String[] args) {
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime nyTime = zdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        ZoneId zone = zdt.getZone();  // 获取时区
        System.out.println(zdt);
        System.out.println(zone);
    }
}
