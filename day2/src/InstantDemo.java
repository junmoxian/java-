import java.time.Instant;

public class InstantDemo
{
    public static void main(String[] args)
    {
        Instant now = Instant.now();  // 获取当前时间戳
        Instant later = now.plusSeconds(60);  // 60秒后
        long epochMilli = now.toEpochMilli();  // 获取毫秒时间戳
        System.out.println("当前毫秒时间戳" + epochMilli);
        System.out.println("60秒后时间戳" + later);
    }
}
