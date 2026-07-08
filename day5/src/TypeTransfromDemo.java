/*
  在java当中，两种不同的数据类型进行运算，需要把运算成员都转换为同一类型，否则会报错。
  这个过程称为类型转换（Type Transformation）。在Java中，类型转换分为两种：自动类型转换（隐式转换）和强制类型转换（显式转换）。
*/
public class TypeTransfromDemo {
    public static void main(String[] args) {
        add();
        add2();
        add3();
    }
  /*隐式转换, 小类型与大类型运算会被提升到大类型，
  *  byte -> short -> int -> long -> float -> double
  * */
    public static void add(){
        byte a = 10;
        int num =30;
        int result = a + num;
        System.out.println(result);
    }
    /*显示转换,大类型转小类型,数值过大容易溢出*/
    public static void add2(){
        int a = 128;
        byte num = 30;
        byte result = (byte)(a + num);
        System.out.println(result); // - 98
    }
    public static void add3(){
        byte a = 127;
        byte num = 30;
        int result = a + num;
        System.out.println(result); // 157
    }
}
