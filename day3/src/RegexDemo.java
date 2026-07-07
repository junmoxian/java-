import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Java 正则表达式常用方法演示。
 *
 * <p>核心类：
 * 1. Pattern：编译后的正则表达式。
 * 2. Matcher：用 Pattern 去匹配某个字符串的匹配器。
 * 3. PatternSyntaxException：正则语法错误时抛出的异常。</p>
 */
public class RegexDemo {

    public static void main(String[] args) {
        demoBasicMatch();
        demoMatchesFindLookingAt();
        demoCaptureGroup();
        demoReplace();
        demoSplit();
        demoFlags();
        demoCommonValidation();
        demoCommonExtract();
        demoQuoteUserInput();
        demoPatternSyntaxException();
    }

    /**
     * 1. 基本使用：Pattern + Matcher。
     */
    public static void demoBasicMatch() {
        printTitle("1. 基本使用 Pattern + Matcher");

        String text = "订单编号：A20260705001，金额：299";

        // Java 字符串里反斜杠要写两个。
        // 正则里的 \d 表示数字，在 Java 字符串中要写成 "\\d"。
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("找到数字：" + matcher.group());
            System.out.println("开始下标：" + matcher.start());
            System.out.println("结束下标：" + matcher.end());
        }
    }

    /**
     * 2. matches、find、lookingAt 的区别。
     */
    public static void demoMatchesFindLookingAt() {
        printTitle("2. matches、find、lookingAt 区别");

        String regex = "\\d+";
        String text = "123abc";

        Pattern pattern = Pattern.compile(regex);

        // matches：要求整个字符串都符合正则。
        System.out.println("matches：" + pattern.matcher(text).matches());

        // find：只要字符串里有一部分符合正则就返回 true。
        System.out.println("find：" + pattern.matcher(text).find());

        // lookingAt：要求从字符串开头开始匹配，但不要求匹配完整字符串。
        System.out.println("lookingAt：" + pattern.matcher(text).lookingAt());

        System.out.println("String.matches 也是完整匹配：" + text.matches(regex));
    }

    /**
     * 3. 捕获组：用小括号把想提取的内容圈起来。
     */
    public static void demoCaptureGroup() {
        printTitle("3. 捕获组 group");

        String text = "姓名：张三，年龄：20";
        Pattern pattern = Pattern.compile("姓名：(.+)，年龄：(\\d+)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            System.out.println("group(0) 整体匹配：" + matcher.group(0));
            System.out.println("group(1) 第 1 组：" + matcher.group(1));
            System.out.println("group(2) 第 2 组：" + matcher.group(2));
            System.out.println("捕获组数量：" + matcher.groupCount());
        }

        String orderText = "订单号：NO-2026-0001";
        Pattern namedPattern = Pattern.compile("订单号：(?<prefix>[A-Z]+)-(?<year>\\d{4})-(?<number>\\d+)");
        Matcher namedMatcher = namedPattern.matcher(orderText);

        if (namedMatcher.find()) {
            System.out.println("命名分组 prefix：" + namedMatcher.group("prefix"));
            System.out.println("命名分组 year：" + namedMatcher.group("year"));
            System.out.println("命名分组 number：" + namedMatcher.group("number"));
        }
    }

    /**
     * 4. 替换：replaceAll 和 replaceFirst。
     */
    public static void demoReplace() {
        printTitle("4. 替换 replaceAll / replaceFirst");

        String phone = "手机号：13812345678";

        // 隐藏手机号中间 4 位。
        String maskedPhone = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        System.out.println(maskedPhone);

        String text = "java python java go";
        System.out.println("替换所有 java：" + text.replaceAll("java", "Java"));
        System.out.println("只替换第一个 java：" + text.replaceFirst("java", "Java"));
    }

    /**
     * 5. 切割字符串：split。
     */
    public static void demoSplit() {
        printTitle("5. 切割 split");

        String text = "Java, Python;Go  JavaScript";

        // 按逗号、分号或多个空格切割。
        String[] words = text.split("[,;\\s]+");

        for (String word : words) {
            System.out.println(word);
        }
    }

    /**
     * 6. 匹配模式：忽略大小写、多行匹配。
     */
    public static void demoFlags() {
        printTitle("6. 匹配模式 flags");

        String text = "Java\njava\nJAVA";

        Pattern ignoreCasePattern = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
        Matcher matcher = ignoreCasePattern.matcher(text);

        while (matcher.find()) {
            System.out.println("忽略大小写找到：" + matcher.group());
        }

        Pattern lineStartPattern = Pattern.compile("^java", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        Matcher lineMatcher = lineStartPattern.matcher(text);

        while (lineMatcher.find()) {
            System.out.println("多行模式行首匹配：" + lineMatcher.group() + "，下标：" + lineMatcher.start());
        }
    }

    /**
     * 7. 开发常用：简单校验。
     */
    public static void demoCommonValidation() {
        printTitle("7. 开发常用：校验");

        String username = "zhangsan_26";
        String usernameRegex = "^[a-zA-Z][a-zA-Z0-9_]{5,15}$";
        System.out.println("用户名是否合法：" + username.matches(usernameRegex));

        String phone = "13812345678";
        String phoneRegex = "^1[3-9]\\d{9}$";
        System.out.println("手机号是否合法：" + phone.matches(phoneRegex));

        String email = "test@example.com";

        // 邮箱规则非常复杂，真实业务一般使用成熟工具类或后端统一校验规则。
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        System.out.println("邮箱是否合法：" + email.matches(emailRegex));
    }

    /**
     * 8. 开发常用：从文本中提取信息。
     */
    public static void demoCommonExtract() {
        printTitle("8. 开发常用：提取信息");

        String log = "2026-07-05 16:20:30 INFO userId=10086 cost=35ms";
        Pattern pattern = Pattern.compile("userId=(\\d+)\\s+cost=(\\d+)ms");
        Matcher matcher = pattern.matcher(log);

        if (matcher.find()) {
            String userId = matcher.group(1);
            String cost = matcher.group(2);

            System.out.println("用户 ID：" + userId);
            System.out.println("耗时毫秒：" + cost);
        }
    }

    /**
     * 9. 开发常用：把用户输入当普通文本匹配。
     */
    public static void demoQuoteUserInput() {
        printTitle("9. Pattern.quote 防止特殊字符误伤");

        String text = "用户搜索内容：a+b?，不是普通的 ab";
        String userInput = "a+b?";

        // 如果直接 Pattern.compile(userInput)，+ 和 ? 会被当成正则特殊符号。
        Pattern pattern = Pattern.compile(Pattern.quote(userInput));
        Matcher matcher = pattern.matcher(text);

        System.out.println("是否找到用户输入的原始文本：" + matcher.find());
    }

    /**
     * 10. 正则写错时的异常。
     */
    public static void demoPatternSyntaxException() {
        printTitle("10. PatternSyntaxException");

        try {
            Pattern.compile("[abc");
        } catch (PatternSyntaxException e) {
            System.out.println("正则语法错误：" + e.getDescription());
        }
    }

    public static void printTitle(String title) {
        System.out.println();
        System.out.println("========== " + title + " ==========");
    }
}
