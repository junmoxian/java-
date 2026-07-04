package characterClass;

public class Main {
  /**
   * 程序入口：按主题演示 Character 类的常见用法。
   *
   * @param args 命令行参数，本示例未使用
   */
  public static void main(String[] args) {
    showBasicUsage();
    showCharacterChecks();
    showCaseConversion();
    showCharAndCharacterDifference();
    showNullUnboxingRisk();
  }

  /**
   * 演示 char 基本类型和 Character 包装类的基础写法。
   */
  private static void showBasicUsage() {
    char letter = 'A';
    Character wrappedLetter = Character.valueOf(letter);

    System.out.println("=== 基础用法 ===");
    System.out.println("char 基本类型：" + letter);
    System.out.println("Character 包装类：" + wrappedLetter);
    System.out.println("字符对应的 Unicode 编码：" + (int) letter);
    System.out.println();
  }

  /**
   * 演示 Character 提供的字符判断方法。
   */
  private static void showCharacterChecks() {
    char letter = 'A';
    char number = '8';
    char space = ' ';
    char chinese = '中';

    System.out.println("=== 字符判断 ===");
    printCheckResult(letter);
    printCheckResult(number);
    printCheckResult(space);
    printCheckResult(chinese);
    System.out.println();
  }

  /**
   * 输出单个字符的判断结果，集中展示 Character 的常用 isXxx 方法。
   *
   * @param value 要检查的字符
   */
  private static void printCheckResult(char value) {
    System.out.println("字符：" + value);
    System.out.println("是否字母：" + Character.isLetter(value));
    System.out.println("是否数字：" + Character.isDigit(value));
    System.out.println("是否空白字符：" + Character.isWhitespace(value));
    System.out.println("是否大写：" + Character.isUpperCase(value));
    System.out.println("是否小写：" + Character.isLowerCase(value));
    System.out.println("---");
  }

  /**
   * 演示 Character 的大小写转换方法。
   */
  private static void showCaseConversion() {
    char lower = 'a';
    char upper = 'Z';

    System.out.println("=== 大小写转换 ===");
    System.out.println(lower + " 转大写：" + Character.toUpperCase(lower));
    System.out.println(upper + " 转小写：" + Character.toLowerCase(upper));
    System.out.println();
  }

  /**
   * 演示 char 和 Character 的区别：char 不能为 null，Character 是对象，可以为 null。
   */
  private static void showCharAndCharacterDifference() {
    char primitiveChar = 'J';
    Character objectChar = Character.valueOf('J');
    Character emptyChar = null;

    System.out.println("=== char 和 Character 的区别 ===");
    System.out.println("char 是基本类型：" + primitiveChar);
    System.out.println("Character 是对象：" + objectChar);
    System.out.println("Character 可以保存 null：" + emptyChar);
    System.out.println("两个 Character 值是否相等：" + objectChar.equals(Character.valueOf('J')));
    System.out.println();
  }

  /**
   * 演示 Character 自动拆箱时的空值风险。
   */
  private static void showNullUnboxingRisk() {
    Character maybeNull = null;

    System.out.println("=== 自动拆箱的 null 风险 ===");
    if (maybeNull == null) {
      System.out.println("Character 是 null 时，不能直接赋值给 char，否则会触发 NullPointerException。");
      return;
    }

    // 只有确认不为 null 后，才让 Character 自动拆箱成 char。
    char safeValue = maybeNull;
    System.out.println("安全拆箱后的字符：" + safeValue);
  }
}
