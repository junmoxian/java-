/**
 *  desc: 静态变量使用
 */
public class StaticVar {
    public   int  count = 0;
    StaticVar() {
        count++;
        System.out.println(count);
    }
    public  static void main(String[] args) {
        /*实例化一个对象，count就会创建一个副本*/
        StaticVar s1 = new StaticVar();
        StaticVar s2 = new StaticVar();
        StaticVar s3 = new StaticVar();
    }
}
