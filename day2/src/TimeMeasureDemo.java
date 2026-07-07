import java.time.Duration;
import java.time.Instant;

public class TimeMeasureDemo {
    public static void main(String[] args) {
        /*
         * 本类演示：如何测量一段 Java 代码运行了多久。
         *
         * 后端开发中，经常需要知道某段代码耗时，例如：
         * 1. 查询数据库用了多久
         * 2. 调用第三方接口用了多久
         * 3. 读取文件、上传文件用了多久
         * 4. 某个循环或算法执行用了多久
         *
         * 测量耗时的核心思路：
         * 1. 任务开始前，记录一个开始时间 start
         * 2. 执行任务
         * 3. 任务结束后，记录一个结束时间 end
         * 4. 用 end - start 得到耗时
         *
         * 常用方式：
         *
         * 1. System.currentTimeMillis()
         *    返回当前时间距离 1970-01-01 00:00:00 GMT 的毫秒数。
         *    单位：毫秒。
         *    适合：记录当前时间戳、粗略计算代码耗时。
         *    注意：它表示的是“当前真实世界时间”，可能受到系统时间调整影响。
         *
         * 2. System.nanoTime()
         *    返回纳秒级时间值。
         *    单位：纳秒。
         *    更适合测量一段代码运行耗时。
         *    注意：nanoTime 的值本身没有日期含义，不能当日期时间使用。
         *    它只适合做差值计算，也就是 end - start。
         *
         * 3. Instant.now() + Duration
         *    java.time 包中的写法，可读性更好。
         *    Instant 表示一个时间点。
         *    Duration 表示两个时间点之间的时间间隔。
         */

        measureWithCurrentTimeMillis();
        measureWithNanoTime();
        measureWithInstant();
    }

    private static void measureWithCurrentTimeMillis() {
        // start 表示任务开始前的时间戳。
        // System.currentTimeMillis() 返回 long 类型，单位是毫秒。
        long start = System.currentTimeMillis();

        // 这里执行真正要测量的任务。
        // 在真实后端项目中，这里可能是查询数据库、调用接口、处理文件等。
        doTask();

        // end 表示任务执行完成后的时间戳。
        long end = System.currentTimeMillis();

        // 结束时间 - 开始时间 = 这段代码运行消耗的时间。
        long cost = end - start;

        System.out.println("currentTimeMillis 测量耗时: " + cost + " 毫秒");
    }

    private static void measureWithNanoTime() {
        // nanoTime 的精度更高，单位是纳秒。
        // 1 毫秒 = 1,000,000 纳秒。
        long start = System.nanoTime();

        doTask();

        long end = System.nanoTime();
        long cost = end - start;

        System.out.println("nanoTime 测量耗时: " + cost + " 纳秒");

        // 因为纳秒数字很大，不方便阅读，所以这里转成毫秒。
        // 使用 1_000_000.0 是为了得到小数结果。
        System.out.println("nanoTime 转成毫秒: " + cost / 1_000_000.0 + " 毫秒");
    }

    private static void measureWithInstant() {
        // Instant.now() 获取当前时间点。
        // 这种写法属于 Java 8 之后推荐使用的 java.time 时间 API。
        Instant start = Instant.now();

        doTask();

        Instant end = Instant.now();

        // Duration.between(start, end) 表示计算两个时间点之间的间隔。
        Duration duration = Duration.between(start, end);

        // duration.toMillis() 把时间间隔转换成毫秒。
        System.out.println("Instant + Duration 测量耗时: " + duration.toMillis() + " 毫秒");
    }

    private static void doTask() {
        try {
            // 这里用 sleep 模拟一段耗时操作。
            // Thread.sleep(800) 表示让当前线程暂停 800 毫秒。
            //
            // 真实项目中，你不一定会主动 sleep。
            // 代码耗时通常来自数据库查询、网络请求、文件读写、复杂计算等。
            Thread.sleep(800);
        } catch (InterruptedException e) {
            // sleep 方法要求处理 InterruptedException。
            // 这个异常表示当前线程在休眠时被其他线程打断。
            System.out.println("任务被打断");
        }
    }
}
