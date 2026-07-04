// package objClass;

public class Puppy {
  String name;
  int age;

  /* 构造方法 */
  public Puppy(String name) {
    this.name = name;
    System.out.println("小狗的名字是 : " + name);
  }

  public void setName(String name) {
    this.name = name;
  }

  public int setAge(int age) {
    this.age = age;
    return age;
  }

  public int getAge() {
    System.out.println("小狗的年龄是 : " + age);
    return age;
  }

  public String getName() {
    System.out.println("小狗的名字是 : " + name);
    return name;
  }

  public static void main(String[] args) {
    // 下面的语句将创建一个Puppy对象
    Puppy myPuppy = new Puppy("tommy");
    myPuppy.setAge(2);
    myPuppy.getName();
    myPuppy.getAge();
  }
}
