/**
 * desc: super关键字的使用
 * 1. super可以用来访问父类的成员变量
 * 2. super可以用来访问父类的方法
 * 3. super可以用来调用父类的构造方法
 * 4. super关键字必须在子类的构造方法中使用，且必须是子类构造方法的第一行
 */


    public class SuperDemo {
        public static void main(String[] args) {
            new Dog().printColor();
        }
    }

    class Animal {
        String color = "白色";
    }

    class Dog extends Animal {
        String color = "黑色";

        void printColor() {
            System.out.println(color); // 子类成员变量
            System.out.println(super.color); // 调用父类成员变量
        }
    }

