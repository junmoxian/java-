/*
        this() 可以调用当前类的构造方法；
        this 可以作为参数在方法中传递；
        this 可以作为参数在构造方法中传递；
        this 可以作为方法的返回值，返回当前类的对象
        this 关键字在构造函数中调用时只能放在构造方法的第一行
        */
public class ThisDemo {
   String name;
   int age;
   ThisDemo() {
      this("猪爸爸",29); // 可以·用来调用有参构造方法
      System.out.println("hello");

   }

   ThisDemo(String name, int age) {
      /*name = name; // 实例变量名相同，由于没有使用 this 关键字，所以无法为实例变量赋值
      age = age;*/
//      this(); // 可以·用来调用无参构造方法
      this.age = age;
      this.name = name;
   }
   void myLog(ThisDemo obj){
      System.out.println(obj);
   }
   //this作为参数传递
   void transmission(){
      myLog(this);
   }
   void out() {
      System.out.println(name+" " + age);
   }

   void run() {
        out();
//      this.out();
   }
   public static void main(String[] args) {
      ThisDemo s1 = new ThisDemo("沉默王二", 18);
      ThisDemo s2 = new ThisDemo("沉默王三", 16);
      ThisDemo s3 = new ThisDemo();
      s1.out();
      s2.out();
      System.out.println("s3:" + s3);
      s3.transmission();
   }
}
