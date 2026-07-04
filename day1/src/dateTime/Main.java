package dateTime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * 演示 Java 开发中最常用的日期时间写法。
 *
 * <p>
 * 本示例优先使用 Java 8+ 的 java.time 包。它的对象大多不可变、线程安全，
 * 比旧的 Date、Calendar、SimpleDateFormat 更适合作为新项目默认选择。
 * </p>
 */
public class Main {
  private static final DateTimeFormatter DATE_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private static final ZoneId SHANGHAI_ZONE = ZoneId.of("Asia/Shanghai");
  private static final ZoneId NEW_YORK_ZONE = ZoneId.of("America/New_York");

  /**
   * 程序入口：按开发场景依次演示日期、时间、格式化、解析、比较、时间戳、时区和耗时统计。
   *
   * @param args 命令行参数，本示例未使用
   */
  public static void main(String[] args) {
    showCurrentDateTime();
    showCreateSpecificDateTime();
    showFormatDateTime();
    showParseDateTime();
    showDateCalculate();
    showDateCompare();
    showInstantTimestamp();
    showTimeZoneConvert();
    showMeasureElapsedTime();
  }

  /**
   * 演示获取当前日期时间。
   *
   * <p>
   * 返回值说明：LocalDate 只有年月日，LocalTime 只有时分秒，LocalDateTime 同时包含日期和时间但不含时区。
   * 副作用：向控制台输出示例结果。
   * </p>
   */
  private static void showCurrentDateTime() {
    LocalDate today = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
    LocalDateTime currentDateTime = LocalDateTime.now();

    System.out.println("=== 1. 获取当前日期时间 ===");
    System.out.println("今天日期：" + today);
    System.out.println("当前时间：" + currentTime);
    System.out.println("当前日期时间：" + currentDateTime);
    System.out.println();
  }

  /**
   * 演示创建指定日期时间。
   *
   * <p>
   * 关键参数：of 方法按年、月、日、时、分、秒传入具体数值。
   * 返回值说明：创建后的日期时间对象不会影响系统时间。
   * 副作用：向控制台输出示例结果。
   * </p>
   */
  private static void showCreateSpecificDateTime() {
    LocalDate nationalDay = LocalDate.of(2026, 10, 1);
    LocalTime startWorkTime = LocalTime.of(9, 30);
    LocalDateTime orderCreatedAt = LocalDateTime.of(2026, 7, 2, 14, 30, 15);

    System.out.println("=== 2. 创建指定日期时间 ===");
    System.out.println("指定日期：" + nationalDay);
    System.out.println("指定时间：" + startWorkTime);
    System.out.println("订单创建时间：" + orderCreatedAt);
    System.out.println();
  }

  /**
   * 演示把日期时间格式化成字符串。
   *
   * <p>
   * 关键参数：yyyy 表示年，MM 表示月，dd 表示日，HH 表示 24 小时制小时，mm 表示分钟，ss 表示秒。
   * 返回值说明：format 返回格式化后的字符串，原日期时间对象不变。
   * 副作用：向控制台输出示例结果。
   * </p>
   */
  private static void showFormatDateTime() {
    LocalDateTime now = LocalDateTime.now();
    String displayText = now.format(DATE_TIME_FORMATTER);

    System.out.println("=== 3. 格式化日期时间 ===");
    System.out.println("默认输出：" + now);
    System.out.println("常用格式：" + displayText);
    System.out.println("日期格式：" + now.toLocalDate().format(DATE_FORMATTER));
    System.out.println();
  }

  /**
   * 演示把字符串解析成日期时间。
   *
   * <p>
   * 状态流转：输入字符串 -> 按格式解析 -> 成功得到 LocalDateTime；格式不匹配 -> 捕获异常并给出提示。
   * 关键参数：输入文本必须和 DateTimeFormatter 的格式保持一致。
   * 返回值说明：parse 成功时返回日期时间对象，失败时不会返回有效对象。
   * 副作用：向控制台输出示例结果或错误提示。
   * </p>
   */
  private static void showParseDateTime() {
    String validText = "2026-07-02 18:45:30";
    String invalidText = "2026/07/02 18:45:30";

    System.out.println("=== 4. 解析字符串日期时间 ===");
    System.out.println("合法文本解析结果：" + parseDateTimeOrNull(validText));
    System.out.println("非法文本解析结果：" + parseDateTimeOrNull(invalidText));
    System.out.println();
  }

  /**
   * 按统一格式解析日期时间文本，解析失败时返回 null。
   *
   * @param text 待解析的日期时间文本，期望格式为 yyyy-MM-dd HH:mm:ss
   * @return 解析成功返回 LocalDateTime，解析失败返回 null
   */
  private static LocalDateTime parseDateTimeOrNull(String text) {
    try {
      return LocalDateTime.parse(text, DATE_TIME_FORMATTER);
    } catch (DateTimeParseException e) {
      // 业务开发中不要让格式错误直接打断主流程，可按场景改成返回错误码或抛出自定义异常。
      System.out.println("解析失败，文本格式应为 yyyy-MM-dd HH:mm:ss，实际值：" + text);
      return null;
    }
  }

  /**
   * 演示日期加减。
   *
   * <p>
   * 关键参数：plusXxx 表示增加，minusXxx 表示减少；这些方法都会返回新对象。
   * 返回值说明：原日期不会被修改，这也是 java.time 更安全的原因之一。
   * 副作用：向控制台输出示例结果。
   * </p>
   */
  private static void showDateCalculate() {
    LocalDate today = LocalDate.now();
    LocalDate tomorrow = today.plusDays(1);
    LocalDate nextWeek = today.plusWeeks(1);
    LocalDate lastMonth = today.minusMonths(1);

    System.out.println("=== 5. 日期加减 ===");
    System.out.println("今天：" + today);
    System.out.println("明天：" + tomorrow);
    System.out.println("下周今天：" + nextWeek);
    System.out.println("上个月今天：" + lastMonth);
    System.out.println("再次输出今天，证明原对象未改变：" + today);
    System.out.println();
  }

  /**
   * 演示日期比较和间隔计算。
   *
   * <p>
   * 关键参数：isBefore、isAfter、isEqual 用于比较先后；ChronoUnit 用于计算两个日期之间的单位数量。
   * 返回值说明：比较方法返回 boolean，between 返回 long。
   * 副作用：向控制台输出示例结果。
   * </p>
   */
  private static void showDateCompare() {
    LocalDate today = LocalDate.now();
    LocalDate deadline = today.plusDays(7);
    long daysLeft = ChronoUnit.DAYS.between(today, deadline);

    System.out.println("=== 6. 日期比较和间隔 ===");
    System.out.println("今天是否早于截止日：" + today.isBefore(deadline));
    System.out.println("今天是否晚于截止日：" + today.isAfter(deadline));
    System.out.println("今天是否等于截止日：" + today.isEqual(deadline));
    System.out.println("距离截止日还有几天：" + daysLeft);
    System.out.println();
  }

  /**
   * 演示时间戳的常见用法。
   *
   * <p>
   * 关键参数：Instant 表示时间线上的一个精确时刻，适合日志、接口传输、数据库记录创建时间。
   * 返回值说明：toEpochMilli 返回从 1970-01-01T00:00:00Z 到当前时刻的毫秒数。
   * 副作用：向控制台输出示例结果。
   * </p>
   */
  private static void showInstantTimestamp() {
    Instant now = Instant.now();
    Instant afterOneMinute = now.plusSeconds(60);
    long epochMilli = now.toEpochMilli();

    System.out.println("=== 7. 时间戳 Instant ===");
    System.out.println("当前时间戳对象：" + now);
    System.out.println("一分钟后：" + afterOneMinute);
    System.out.println("毫秒时间戳：" + epochMilli);
    System.out.println();
  }

  /**
   * 演示跨时区转换。
   *
   * <p>
   * 关键参数：withZoneSameInstant 会保持同一个真实时间点，只改变展示时区。
   * 返回值说明：返回新的 ZonedDateTime，不修改原对象。
   * 副作用：向控制台输出示例结果。
   * </p>
   */
  private static void showTimeZoneConvert() {
    ZonedDateTime shanghaiTime = ZonedDateTime.now(SHANGHAI_ZONE);
    ZonedDateTime newYorkTime = shanghaiTime.withZoneSameInstant(NEW_YORK_ZONE);

    System.out.println("=== 8. 时区转换 ===");
    System.out.println("上海时间：" + shanghaiTime.format(DATE_TIME_FORMATTER));
    System.out.println("纽约时间：" + newYorkTime.format(DATE_TIME_FORMATTER));
    System.out.println("上海时区：" + shanghaiTime.getZone());
    System.out.println("纽约时区：" + newYorkTime.getZone());
    System.out.println();
  }

  /**
   * 演示统计一段代码的耗时。
   *
   * <p>
   * 状态流转：记录开始时刻 -> 执行业务代码 -> 记录结束时刻 -> 计算两个时刻的 Duration。
   * 返回值说明：Duration.toMillis 返回毫秒耗时。
   * 副作用：当前线程短暂休眠，并向控制台输出示例结果。
   * </p>
   */
  private static void showMeasureElapsedTime() {
    Instant start = Instant.now();
    sleepQuietly(120);
    Instant end = Instant.now();
    long elapsedMillis = Duration.between(start, end).toMillis();

    System.out.println("=== 9. 统计代码耗时 ===");
    System.out.println("模拟业务耗时毫秒数：" + elapsedMillis);
    System.out.println();
  }

  /**
   * 让当前线程休眠指定毫秒数，发生中断时恢复中断标记。
   *
   * @param millis 休眠毫秒数，必须大于等于 0
   */
  private static void sleepQuietly(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      // 恢复中断标记，避免上层逻辑丢失“线程已被中断”的状态。
      Thread.currentThread().interrupt();
    }
  }
}
