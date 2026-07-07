/**
 * 演示抽象类的常见规则：
 * 1. 抽象类不能直接实例化；
 * 2. 抽象类中可以没有抽象方法；
 * 3. 抽象方法只有声明，没有方法体；
 * 4. 构造方法和 static 方法不能声明为 abstract；
 * 5. 非抽象子类必须实现父类中的抽象方法，抽象子类可以暂时不实现。
 */
public class AbstractClassDemo {

    /**
     * 程序入口：通过创建非抽象子类对象，演示抽象类的继承和方法实现。
     *
     * @param args 命令行参数，本示例中未使用
     */
    public static void main(String[] args) {
        // 错误示例：抽象类不能直接创建对象。
        // AbstractWorker worker = new AbstractWorker("抽象员工");

        // 正确示例：父类引用可以指向已经实现完整的非抽象子类对象。
        AbstractWorker teacher = new JavaTeacher("张三");
        teacher.work();
        teacher.rest();

        // static 方法可以定义在抽象类中，但 static 方法本身不能是 abstract。
        AbstractWorker.printRule();

        // Designer 仍然是抽象类，不能直接创建对象。
        // Designer designer = new Designer("李四");

        // UiDesigner 是非抽象子类，已经补全 work 方法，所以可以创建对象。
        AbstractWorker uiDesigner = new UiDesigner("李四");
        uiDesigner.work();
        uiDesigner.rest();
    }
}

/**
 * 抽象父类：负责定义所有员工共有的姓名属性、普通行为和必须由子类实现的工作行为。
 */
abstract class AbstractWorker {
    private final String name;

    /**
     * 创建员工对象时保存姓名。
     *
     * @param name 员工姓名
     */
    public AbstractWorker(String name) {
        this.name = name;
    }

    /**
     * 获取员工姓名，供子类和普通方法复用。
     *
     * @return 员工姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 抽象方法：只规定“员工必须会工作”，具体怎么工作交给不同子类实现。
     */
    public abstract void work();

    /**
     * 普通方法：抽象类中可以包含已经实现好的通用行为。
     */
    public void rest() {
        System.out.println(getName() + " 下班休息");
    }

    /**
     * 类方法：static 方法属于类本身，可以存在于抽象类中，但不能声明为 abstract。
     */
    public static void printRule() {
        System.out.println("抽象类可以有 static 方法，但 static 方法不能是 abstract");
    }

    // 错误示例：构造方法不能声明为 abstract。
    // public abstract AbstractWorker(String name);

    // 错误示例：static 方法不能声明为 abstract。
    // public abstract static void badStaticMethod();
}

/**
 * 抽象类中不一定要有抽象方法，这个类只有普通方法，但仍然可以被声明为 abstract。
 */
abstract class OnlyNormalMethodClass {

    /**
     * 普通方法：用于说明“没有抽象方法的类也可以是抽象类”。
     */
    public void showMessage() {
        System.out.println("我是没有抽象方法的抽象类");
    }
}

/**
 * 非抽象子类：必须实现 AbstractWorker 中的所有抽象方法。
 */
class JavaTeacher extends AbstractWorker {

    /**
     * 创建 Java 老师对象，并把姓名交给父类保存。
     *
     * @param name 老师姓名
     */
    public JavaTeacher(String name) {
        super(name);
    }

    /**
     * 实现父类抽象方法，给出 JavaTeacher 的具体工作内容。
     */
    @Override
    public void work() {
        System.out.println(getName() + " 正在讲 Java 抽象类");
    }
}

/**
 * 抽象子类：因为自己仍然是 abstract，所以可以暂时不实现父类的 work 方法。
 */
abstract class Designer extends AbstractWorker {

    /**
     * 创建设计师对象，并把姓名交给父类保存。
     *
     * @param name 设计师姓名
     */
    public Designer(String name) {
        super(name);
    }
}

/**
 * 非抽象孙子类：继承抽象类 Designer 后，最终必须实现 work 方法。
 */
class UiDesigner extends Designer {

    /**
     * 创建 UI 设计师对象，并把姓名交给父类保存。
     *
     * @param name UI 设计师姓名
     */
    public UiDesigner(String name) {
        super(name);
    }

    /**
     * 实现父类抽象方法，给出 UiDesigner 的具体工作内容。
     */
    @Override
    public void work() {
        System.out.println(getName() + " 正在设计页面");
    }
}
