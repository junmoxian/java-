package stringClass;

import java.util.Arrays;

/**
 * 演示 Java String 常用方法的入口类。
 *
 * <p>
 * 本示例面向前端同学理解：Java 的 String 很像 JavaScript 字符串，
 * 但常见操作通常通过方法调用完成，例如 length()、equals()、contains()。
 * </p>
 */
public class Main {
  /**
   * 程序入口：按主题依次演示 String 的创建、读取、比较、查找、截取、替换、分割和格式化。
   *
   * @param args 命令行参数，本示例未使用
   */
  public static void main(String[] args) {
    showImmutableString();
    showLengthAndCharAt();
    showEqualsAndCompare();
    showSearchMethods();
    showSubstringMethods();
    showCaseAndTrimMethods();
    showReplaceMethods();
    showSplitMethods();
    showFormatAndValueOf();
    showStringBuilderForManyChanges();
  }

  /**
   * 演示 String 不可变：重新赋值并不是修改原对象，而是让变量指向新的字符串。
   */
  private static void showImmutableString() {
    String title = "Hello";
    String oldTitle = title;

    title = title + " Java";

    System.out.println("=== String 不可变 ===");
    System.out.println("旧字符串仍然是：" + oldTitle);
    System.out.println("变量 title 现在指向新字符串：" + title);
    System.out.println();
  }

  /**
   * 演示 length() 和 charAt()：读取字符串长度以及指定下标位置的字符。
   */
  private static void showLengthAndCharAt() {
    String text = "Java String";
    int index = 5;

    System.out.println("=== 长度与取字符 ===");
    System.out.println("文本：" + text);
    System.out.println("length() 长度：" + text.length());

    // Java 和 JavaScript 一样，下标从 0 开始；读取前先判断范围，避免越界异常。
    if (index >= 0 && index < text.length()) {
      System.out.println("charAt(" + index + ") 字符：" + text.charAt(index));
    }
    System.out.println();
  }

  /**
   * 演示 equals()、equalsIgnoreCase() 和 compareTo()：比较字符串内容或排序关系。
   */
  private static void showEqualsAndCompare() {
    String inputName = "Tom";
    String savedName = "tom";

    System.out.println("=== 字符串比较 ===");
    System.out.println("equals 区分大小写：" + inputName.equals(savedName));
    System.out.println("equalsIgnoreCase 不区分大小写：" + inputName.equalsIgnoreCase(savedName));
    System.out.println("compareTo 按字典顺序比较：" + inputName.compareTo(savedName));

    // 判断固定值时，把常量写在前面，可以避免 inputName 为 null 时触发 NullPointerException。
    System.out.println("安全写法，判断是否等于 Tom：" + "Tom".equals(inputName));
    System.out.println();
  }

  /**
   * 演示 contains()、startsWith()、endsWith()、indexOf() 和 lastIndexOf()：查找文本位置或包含关系。
   */
  private static void showSearchMethods() {
    String url = "https://www.runoob.com/java/java-string.html";

    System.out.println("=== 查找与包含 ===");
    System.out.println("contains(\"java\")：" + url.contains("java"));
    System.out.println("startsWith(\"https\")：" + url.startsWith("https"));
    System.out.println("endsWith(\".html\")：" + url.endsWith(".html"));
    System.out.println("indexOf(\"java\") 第一次出现位置：" + url.indexOf("java"));
    System.out.println("lastIndexOf(\"java\") 最后一次出现位置：" + url.lastIndexOf("java"));
    System.out.println("indexOf 找不到时返回：" + url.indexOf("vue"));
    System.out.println();
  }

  /**
   * 演示 substring()：从原字符串中截取一段新字符串。
   */
  private static void showSubstringMethods() {
    String fileName = "avatar.profile.png";
    int dotIndex = fileName.lastIndexOf(".");

    System.out.println("=== 截取字符串 ===");
    System.out.println("substring(0, 6)：" + fileName.substring(0, 6));
    System.out.println("substring(7)：" + fileName.substring(7));

    // 截取前先确认分隔符存在；lastIndexOf 找不到会返回 -1。
    if (dotIndex != -1 && dotIndex + 1 < fileName.length()) {
      String extension = fileName.substring(dotIndex + 1);
      System.out.println("文件扩展名：" + extension);
    }
    System.out.println();
  }

  /**
   * 演示 toLowerCase()、toUpperCase() 和 trim()：大小写转换以及去掉首尾空白。
   */
  private static void showCaseAndTrimMethods() {
    String keyword = "  JavaScript  ";

    System.out.println("=== 大小写与去空白 ===");
    System.out.println("原文本：[" + keyword + "]");
    System.out.println("trim()：[" + keyword.trim() + "]");
    System.out.println("toLowerCase()：" + keyword.trim().toLowerCase());
    System.out.println("toUpperCase()：" + keyword.trim().toUpperCase());
    System.out.println();
  }

  /**
   * 演示 replace()、replaceFirst() 和 replaceAll()：替换字符或匹配规则。
   */
  private static void showReplaceMethods() {
    String sentence = "Java is good, Java is powerful.";
    String phone = "138-1234-5678";

    System.out.println("=== 替换字符串 ===");
    System.out.println("replace 替换所有 Java：" + sentence.replace("Java", "Spring"));
    System.out.println("replaceFirst 只替换第一个 Java：" + sentence.replaceFirst("Java", "Spring"));

    // replaceAll 的第一个参数是正则表达式，这里用 \d 匹配数字，用于演示脱敏效果。
    System.out.println("replaceAll 手机号脱敏：" + phone.replaceAll("\\d", "*"));
    System.out.println();
  }

  /**
   * 演示 split() 和 join()：把字符串拆成数组，再把数组拼回字符串。
   */
  private static void showSplitMethods() {
    String tagsText = "java,html,css,vue";
    String[] tags = tagsText.split(",");

    System.out.println("=== 分割与拼接数组 ===");
    System.out.println("split 后的数组：" + Arrays.toString(tags));
    System.out.println("String.join 拼回字符串：" + String.join(" | ", tags));
    System.out.println();
  }

  /**
   * 演示 String.format() 和 String.valueOf()：格式化文本以及把其他类型转成字符串。
   */
  private static void showFormatAndValueOf() {
    String userName = "Alex";
    int score = 98;
    double price = 19.9;
    String scoreText = String.valueOf(score);

    System.out.println("=== 格式化与类型转换 ===");
    System.out.println(String.format("用户：%s，分数：%d，价格：%.2f", userName, score, price));
    System.out.println("String.valueOf(score)：" + scoreText);
    System.out.println();
  }

  /**
   * 演示 StringBuilder：当需要多次修改或循环拼接字符串时，优先使用 StringBuilder。
   */
  private static void showStringBuilderForManyChanges() {
    String[] menuItems = { "首页", "课程", "关于我们" };
    StringBuilder html = new StringBuilder();

    System.out.println("=== 多次拼接用 StringBuilder ===");

    // StringBuilder 会在同一个对象上追加内容，避免循环中反复创建临时 String。
    html.append("<nav>");
    for (String item : menuItems) {
      html.append("<a>").append(item).append("</a>");
    }
    html.append("</nav>");

    System.out.println(html.toString());
  }
}
