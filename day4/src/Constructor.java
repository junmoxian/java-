import java.sql.SQLType;

public class Constructor {
    /*构造方法*/
    /*特点：
      1. 与类名相同
      2. 没有返回类型,不能写 void
      3. 自动调用，通过new 操作符
      4. 不能直接调用，不能像普通方法一样被调用，只能通过new
      5. 支持重载，可以预设多个构造函数，参数可以不同，通过重载，可以创建不同的构造方法以适应不同的初始化要求
      6. 提供默认构造器，如没有定义任何构造方法，就使用默认
      7. this 指向当前对象
      8. 不能被继承，但可以被调用，通过super()函数
      9. 对像初始化的保障
    * */
/*    public  Constructor(){
        System.out.println("122");
    }*/
    //有参构造方法
    String name;
    int age;
    public Constructor(String name,int age){
        this.name = name;
        this.age = age;
    }
    public void mytoString(){
        System.out.println("name:" + name + "age:" + age);
    }
    public static void main(String[] args){
        Constructor obj = new Constructor("张三",20);
//        System.out.println(obj);
        obj.mytoString();
    }
}
