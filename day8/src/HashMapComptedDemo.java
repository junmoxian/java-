/**
 * desc: 演示取余和取模运算
 */

public class HashMapComptedDemo {
    public static void main(String[] args) {
        int a = -7;
        int b = 3;

       // a 对 b 取余
        int remainder = a % b;
         // a 对 b 取模
        int modulus = Math.floorMod(a, b);

        System.out.println("数字: a = " + a + ", b = " + b);
        System.out.println("取余 (%): " + remainder);
        System.out.println("取模 (Math.floorMod): " + modulus);

     // 改变 a 和 b 的正负情况
        a = 7;
        b = -3;

        remainder = a % b;
        modulus = Math.floorMod(a, b);

        System.out.println("\n数字: a = " + a + ", b = " + b);
        System.out.println("取余 (%): " + remainder);
        System.out.println("取模 (Math.floorMod): " + modulus);
    }
}
