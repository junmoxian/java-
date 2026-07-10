/**
 * desc: 调用父类有参构造函数
 */
class Person {
    int id;
    String name;

    Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Emp extends Person {
    float salary;

    Emp(int id, String name, float salary) {
        super(id, name);
        this.salary = salary;
    }

    void display() {
        System.out.println(id + " " + name + " " + salary);
    }
}

public class SuperDemo4 {
    public static void main(String[] args) {
        new Emp(1, "沉默王二", 20000f).display();
    }
}