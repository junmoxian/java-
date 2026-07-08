import java.util.regex.Pattern;

public class SplitDemo {
    private static Pattern twopart = Pattern.compile("\\.");
    public static void main(String [] args){
        showSplit();
        showSplit2();
        showSplit3();
        showSplit4();
        showSplit5();
    }
    public  static void showSplit(){
                String cmower = "沉默王二，一枚有趣的程序员";
                if (cmower.contains("，")) {
                    String [] parts = cmower.split("，");
                    System.out.println("第一部分：" + parts[0] +" 第二部分：" + parts[1]);
                } else {
                    throw new IllegalArgumentException("当前字符串没有包含逗号");
                }
    }
    public static void showSplit2(){
        String cmower = "沉默王二.一枚有趣的程序员";
        if (cmower.contains(".")) {
            String [] parts = cmower.split("\\.");
            System.out.println("第一部分：" + parts[0] +" 第二部分：" + parts[1]);
        }
    }
    public static void showSplit3(){
        String cmower = "沉默王二.一枚有趣的程序员";
        String [] parts = cmower.split(Pattern.quote("."));
            System.out.println("第一部分：" + parts[0] +" 第二部分：" + parts[1]);

    }

    public static void showSplit4(){
        String [] parts = twopart.split("沉默王二.一枚有趣的程序员");
        System.out.println("第一部分：" + parts[0] +" 第二部分：" + parts[1]);

    }
    public static void showSplit5(){
        String cmower = "沉默王二，一枚有趣的程序员，宠爱他";
        if (cmower.contains("，")) {
            String [] parts = cmower.split("，", 2);
            System.out.println("第一部分：" + parts[0] +" 第二部分：" + parts[1]);
        }
    }
}
