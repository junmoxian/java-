
/**
 * 演示 {@link JsonField} 如何控制 {@link JsonSerializer} 的序列化结果。
 */
public class Annotated {
    // 没有添加 @JsonField，JsonSerializer 会忽略 age 字段。
    private int age;

    // 注解中指定了自定义 key，所以序列化后字段名为 "writerName"。
    @JsonField("writerName")
    private String name;

    // 注解没有指定 value，所以序列化后沿用原字段名 "bookName"。
    @JsonField
    private String bookName;

    /**
     * 创建一个用于注解序列化演示的对象。
     *
     * @param age 年龄，没有 {@link JsonField}，不会进入 JSON
     * @param name 作者名，会使用自定义 key "writerName" 序列化
     * @param bookName 书名，会使用原字段名 "bookName" 序列化
     */
    public Annotated(int age, String name, String bookName) {
        this.age = age;
        this.name = name;
        this.bookName = bookName;
    }

    // 此处省略 getter / setter，示例中直接通过构造方法赋值。

    /**
     * 返回当前演示对象的可读字符串形式。
     *
     * @return 便于调试查看的对象字段信息
     */
    @Override
    public String toString() {
        return "Annotated{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }

    /**
     * 运行注解序列化演示。
     *
     * @param args 命令行参数，本示例未使用
     * @throws IllegalAccessException 当 JsonSerializer 无法读取字段时抛出
     */
    public static void main(String[] args) throws IllegalAccessException {
        Annotated cmower = new Annotated(18,"沉默王二","Web全栈开发进阶之路");
        System.out.println(JsonSerializer.serialize(cmower));
    }
}
