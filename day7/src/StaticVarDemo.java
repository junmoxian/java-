/**
 * 演示 static 静态变量的反例和正确用法。
 *
 * 静态变量属于类本身，类加载时创建，程序结束或类卸载时销毁；
 * 所有对象共享同一份静态变量，因此可变静态变量要谨慎封装。
 */
public class StaticVarDemo {
    /**
     * 正确用法：对外公开且不会变化的数据，声明为 public static final 常量。
     */
    public static final String COURSE_NAME = "Java static 变量演示";

    /**
     * 反例：public static 的可变变量会被外部任意改写，容易造成状态不可控。
     */
    public static int badPublicCounter = 0;

    /**
     * 正确用法：可变静态变量使用 private 封装，只通过方法暴露必要操作。
     */
    private static int createdCount;

    /**
     * 静态变量的默认值和实例变量相似：int 为 0，boolean 为 false，引用类型为 null。
     */
    private static int defaultNumber;
    private static boolean defaultFlag;
    private static String defaultText;

    /**
     * 静态变量可以在静态代码块中初始化，静态代码块在类加载时执行一次。
     */
    private static String sharedMessage;

    static {
        sharedMessage = "静态代码块已经完成初始化";
    }

    /**
     * 实例变量属于每个对象，每个对象各有一份自己的 name。
     */
    private final String name;

    /**
     * 创建对象时维护对象名称，并累加对象创建数量。
     *
     * @param name 当前对象的名称
     */
    public StaticVarDemo(String name) {
        this.name = name;
        createdCount++;
    }

    /**
     * 打印当前对象信息，展示实例变量不同、静态变量共享。
     */
    public void printObjectInfo() {
        System.out.println(name + " 看到的 createdCount = " + createdCount);
        System.out.println(name + " 看到的 sharedMessage = " + sharedMessage);
    }

    /**
     * 更新共享消息，演示所有对象会同时看到同一份静态变量的新值。
     *
     * @param message 新的共享消息
     */
    public static void updateSharedMessage(String message) {
        if (message == null || message.isBlank()) {
            sharedMessage = "共享消息不能为空，已使用默认提示";
            return;
        }
        sharedMessage = message;
    }

    /**
     * 返回已创建对象数量，避免外部直接修改 createdCount。
     *
     * @return 当前已创建的 StaticVarDemo 对象数量
     */
    public static int getCreatedCount() {
        return createdCount;
    }

    /**
     * 打印静态变量默认值，说明静态变量和实例变量拥有相似默认值规则。
     */
    private static void printDefaultValues() {
        System.out.println("defaultNumber = " + defaultNumber);
        System.out.println("defaultFlag = " + defaultFlag);
        System.out.println("defaultText = " + defaultText);
    }

    /**
     * 用注释展示不能编译或不推荐的 static 变量反例。
     */
    private static void printWrongExamples() {
        System.out.println("反例 1：static 变量不能声明在方法、构造方法或普通语句块内部。");
        /*
         * 下面代码如果取消注释，会编译失败：
         *
         * public void wrongMethod() {
         *     static int localCounter = 0;
         * }
         */

        System.out.println("反例 2：public static 可变变量会被外部随意修改。");
        badPublicCounter = 100;
        System.out.println("badPublicCounter 被直接改成了 " + badPublicCounter);
    }

    /**
     * 程序入口：依次演示反例、默认值、共享静态变量和推荐访问方式。
     *
     * @param args 命令行参数，本示例未使用
     */
    public static void main(String[] args) {
        System.out.println("课程名称常量：" + COURSE_NAME);

        printWrongExamples();

        System.out.println("\n静态变量默认值：");
        printDefaultValues();

        System.out.println("\n创建两个对象后，它们共享同一份 createdCount：");
        StaticVarDemo first = new StaticVarDemo("对象 first");
        StaticVarDemo second = new StaticVarDemo("对象 second");
        first.printObjectInfo();
        second.printObjectInfo();
        System.out.println("通过方法读取 createdCount = " + StaticVarDemo.getCreatedCount());

        System.out.println("\n修改 sharedMessage 后，两个对象看到的都是新值：");
        StaticVarDemo.updateSharedMessage("所有对象共享这一份静态变量");
        first.printObjectInfo();
        second.printObjectInfo();
    }
}
