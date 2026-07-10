/**
 *  desc: 调用父类的成员方法
 */
public class SuperDemo2 {
    public static void main(String[] args) {
        new MyDog().work();
    }
}



class MyAnimal {
    void eat() {
        System.out.println("父类吃...");
    }
}

class MyDog extends MyAnimal {
    @Override
    void eat() {
        System.out.println("子类吃...");
    }

    void bark() {
        System.out.println("汪汪汪...");
    }

    void work() {
        super.eat();
        bark();
    }
}

