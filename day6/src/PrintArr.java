import java.util.Arrays;
import java.util.stream.Stream;

/*打印数组*/
public class PrintArr {
    public static void main(String[] args) {
        printStream();
    }
    public static void printStream(){
        int[] cmowers = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(cmowers));
        String [][] str = {{"zhangsan","lisi"},{"wangwu","zhaoliu"}};
        System.out.println(Arrays.deepToString(str));
    }

}
