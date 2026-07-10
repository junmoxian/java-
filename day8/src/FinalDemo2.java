/**
 * desc: 被final 修饰了的类，不能被子类继承
 */
public class FinalDemo2 {
    public static void main(String[] args) {
        Child child = new Child();
        child.sayhi();
    }
}



final class MyParent {
    final void sayhi(){
        System.out.println("父类sayhi");
    }
}

//class  MyChild extends MyParent{
//    @Override
//    void sayhi(){
//        System.out.println("子类sayhi");
//    }
//}