/**
 * desc: 被final 修饰了的方法，不能被子类重写
 */
public class FinalFunDemo {
    public static void main(String[] args) {
        Child child = new Child();
        child.sayhi();
    }
}



 class Parent {
   final void sayhi(){
        System.out.println("父类sayhi");
    }
}

class  Child extends Parent{
//    @Override
//    void sayhi(){
//        System.out.println("子类sayhi");
//    }
}