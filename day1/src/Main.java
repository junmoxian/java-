import objClass.Puppy;

public class Main {
  public static void main(String[] args) {
    // 局部变量：只在 main 方法内部存在。
    int count = 2;

    // 类变量：所有 Student 对象共享这一份学校名称。
    Student.schoolName = "Java 学校";

    Student stu1 = new Student("张三", 20);
    Student stu2 = new Student("李四", 22);

    System.out.println("学生数量：" + count);
    stu1.showInfo();
    stu2.showInfo();

    // 修改类变量后，所有 Student 对象读取到的学校名称都会变化。
    Student.schoolName = "高级 Java 学校";

    stu1.showInfo();
    stu2.showInfo();
  }
}

/**
 * Student 用于演示局部变量、成员变量和类变量。
 */
class Student {
  // 类变量：static 表示该值属于类，而不是某一个对象。
  static String schoolName;

  // 成员变量：每一个 Student 对象都有自己的姓名和年龄。
  String name;
  int age;

  /**
   * 创建 Student 对象，并初始化成员变量。
   *
   * @param name 学生姓名
   * @param age  学生年龄
   */
  Student(String name, int age) {
    this.name = name;
    this.age = age;
  }

  /**
   * 输出当前学生的信息。
   */
  void showInfo() {
    // 局部变量：方法执行时创建，方法结束后销毁。
    String message = "学校：" + schoolName + "，姓名：" + name + "，年龄：" + age;

    System.out.println(message);
  }
}
