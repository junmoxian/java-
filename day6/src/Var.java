public class Var {
    //byte、short、char 参与一元算术运算时，会自动提升为 int
    public static void main(String[] args) {
        byte b = 10;
        short s = 20;
        char c = 'A'; // 'A' 的 ASCII 值为 65

        // 一元算术运算
        int result1 = b + 5; // byte 自动提升为 int
        int result2 = s + 10; // short 自动提升为 int
        int result3 = c + 1; // char 自动提升为 int
//        byte result4 = b + 20; // byte 自动提升为 int，但需要强制类型转换回 byte
        byte result4 = (byte)(b+20); //语法: (要强制转换的类型) 数值变量
        System.out.println("Result of byte + 5: " + result1);
        System.out.println("Result of short + 10: " + result2);
        System.out.println("Result of char + 1: " + result3);
        System.out.println("Result of byte + 20 (with casting): " + result4);

        showVar();
    }
    // 隐式转换不只有类型提升，大类型转换小类型同样适用
    public  static void showVar(){
        byte b = 50;
        b *= 2;
        System.out.println("Result of byte *= 2: " + b); // 输出 100
        System.out.println("b装箱后的类型是:" + ((Object) b).getClass().getSimpleName()); // 输出byte
    }
}
