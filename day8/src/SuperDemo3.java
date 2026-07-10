/**
 *  desc: 使用super调用父类构造方法
 */
public class SuperDemo3 {
    public static void main(String[] args) {
        new MyDog2();
    }
}

class MyAnimal2 {
   public MyAnimal2(){
        System.out.println("动物来了");
    }
}

class MyDog2 extends MyAnimal2 {
    public MyDog2() {
        //子类即使不使用 super() 主动调用父类的构造方法，父类的构造方法仍然会先执行
//        super();
        System.out.println("狗狗来了");
    }
}