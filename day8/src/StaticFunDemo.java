/**
 * desc:
 *  1。 静态方法通过类名调用
 *  2. 静态方法只能访问静态变量
 *  3. 静态方法不能调用非静态方法
 */
public class StaticFunDemo {
    String name;
    int age;
    static String school = "郑州大学";

    public StaticFunDemo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    static void change() {
        school = "河南大学";
//        out(); 无法从静态上下文中引用非静态 方法 out()
//        System.out.println(age);  无法从静态上下文中引用非静态 变量 age
    }

    void out() {
        System.out.println(name + " " + age + " " + school);
    }

    public static void main(String[] args) {
        StaticFunDemo.change();

        StaticFunDemo s1 = new StaticFunDemo("沉默王二", 18);
        StaticFunDemo s2 = new StaticFunDemo("沉默王三", 16);

        s1.out();
        s2.out();
    }
}