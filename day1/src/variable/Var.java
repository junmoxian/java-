// package variable;

public class Var {
  public void pupAge() {
    int age = 0; // 声明变量要赋值不然会报错
    age = age + 7;
    System.out.println("小狗的年龄是 : " + age);
  }

  /* 类变量（静态变量） */
  public static int a = 10;

  public static void main(String[] args) {
    // int a = 10;
    // char b = 'A';
    // double c = 3.14;
    // float d = 3.14f;
    // boolean e = true;
    // String f = "Hello, World!";
    // System.out.println("int a = " + a);
    // System.out.println("char b = " + b);
    // System.out.println("double c = " + c);
    // System.out.println("float d = " + d);
    // System.out.println("boolean e = " + e);
    // System.out.println("String f = " + f);
    /* 创建对象 */
    // Var test = new Var();
    // test.pupAge();
    /* 这里count是静态变量，属于 Var 类。main 也是 Var 类里的静态方法，所以可以直接读： */
    System.out.println("类变量 a = " + a);
    /* 通过类访问 */
    System.out.println("通过类访问,类变量 a = " + Var.a);
    /* 通过对象访问 */
    Var test = new Var();
    System.out.println("通过对象访问,类变量 a = " + test.a);

    /* 常量 */
    final int b = 20; // 声明常量
    // b = 30; // 试图修改常量的值，编译器会报错
    System.out.println("常量 b = " + b);
  }
}
