# Java 初学问答摘要笔记

这份笔记从之前的问答中提炼，目标是以后快速回忆：目录怎么放、文件怎么命名、怎么编译运行、变量和 `static` 怎么理解、包和多文件怎么组织、抽象类与 `protected` 怎么判断。

## 1. 练习 Java 推荐目录

初学简单版适合练变量、循环、数组、方法、类：

```text
day1/
├─ src/
│  └─ Main.java
└─ out/
```

常用编译运行：

```powershell
javac -d out src/Main.java
java -cp out Main
```

后续学习 Maven、测试、Spring Boot 时，会逐渐过渡到标准项目结构：

```text
day1/
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  └─ com/example/day1/
│  │  │     └─ Main.java
│  │  └─ resources/
│  └─ test/
│     └─ java/
│        └─ com/example/day1/
│           └─ MainTest.java
├─ pom.xml
└─ README.md
```

当前练习阶段可以先按知识点拆目录，例如：

```text
src/
├─ dataType/
├─ variable/
├─ modifier/
├─ objClass/
├─ abstractClass/
└─ for/
```

## 2. 文件名、类名、main 方法

Java 不是要求所有文件名大写，而是要求：

```text
如果文件里有 public class，文件名必须和 public 类名完全一致，包括大小写。
```

常见对应关系：

```text
Main.java  -> 文件名
Main       -> 类名，类名习惯首字母大写
main       -> 方法名，程序入口方法固定小写
```

正确示例：

```java
public class Main {
  public static void main(String[] args) {
    System.out.println("Hello Java");
  }
}
```

运行时写的是类名，不是文件名，也不是 `main` 方法名：

```powershell
javac Main.java
java Main
```

不要写成：

```powershell
java main
java Main.java
java Main.class
```

## 3. `javac` 和 `java` 的含义

```powershell
javac Main.java
```

含义：用 Java 编译器把 `Main.java` 源代码编译成 `Main.class` 字节码。

```powershell
java Main
```

含义：用 Java 运行器运行 `Main` 这个类，并寻找里面的：

```java
public static void main(String[] args)
```

一句话记：

```text
javac Main.java  -> 编译 .java，生成 .class
java Main        -> 运行类里的 main 方法
```

## 4. Code Runner 与大小写缓存

如果报错类似：

```text
java.lang.NoClassDefFoundError: main (wrong name: Main)
```

核心原因通常是：运行命令用了小写 `main`，但真实类名是大写 `Main`。

如果文件已经是 `Main.java`，类也是 `public class Main`，但 Code Runner 仍执行：

```powershell
java main
```

可能是 VS Code / Code Runner 还缓存着小写文件名。处理办法：

```text
1. 关闭 main.java 标签页。
2. 从资源管理器重新打开 Main.java。
3. 重新运行。
4. 如果仍不行，可临时改名 Temp.java，再改回 Main.java，并重启 VS Code。
```

## 5. 中文输出与 UTF-8 编码

如果出现：

```text
错误: 编码 GBK 的不可映射字符
```

通常是因为文件保存为 UTF-8，但 `javac` 默认按系统编码 GBK 去读。

最稳的编译方式：

```powershell
javac -encoding UTF-8 Main.java
java Main
```

如果希望 Code Runner 编译 Java 时也使用 UTF-8，可以在项目的 `.vscode/settings.json` 中配置 Java 的执行命令，让 `javac` 带上：

```text
-encoding UTF-8
```

如果想让 `javac Main.java` 默认使用 UTF-8，可以设置环境变量：

```powershell
setx JAVA_TOOL_OPTIONS "-Dfile.encoding=UTF-8"
```

设置后需要重启 VS Code / 终端。这个方式会影响 Java 工具的默认编码，范围更大，使用前要知道它不是只针对单个文件。

## 6. 多文件、目录和 package

在 Java 里，`src` 下面的目录通常会被 IDE 当成包路径。

例如：

```text
src/
├─ Main.java
└─ demo/
   └─ Student.java
```

那么 `Student.java` 推荐写：

```java
package demo;

public class Student {
  public String name;
}
```

`Main.java` 使用它时：

```java
import demo.Student;

public class Main {
  public static void main(String[] args) {
    Student stu = new Student();
    stu.name = "张三";
  }
}
```

记忆：

```text
同一个包：不用 import
不同包：需要 import
java.lang 包：不用 import，例如 String、System
```

初学时文件少，可以都放在 `src` 下，不写 `package`。代码量变大后，再按主题拆包：

```text
src/
├─ basic/
├─ oop/
├─ array/
├─ string/
└─ util/
```

包名习惯全小写，例如 `oop`、`demo`、`objclass`。

## 7. 有 package 时怎么运行

如果类写了：

```java
package objClass;
```

它的完整类名就不是 `PuppyDemo`，而是：

```text
objClass.PuppyDemo
```

正确运行方式通常从项目根目录执行：

```powershell
javac -encoding UTF-8 -d out src\objClass\*.java
java -cp out objClass.PuppyDemo
```

不要直接进入包目录后运行：

```powershell
cd src\objClass
java PuppyDemo
```

因为 Java 运行的是完整类名，包名也是类名的一部分。

## 8. 什么类可以直接运行

任何类只要有正确的 `main` 方法，就可以作为程序入口运行：

```java
public static void main(String[] args) {
}
```

有 `main` 方法的类：

```text
可以直接运行，是入口类。
```

没有 `main` 方法的类：

```text
不能直接运行，只是普通类，只能被别的类创建或调用。
```

例如 `Puppy` 只是对象模板时不能单独运行，`PuppyDemo` 里有 `main` 方法时才适合作为入口。

## 9. 类、构造方法、对象

这句不是类声明，而是构造方法：

```java
public Puppy(String name) {
  System.out.println("小狗的名字是：" + name);
}
```

完整结构应该是：

```java
public class Puppy {
  public Puppy(String name) {
    System.out.println("小狗的名字是：" + name);
  }
}
```

区别：

```text
public class Puppy     -> 类声明，有 class 关键字
public Puppy(String)   -> 构造方法，没有返回值类型，名字必须和类名一样
new Puppy("旺财")      -> 创建对象时自动调用构造方法
```

`Puppy.java` 里和文件名对应的 `Puppy` 叫类名，不叫主程序。真正的主程序入口是 `main` 方法。

## 10. 三种变量

局部变量：写在方法、构造方法或代码块里面，方法执行结束后销毁。

```java
public static void main(String[] args) {
  int count = 2;
}
```

成员变量：写在类里面、方法外面，属于每一个对象。

```java
class Student {
  String name;
  int age;
}
```

类变量：写在类里面、方法外面，并且用 `static` 修饰，属于类，所有对象共享。

```java
class Student {
  static String schoolName;
}
```

一句话记：

```text
局部变量：方法里，临时存在
成员变量：类里方法外，每个对象一份
类变量：类里方法外，加 static，所有对象共享一份
```

## 11. main 为什么能读 static 变量

`main` 方法本身是静态方法：

```java
public static void main(String[] args)
```

所以它可以直接访问同一个类里的静态变量：

```java
public class Var {
  static int count = 10;

  public static void main(String[] args) {
    System.out.println(count);
    System.out.println(Var.count);
  }
}
```

但是 `main` 不能直接访问普通成员变量：

```java
public class Var {
  int normalNum = 20;

  public static void main(String[] args) {
    // System.out.println(normalNum); // 错误

    Var v = new Var();
    System.out.println(v.normalNum); // 正确
  }
}
```

一句话记：

```text
static 方法可以直接访问 static 成员；
static 方法访问普通成员，需要先 new 对象。
```

## 12. 局部变量不能声明为 static

错误写法：

```java
public class Main {
  public static void main(String[] args) {
    static int age = 18; // 错误
  }
}
```

原因：

```text
局部变量属于方法，方法结束就销毁。
static 变量属于类，类存在它就存在。
```

这两个概念冲突，所以 Java 不允许局部变量加 `static`。

正确写法：

```java
public class Main {
  static int age = 18;

  public static void main(String[] args) {
    int num = 10;
  }
}
```

## 13. `public static final String APP_NAME`

这句：

```java
public static final String APP_NAME = "MyApp";
```

拆开理解：

```text
public   -> 访问修饰符
static   -> 静态，属于类
final    -> 只能赋值一次，不能重新赋值
String   -> 数据类型
APP_NAME -> 常量名
"MyApp"  -> 初始值
```

更准确的注释：

```java
/* 访问修饰符 | static 静态 | final 不可变 | 数据类型 | 常量名 */
```

常量命名习惯：

```text
全大写 + 下划线
```

例如：

```java
public static final int MAX_COUNT = 100;
public static final double PI = 3.14159;
public static final String APP_NAME = "MyApp";
```

## 14. String、int、byte 的区别

`String` 首字母大写，因为它是 Java 已经写好的类：

```java
java.lang.String
```

`int`、`double`、`boolean`、`char` 是基本数据类型，关键字固定小写。

记忆：

```text
类名大写：String、Student、Main
变量名小写：name、age、student
基本类型小写：int、double、boolean、char
```

`byte` 是 Java 的整数类型，中文常叫字节型：

```text
占 1 个字节，也就是 8 个 bit
范围：-128 到 127
```

示例：

```java
byte age = 18;
byte max = 127;
byte min = -128;
```

## 15. 水平输出和换行

`println`：输出后换行。

```java
System.out.println("姓名：" + name);
```

`print`：输出后不换行。

```java
System.out.print("姓名：" + name);
```

`printf`：按格式输出。

```java
System.out.printf("姓名：%s，年龄：%d%n", name, age);
```

九九乘法表横向输出的关键：

```java
for (int i = 1; i <= 9; i++) {
  for (int j = 1; j <= i; j++) {
    System.out.print(j + "*" + i + "=" + (i * j) + "\t");
  }
  System.out.println();
}
```

记忆：

```text
内层循环用 print，保证同一行横向输出；
外层循环结束后用 println，换到下一行。
```

## 16. 基类、父类、子类

可以这样类比：

```text
基类 = 父类
子类 = 直接或间接继承它的类
```

示例：

```java
class Animal {
}

class Dog extends Animal {
}

class Husky extends Dog {
}
```

关系：

```text
Animal -> 基类 / 父类
Dog    -> Animal 的直接子类
Husky  -> Dog 的直接子类，也是 Animal 的间接子类
```

所以“子类”可以包含儿子、孙子、重孙子这类后代类。

## 17. 抽象类和抽象方法

抽象方法只有声明，没有方法体：

```java
public abstract void work();
```

规则：

```text
抽象方法必须写在抽象类中。
抽象方法不能声明为 final。
抽象方法不能声明为 static。
继承抽象类的普通子类，必须实现父类所有抽象方法。
如果子类不实现所有抽象方法，那子类也必须是抽象类。
抽象类可以没有抽象方法。
```

示例：

```java
abstract class Father {
  public abstract void work();

  public void start() {
    System.out.println("父类开始安排任务");
    work();
  }
}

class Son extends Father {
  @Override
  public void work() {
    System.out.println("子类具体完成任务");
  }

  public void playGame() {
    System.out.println("子类自己的方法");
  }
}

public class Main {
  public static void main(String[] args) {
    Father f = new Son();

    f.start();
    f.work();

    // f.playGame(); // 错误：Father 类型看不到 Son 独有方法
  }
}
```

核心理解：

```text
父类变量能调用父类声明过的方法；
真正执行谁的方法，看实际对象是谁；
子类独有的方法，父类类型的变量不能直接调用。
```

`Father f = new Son();` 的含义：

```text
变量类型是 Father；
实际对象是 Son；
编译时看变量类型能调用哪些方法；
运行时看实际对象执行哪个重写版本。
```

## 18. protected 关键字

`protected` 不是要求同一个文件，而是和包、继承有关。

基本规则：

```text
同一个包：可以访问 protected 成员。
不同包 + 是子类：可以访问继承来的 protected 成员。
不同包 + 不是子类：不能访问。
```

`protected` 常用于修饰：

```text
成员变量
成员方法
构造方法
```

不同包时最容易混：

```java
package child;

import base.Parent;

public class Child extends Parent {
  public void test() {
    sayHello();       // 可以：访问自己继承来的 protected 方法
    this.sayHello();  // 可以
    super.sayHello(); // 可以

    Child c = new Child();
    c.sayHello();     // 可以：引用类型是 Child

    Parent p1 = new Parent();
    // p1.sayHello(); // 不可以：基类实例

    Parent p2 = new Child();
    // p2.sayHello(); // 不可以：引用类型是 Parent
  }
}
```

一句话记：

```text
同包：protected 包内可访问。
异包：子类只能通过 this、super、或子类类型的引用，访问继承来的 protected 成员；
不能通过基类类型的引用访问。
```

## 19. 最常用速记表

| 问题 | 速记 |
| --- | --- |
| `Main.java`、`Main`、`main` 区别 | 文件名、类名、入口方法名 |
| 运行时写什么 | 写类名：`java Main` |
| 有包时运行什么 | 写完整类名：`java -cp out 包名.类名` |
| 什么类能运行 | 有正确 `main` 方法的类 |
| 普通类不能运行的原因 | 没有程序入口 |
| `String` 为什么大写 | 它是类 |
| `int` 为什么小写 | 它是基本类型关键字 |
| `static` 变量属于谁 | 属于类 |
| 普通成员变量属于谁 | 属于对象 |
| `main` 调普通方法 | 先 `new` 对象 |
| 局部变量能否 static | 不能 |
| `final` 是什么 | 只能赋值一次 |
| `protected` 看什么 | 看包和继承，不看是否同文件 |
| `println` 和 `print` | 前者换行，后者不换行 |

## 20. Number 类和数字包装类

`Number` 是 Java 数字包装类的共同父类。

前端可以这样类比：

```text
JavaScript 里常用 Number 表示数字；
Java 里数字分得更细，有基本类型和包装类。
```

Java 常见数字类型：

```java
int age = 18;              // 基本类型，不是对象
Integer count = 10;        // 包装类，是对象
Double price = 99.9;       // 包装类，是对象
Number number = count;     // 父类引用，实际对象是 Integer
```

`Number` 本身是抽象类，不能直接创建对象：

```java
// Number n = new Number(); // 错误：Number 是抽象类

Number n1 = Integer.valueOf(10);
Number n2 = Double.valueOf(3.14);
```

`Number` 常用于统一接收不同类型的数字对象：

```java
Number num = Double.valueOf(1234.56);

System.out.println(num.intValue());    // 1234，小数会被截掉
System.out.println(num.longValue());   // 1234
System.out.println(num.floatValue());  // 1234.56
System.out.println(num.doubleValue()); // 1234.56
```

基本类型和包装类的区别：

```text
int、double：基本类型，适合普通计算。
Integer、Double：包装类，是对象，可以放进集合，可以为 null。
Number：这些数字包装类的共同父类。
Math：数学工具类，提供 round、floor、ceil、random 等方法。
```

自动装箱和拆箱：

```java
Integer x = 5; // 自动装箱：int -> Integer
x = x + 10;   // 自动拆箱参与计算，再自动装箱
```

包装类比较值时，优先用 `equals`：

```java
Integer a = 200;
Integer b = 200;

System.out.println(a == b);      // 比较对象地址，可能是 false
System.out.println(a.equals(b)); // 比较值，是 true
```

一句话记：

```text
基本类型负责计算；
包装类负责当对象用；
Number 负责统一表示数字对象；
Math 负责提供数学工具方法。
```

## 21. Character 类代码示例

`char` 是基本类型，`Character` 是它的包装类。

```java
char letter = 'A';
Character wrappedLetter = Character.valueOf(letter);

System.out.println(letter);
System.out.println(wrappedLetter);
System.out.println((int) letter); // 输出 Unicode 编码
```

常用判断方法：

```java
Character.isLetter('A');     // 是否字母：true
Character.isDigit('8');      // 是否数字：true
Character.isWhitespace(' '); // 是否空白字符：true
Character.isUpperCase('A');  // 是否大写：true
Character.isLowerCase('a');  // 是否小写：true
```

大小写转换：

```java
Character.toUpperCase('a'); // 'A'
Character.toLowerCase('Z'); // 'z'
```

`char` 和 `Character` 的区别：

```java
char primitiveChar = 'J';
Character objectChar = Character.valueOf('J');
Character emptyChar = null;
```

区别速记：

```text
char 是基本类型，不能为 null。
Character 是对象，可以为 null。
Character 可以调用工具方法和参与泛型、集合等对象场景。
```

自动拆箱的空值风险：

```java
Character maybeNull = null;

// char value = maybeNull; // 会触发 NullPointerException

if (maybeNull != null) {
  char value = maybeNull;
  System.out.println(value);
}
```

项目中新增的练习文件：

```text
src/characterClass/Main.java
```

运行方式：

```powershell
javac -encoding UTF-8 -d .\out .\src\characterClass\Main.java
java -cp .\out characterClass.Main
```

一句话记：

```text
char 是单个字符；
Character 是字符对象；
Character.xxx 通常是字符判断或转换工具方法。
```

## 22. 类名.xxx 为什么有时不用 import

`import` 的作用不是加载类，而是让你可以少写完整包名。

是否需要 `import`，主要看包：

```text
同一个 package：不用 import。
不同 package：通常需要 import。
不想 import：可以写完整包名。
```

同包示例：

```java
public class AppConfig {
  public static final String APP_NAME = "MyApp";
}
```

```java
public class Main {
  public static void main(String[] args) {
    System.out.println(AppConfig.APP_NAME);
  }
}
```

如果两个类在同一个包里，Java 能直接找到 `AppConfig`，所以不用 `import`。

不同包示例：

```java
package config;

public class AppConfig {
  public static final String APP_NAME = "MyApp";
}
```

其他包里使用时：

```java
import config.AppConfig;

public class Main {
  public static void main(String[] args) {
    System.out.println(AppConfig.APP_NAME);
  }
}
```

也可以不导入，直接写完整类名：

```java
System.out.println(config.AppConfig.APP_NAME);
```

`类名.xxx` 通常是在访问静态成员：

```java
AppConfig.APP_NAME;
Math.random();
Integer.MAX_VALUE;
Character.isDigit('8');
```

是否需要 `new`，看是不是 `static`：

```java
Student stu = new Student();
stu.name = "张三";   // 普通成员：对象点

AppConfig.APP_NAME; // static 成员：类名点
```

两个问题不要混在一起：

```text
需不需要 import：看类在哪个 package。
需不需要 new：看访问的是 static 成员还是对象成员。
```

一句话记：

```text
同包不用 import；
不同包要 import；
static 用类名点；
普通成员用对象点。
```

## 23. String 类快速入门

参考页面：

```text
https://www.runoob.com/java/java-string.html
```

`String` 是 Java 里专门表示文本的类，前端可以先把它理解成 JavaScript 里的字符串。

核心区别是：Java 的 `String` 是类对象，而且内容不可变。

```java
String site = "Runoob";
String name = new String("Runoob");
```

日常更推荐第一种：

```java
String site = "Runoob";
```

因为字符串字面量会进入字符串常量池，Java 可以复用相同内容的字符串。

### String 不可变

```java
String text = "Google";
text = "Runoob";
```

这不是把原来的 `"Google"` 改成 `"Runoob"`，而是让变量 `text` 指向了一个新的字符串对象。

前端类比：

```js
let text = "Google";
text = "Runoob";
```

变量可以重新赋值，但字符串内容本身不可变。

### String 长度

```java
String text = "hello";
System.out.println(text.length());
```

注意：

```text
String 长度：text.length()
数组长度：arr.length
```

`String` 的长度是方法，所以有括号。

数组的长度是属性，所以没有括号。

### 字符串比较

Java 比较字符串内容时，优先用 `equals()`：

```java
String name = "Tom";

System.out.println(name.equals("Tom"));
```

不要用：

```java
name == "Tom"
```

`==` 更偏向比较两个引用是不是指向同一个对象，`equals()` 才是比较字符串内容。

为了避免空指针，可以把常量写前面：

```java
System.out.println("Tom".equals(name));
```

### 常用方法速记

| 方法 | 作用 | 前端类比 |
| --- | --- | --- |
| `length()` | 获取长度 | `str.length` |
| `charAt(index)` | 取指定位置字符 | `str[index]` 或 `charAt` |
| `equals(value)` | 比较内容 | `===` 的值比较场景 |
| `equalsIgnoreCase(value)` | 忽略大小写比较 | 先都转小写再比 |
| `contains(value)` | 是否包含 | `includes()` |
| `startsWith(value)` | 是否以某内容开头 | `startsWith()` |
| `endsWith(value)` | 是否以某内容结尾 | `endsWith()` |
| `indexOf(value)` | 第一次出现的位置 | `indexOf()` |
| `lastIndexOf(value)` | 最后一次出现的位置 | `lastIndexOf()` |
| `substring(start, end)` | 截取字符串 | `slice()` / `substring()` |
| `trim()` | 去掉首尾空白 | `trim()` |
| `toLowerCase()` | 转小写 | `toLowerCase()` |
| `toUpperCase()` | 转大写 | `toUpperCase()` |
| `replace(old, new)` | 替换文本 | `replace()` |
| `split(separator)` | 分割为数组 | `split()` |

一句话记：

```text
String 是 Java 的文本类型；
它不可变；
长度用 length()；
内容比较用 equals()。
```

## 24. StringBuffer 和 StringBuilder

参考页面：

```text
https://www.runoob.com/java/java-stringbuffer.html
```

因为 `String` 不可变，所以频繁拼接或修改字符串时，不适合一直用 `+`。

例如：

```java
String html = "";
html = html + "<div>";
html = html + "hello";
html = html + "</div>";
```

每次拼接都可能创建新的字符串对象。少量拼接没问题，但循环里大量拼接就不划算。

这时可以用 `StringBuilder`：

```java
StringBuilder html = new StringBuilder();

html.append("<div>");
html.append("hello");
html.append("</div>");

String result = html.toString();
```

前端类比：

```js
const parts = [];

parts.push("<div>");
parts.push("hello");
parts.push("</div>");

const result = parts.join("");
```

### StringBuilder

`StringBuilder` 是可变字符串容器，适合大多数多次拼接场景。

```java
StringBuilder sb = new StringBuilder();

sb.append("Hello");
sb.append(", ");
sb.append("Java");

System.out.println(sb.toString());
```

常用方法：

```java
sb.append("abc");        // 追加
sb.insert(0, "Hi ");     // 插入
sb.delete(0, 3);         // 删除
sb.replace(0, 5, "Yo");  // 替换
sb.reverse();            // 反转
sb.length();             // 长度
sb.toString();           // 转回 String
```

### StringBuffer

`StringBuffer` 和 `StringBuilder` 用法很像，但 `StringBuffer` 是线程安全的。

```java
StringBuffer sb = new StringBuffer();

sb.append("www");
sb.append(".runoob");
sb.append(".com");

System.out.println(sb.toString());
```

### 怎么选择

| 类 | 是否可变 | 线程安全 | 常见用途 |
| --- | --- | --- | --- |
| `String` | 不可变 | 安全 | 普通文本、少量拼接 |
| `StringBuilder` | 可变 | 不保证 | 大多数多次拼接场景 |
| `StringBuffer` | 可变 | 是 | 多线程共享同一个字符串容器 |

一句话记：

```text
普通文本用 String；
大量拼接用 StringBuilder；
多线程共享时才考虑 StringBuffer。
```

## 25. 项目里的 String 常用方法示例

本次已经新增练习文件：

```text
src/stringClass/Main.java
```

运行方式：

```powershell
javac -encoding UTF-8 -d out src\stringClass\Main.java
java -cp out stringClass.Main
```

这个示例按主题演示了：

```text
String 不可变
length()
charAt()
equals()
equalsIgnoreCase()
compareTo()
contains()
startsWith()
endsWith()
indexOf()
lastIndexOf()
substring()
trim()
toLowerCase()
toUpperCase()
replace()
replaceFirst()
replaceAll()
split()
String.join()
String.format()
String.valueOf()
StringBuilder
```

其中有几个边界点要记住：

```text
charAt(index)：index 不能越界。
substring(start, end)：start 和 end 不能越界。
indexOf 找不到时返回 -1。
replaceAll 的第一个参数是正则表达式。
equals 比较内容，== 不适合做字符串内容比较。
```

## 26. Java 数组快速入门

参考页面：

```text
https://www.runoob.com/java/java-array.html
```

Java 数组就是固定长度、同一种类型的数据容器。

前端类比：

```js
const names = ["Tom", "Jack", "Lucy"];
```

Java 写法：

```java
String[] names = {"Tom", "Jack", "Lucy"};
```

区别是：Java 数组只能放一种类型。

```java
int[] scores = {80, 90, 100};
String[] names = {"Tom", "Jack", "Lucy"};
```

`int[]` 只能放整数，`String[]` 只能放字符串。

### 声明和创建

```java
int[] numbers;
numbers = new int[3];
```

也可以合起来：

```java
int[] numbers = new int[3];
```

直接初始化：

```java
int[] numbers = {10, 20, 30};
String[] names = {"Tom", "Jack", "Lucy"};
```

### 访问和修改

数组下标从 0 开始：

```java
String[] names = {"Tom", "Jack", "Lucy"};

System.out.println(names[0]); // Tom
System.out.println(names[1]); // Jack
System.out.println(names[2]); // Lucy
```

修改元素：

```java
int[] scores = {80, 90, 100};
scores[0] = 85;
```

数组长度：

```java
System.out.println(scores.length);
```

注意这里是 `length`，不是 `length()`。

### 遍历数组

普通 `for`，适合需要下标时使用：

```java
int[] scores = {80, 90, 100};

for (int i = 0; i < scores.length; i++) {
  System.out.println("第 " + i + " 个分数：" + scores[i]);
}
```

增强 `for`，类似前端的 `for...of`：

```java
for (int score : scores) {
  System.out.println(score);
}
```

前端类比：

```js
for (const score of scores) {
  console.log(score);
}
```

### 数组作为参数和返回值

作为参数：

```java
public static void printArray(int[] arr) {
  for (int value : arr) {
    System.out.println(value);
  }
}
```

调用时创建匿名数组：

```java
printArray(new int[]{3, 1, 2});
```

作为返回值：

```java
public static int[] reverse(int[] arr) {
  int[] result = new int[arr.length];

  for (int i = 0; i < arr.length; i++) {
    result[arr.length - 1 - i] = arr[i];
  }

  return result;
}
```

### 二维数组

二维数组可以理解成数组里面放数组：

```java
int[][] matrix = {
  {1, 2, 3},
  {4, 5, 6}
};

System.out.println(matrix[0][0]); // 1
System.out.println(matrix[1][2]); // 6
```

前端类比：

```js
const matrix = [
  [1, 2, 3],
  [4, 5, 6]
];
```

一句话记：

```text
Java 数组长度固定；
元素类型固定；
下标从 0 开始；
长度用 arr.length。
```

## 27. java.util.Arrays 方法会不会原地修改

`java.util.Arrays` 是 Java 提供的数组工具类。

使用前需要导入：

```java
import java.util.Arrays;
```

常用方法：

```java
int[] numbers = {3, 1, 2};

Arrays.sort(numbers);
System.out.println(Arrays.toString(numbers));
```

### Java 是值传递

Java 永远是值传递。

但数组变量里保存的是数组对象的引用值。

所以把数组传给方法时，传进去的是引用值的一份拷贝。方法内部可以通过这个引用找到原数组并修改它的内容。

```java
public static void changeValue(int[] arr) {
  arr[0] = 99;
}

public static void changeRef(int[] arr) {
  arr = new int[]{7, 8, 9};
}

public static void main(String[] args) {
  int[] nums = {1, 2, 3};

  changeValue(nums);
  System.out.println(Arrays.toString(nums)); // [99, 2, 3]

  changeRef(nums);
  System.out.println(Arrays.toString(nums)); // [99, 2, 3]
}
```

理解：

```text
arr[0] = 99：修改的是原数组内容。
arr = new int[]{7, 8, 9}：只是让方法内部的 arr 指向新数组，不影响外面的 nums。
```

### Arrays 方法分类

| 方法 | 是否修改原数组 | 返回值 |
| --- | --- | --- |
| `Arrays.sort(arr)` | 是 | `void` |
| `Arrays.parallelSort(arr)` | 是 | `void` |
| `Arrays.fill(arr, value)` | 是 | `void` |
| `Arrays.setAll(arr, generator)` | 是 | `void` |
| `Arrays.copyOf(arr, newLength)` | 否 | 新数组 |
| `Arrays.copyOfRange(arr, from, to)` | 否 | 新数组 |
| `Arrays.binarySearch(arr, key)` | 否 | 下标 |
| `Arrays.equals(a, b)` | 否 | `boolean` |
| `Arrays.toString(arr)` | 否 | 字符串 |
| `Arrays.asList(arr)` | 特殊 | 返回 List 视图 |

重点记：

```java
Arrays.sort(nums);                    // 原地修改
Arrays.fill(nums, 0);                 // 原地修改
int[] copy = Arrays.copyOf(nums, nums.length); // 返回新数组
```

如果是对象数组，`copyOf` 返回的是新数组，但里面的对象引用还是原来的对象，属于浅拷贝。

一句话记：

```text
sort、fill、setAll 通常原地改；
copyOf、copyOfRange 返回新数组；
Java 是值传递，但数组里存的是引用值。
```

## 28. Java 里怎么表示 C 语言的结构体

C 语言里会用 `struct` 表示一组不同类型的数据：

```c
struct Product {
  char name[50];
  double price;
  int stock;
};
```

Java 里通常用 `class` 表示这种结构：

```java
class Product {
  String name;
  double price;
  int stock;
}
```

创建一个商品对象：

```java
Product apple = new Product();

apple.name = "苹果";
apple.price = 5.5;
apple.stock = 100;
```

### 数组里放自定义结构

Java 数组只能放一种类型，但这个类型可以是自定义类。

```java
Product[] products = new Product[2];
```

意思是：数组里每一项都是一个 `Product` 对象。

```java
products[0] = new Product();
products[0].name = "苹果";
products[0].price = 5.5;
products[0].stock = 100;

products[1] = new Product();
products[1].name = "香蕉";
products[1].price = 3.2;
products[1].stock = 80;
```

前端类比：

```js
const products = [
  { name: "苹果", price: 5.5, stock: 100 },
  { name: "香蕉", price: 3.2, stock: 80 }
];
```

Java 可以用构造方法写得更清楚：

```java
class Product {
  String name;
  double price;
  int stock;

  Product(String name, double price, int stock) {
    this.name = name;
    this.price = price;
    this.stock = stock;
  }
}
```

使用：

```java
Product[] products = {
  new Product("苹果", 5.5, 100),
  new Product("香蕉", 3.2, 80)
};

for (Product product : products) {
  System.out.println(product.name + "，价格：" + product.price);
}
```

### Java 16+ 的 record

如果只是用来装数据，也可以使用 `record`：

```java
record Product(String name, double price, int stock) {}
```

使用：

```java
Product apple = new Product("苹果", 5.5, 100);

System.out.println(apple.name());
System.out.println(apple.price());
System.out.println(apple.stock());
```

### 怎么选择

| 需求 | Java 写法 |
| --- | --- |
| 一个商品结构 | `class Product` |
| 多个商品，数量固定 | `Product[] products` |
| 多个商品，需要动态增删 | `ArrayList<Product>` |
| 只是存数据，不怎么改 | `record Product(...)` |

一句话记：

```text
Java 数组只能放一种类型；
但这个类型可以是你自己定义的类；
类里面可以组合 String、double、int 等不同字段。
```

## 29. 当前阶段总速记

| 问题 | 记法 |
| --- | --- |
| Java 里的文本 | `String` |
| `String` 能不能改内容 | 不能，重新赋值是指向新对象 |
| 字符串比较内容 | 用 `equals()` |
| 大量拼接字符串 | 用 `StringBuilder` |
| 多线程共享字符串容器 | 用 `StringBuffer` |
| 数组能放几种类型 | 一种 |
| 数组长度能不能变 | 不能 |
| 数组长度怎么取 | `arr.length` |
| 字符串长度怎么取 | `str.length()` |
| 数组想动态增删怎么办 | 后面学 `ArrayList` |
| 多种类型组成一项数据 | 定义 `class` 或 `record` |
| 多个商品怎么放 | `Product[]` 或 `ArrayList<Product>` |
| `Arrays.sort()` | 原地修改数组 |
| `Arrays.copyOf()` | 返回新数组 |
| Java 参数传递 | 永远是值传递 |
