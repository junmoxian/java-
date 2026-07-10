/**
 *  desc: 静态变量使用
 */
public class StaticVarDemo2 {
    public static   int  count = 0;
    StaticVarDemo2() {
        count++;
        System.out.println(count);
    }
    public  static void main(String[] args) {
        /*count 属于静态变量，所有实例成员共享*/
        StaticVarDemo2 s1 = new StaticVarDemo2();
        StaticVarDemo2 s2 = new StaticVarDemo2();
        StaticVarDemo2 s3 = new StaticVarDemo2();
    }
}
