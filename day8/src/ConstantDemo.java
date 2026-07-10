/**
 *  desc: 常量
 */
public class ConstantDemo {
    public static  final  String CONSTANT_VALUE = "This is a constant value";

    /*作为参数修饰符，让形参不能被修改*/
   void fun(final int param) {
//         param = 10; // 编译错误，不能修改final参数
    }
    public static void main(String[] args) {
        System.out.println(CONSTANT_VALUE);
        ConstantDemo obj  = new ConstantDemo();
        obj.fun(3);
    }
}
