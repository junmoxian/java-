import java.util.ArrayList;
import java.util.List;

/**
 *  desc: 静态代码块的使用
 *  静态代码块通常用来初始化一些静态变量，它会优先于 main() 方法执行
 *  use: 使用静态代码块来加载配置文件到内存当中
 */
public class StaticCodeBlock {
//    static {
//        System.out.println("静态代码块");
//    }
//
    public static void main(String[] args) {
        System.out.println("main 方法");
    }
    public static List<String> writes = new ArrayList<>();

    static {
        writes.add("沉默王二");
        writes.add("沉默王三");
        writes.add("沉默王四");

        System.out.println("第一块");
    }

    static {
        writes.add("沉默王五");
        writes.add("沉默王六");

        System.out.println("第二块");
    }
}

