# Java 学习问题回顾

这份笔记整理本轮学习中问到的 Java 基础、IntelliJ 使用、IO 流、继承和接口相关问题，方便之后快速复习。

## 1. IntelliJ 当前文件不能运行

### 问题

IntelliJ 提示“编辑器中的文件不可运行”，或者运行按钮是灰色。

### 核心原因

Java 程序要能直接运行，必须有标准入口方法：

```java
public static void main(String[] args) {
}
```

如果写成下面这样，IntelliJ 不会把它当成程序入口：

```java
public static void main() {
}
```

### 构造方法也要注意

构造方法必须和类名完全一样，并且不能写返回类型：

```java
public class Constructor {
    public Constructor() {
        System.out.println("创建对象");
    }
}
```

如果写成 `public void Constructor()`，它就不是构造方法，而是普通方法。

## 2. 构造方法 Constructor

### 作用

构造方法用于对象初始化，通常在 `new` 对象时自动调用。

### 特点

- 构造方法名必须和类名完全相同。
- 构造方法没有返回类型，不能写 `void`。
- 构造方法通过 `new` 自动调用。
- 构造方法可以重载，也就是多个构造方法参数不同。
- 如果一个构造方法都没写，Java 会自动提供默认无参构造方法。
- 如果自己写了任意构造方法，Java 就不会再自动提供无参构造方法。
- 构造方法不能被继承，但子类构造方法可以通过 `super(...)` 调用父类构造方法。

### 示例

```java
public class Student {
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

## 3. BufferedReader + InputStreamReader

### 作用

常用于从控制台读取用户输入，也常用于刷题或命令行程序。

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String line = br.readLine();
```

### 每层含义

- `System.in`：标准输入，通常表示键盘输入，是字节流。
- `InputStreamReader`：把字节流转换成字符流。
- `BufferedReader`：增加缓冲能力，并提供 `readLine()` 一次读取一整行。

### 常见场景

- 控制台输入姓名、年龄、命令。
- 在线算法题读取大量输入。
- 命令行菜单程序。

### 和 Scanner 的区别

- `Scanner` 更简单，适合初学。
- `BufferedReader` 更快，适合大量输入。

## 4. 开发中常用 IO 类

### 文本优先用 Reader / Writer

文本文件、日志、配置、CSV 这类内容，适合用字符流：

```java
FileReader
FileWriter
BufferedReader
BufferedWriter
```

### 二进制优先用 InputStream / OutputStream

图片、视频、PDF、压缩包这类内容，适合用字节流：

```java
FileInputStream
FileOutputStream
BufferedInputStream
BufferedOutputStream
```

### 复习口诀

```text
文本用 Reader / Writer
二进制用 InputStream / OutputStream
中文内容尽量指定 UTF-8
```

项目里已有两个演示类：

- `src/CommonIoDemo.java`：演示常用 IO 场景。
- `src/BackendFileIoDemo.java`：演示后端常见文件解析、日志追加、报告生成。

## 5. File / FileReader / FileWriter

### File

`File` 表示文件或目录路径，可以判断文件是否存在、是否是文件、是否是目录，也可以获取文件名、大小、绝对路径。

```java
File file = new File("users.csv");

System.out.println(file.exists());
System.out.println(file.isFile());
System.out.println(file.getAbsolutePath());
```

### FileReader

`FileReader` 用来读取文本文件。

```java
try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
}
```

### FileWriter

`FileWriter` 用来写文本文件。

```java
try (FileWriter writer = new FileWriter(file)) {
    writer.write("hello");
}
```

追加写入时使用：

```java
new FileWriter(file, true)
```

其中 `true` 表示不覆盖原文件，而是在文件末尾追加。

## 6. 一个 Java 源文件只能有一个 public class

### 规则

一个 `.java` 文件最多只能有一个 `public class`，并且文件名必须和这个 `public class` 名字一致。

例如：

```text
Animal.java -> public class Animal
Dog.java    -> public class Dog
```

### 学习时可以写在一个文件里

可以有多个非 `public` 类：

```java
public class TestExtends {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();
    }
}

class Animal {
    public void eat() {
        System.out.println("动物吃东西");
    }
}

class Dog extends Animal {
}
```

### 真实开发推荐

真实开发更推荐一个类一个文件，结构清晰，也方便 IntelliJ 管理。

## 7. 没有 package 时的类访问

### 理解

如果多个类都放在同一个 `src` 下，并且文件开头都没有写 `package`，它们就属于默认包。

默认包里的类可以直接互相访问，不需要 `import`。

```java
public class Dog extends Animal {
}
```

### 注意

默认包适合学习阶段。真实项目一般会写包名：

```java
package com.example.demo;
```

并且有包名的类不能正常引用默认包里的类，所以后续学项目时要逐步习惯包结构。

## 8. extends 继承

### 作用

`extends` 表示“子类继承父类”。

```java
public class Dog extends Animal {
}
```

可以理解为：

```text
Dog 是一种 Animal
```

### 继承解决的问题

- 复用父类已有属性和方法。
- 表达类与类之间的“是什么”关系。
- 让子类在父类基础上扩展自己的能力。

### 注意

Java 中一个类只能继承一个父类。

## 9. implements 接口实现

### 作用

`implements` 表示一个类实现某个接口。

接口负责规定行为，类负责写具体实现。

```java
interface Eatable {
    void eat();
}

class Dog implements Eatable {
    public void eat() {
        System.out.println("狗吃东西");
    }
}
```

### 可以这样理解

```text
interface：规定这个类应该具备什么行为
implements：声明这个类会实现这些行为
class：真正写出行为的具体代码
```

### 和 extends 的区别

```java
class Dog extends Animal
```

表示：

```text
Dog 是一种 Animal
```

```java
class Dog implements Eatable
```

表示：

```text
Dog 具备 Eatable 规定的行为
```

### implements 解决的问题

- 统一规则。
- 降低耦合。
- 支持多态。
- 让不同类按照同一套标准工作。

### 后端常见示例

```java
interface UserService {
    void register();
}

class UserServiceImpl implements UserService {
    public void register() {
        System.out.println("用户注册逻辑");
    }
}
```

`UserService` 规定能力，`UserServiceImpl` 实现能力。

## 10. IntelliJ 退出免打扰模式

### 方法一

菜单栏：

```text
View -> Appearance -> Exit Distraction Free Mode
```

中文界面通常是：

```text
视图 -> 外观 -> 退出免打扰模式
```

### 方法二

按两下 `Shift`，搜索：

```text
Distraction Free Mode
```

然后回车切换关闭。

## 11. 当前阶段建议记住的核心句子

- 构造方法是对象初始化时自动调用的方法。
- 文本 IO 用字符流，二进制 IO 用字节流。
- `File` 表示路径，不代表文件一定真实存在。
- 一个 `.java` 文件最多只能有一个 `public class`。
- 默认包里的类可以直接互相访问，但真实项目不推荐默认包。
- `extends` 表示“是什么”。
- `implements` 表示“能做什么”。
- 接口定规则，实现类写具体逻辑。

## 12. final 修饰类时，类成员是否也会变成 final

### 问题

`final` 定义的类，其中的属性、方法是不是也自动变成 `final`？

### 摘要

不是。

`final class` 只表示这个类不能被继承，并不会自动让类中的属性或方法变成 `final`。

```java
public final class User {
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
```

含义：

- `User` 不能再有子类。
- `name` 没有被 `final` 修饰，仍然可以重新赋值。
- `setName` 没有被 `final` 修饰，但因为类本身不能被继承，所以也不会有子类去重写它。

### 复习句子

```text
final 修饰类：禁止继承。
final 修饰属性：禁止二次赋值。
final 修饰方法：禁止子类重写。
```

## 13. 自动类型转换和 char 的特殊情况

### 问题

自动类型转换是不是只能从小类型到大类型？`char` 为什么不能和 `byte`、`short` 自动互转？

### 摘要

一般情况下，自动类型转换是从取值范围小的类型转到取值范围大的类型。

```java
int i = 10;
long l = i;
double d = l;
```

大类型到小类型通常不能自动转换，但如果是编译器能确定范围的常量，可以直接赋值：

```java
byte b1 = 100; // 可以，100 在 byte 范围内

int num = 100;
// byte b2 = num; // 不可以，num 是变量，编译器不保证它一直是 100
```

`char` 比较特殊：

```text
char  范围：0 ~ 65535
byte  范围：-128 ~ 127
short 范围：-32768 ~ 32767
```

所以：

```java
char c = 'A';

int i = c;
long l = c;
float f = c;
double d = c;
```

这些可以自动转换，因为目标类型能容纳 `char` 的全部取值。

但下面这些不可以：

```java
byte b = 65;
short s = 65;

// char c1 = b; // 不可以，byte 可能是负数
// char c2 = s; // 不可以，short 可能是负数

char c = 'A';

// byte b2 = c;  // 不可以，char 最大值可能超过 byte
// short s2 = c; // 不可以，char 最大值可能超过 short
```

### 复习句子

```text
char 可以自动转 int、long、float、double。
byte、short、char 之间不是简单包含关系，所以不能自动互转。
```

## 14. 多态中为什么用父类作为子类对象的类型

### 问题

为什么经常写 `Animal animal = new Dog();`，而不是直接写 `Dog dog = new Dog();`？

### 摘要

父类作为引用类型，是为了用统一的父类标准接收不同子类对象。

```java
Animal animal = new Dog();
animal.eat();
```

这句话的含义：

```text
编译类型：Animal
运行时真实对象：Dog
```

调用方法时：

- 编译时先检查 `Animal` 中有没有 `eat()` 方法。
- 如果父类没有这个方法，编译报错。
- 如果父类有这个方法，运行时再调用真实对象 `Dog` 重写后的方法。

这样可以写出通用代码：

```java
public void feed(Animal animal) {
    animal.eat();
}
```

以后传入 `Dog`、`Cat`、`Bird` 都可以，只要它们是 `Animal` 的子类并实现对应行为。

### 复习句子

```text
父类引用指向子类对象：编译看左边父类，运行看右边子类。
```

## 15. 虚函数

### 问题

什么是虚函数？

### 摘要

虚函数可以理解为：方法调用时，最终执行哪个版本，不只看变量声明类型，而是在运行时看对象真实类型。

```java
class Animal {
    public void eat() {
        System.out.println("动物吃东西");
    }
}

class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }
}

Animal animal = new Dog();
animal.eat(); // 狗吃骨头
```

在 Java 中，普通成员方法默认支持这种动态绑定，所以可以理解为默认就是类似虚函数。

不参与普通多态重写的情况：

- `static` 方法属于类，不属于对象。
- `final` 方法不能被重写。
- `private` 方法子类不可见，不能被重写。
- 构造方法不参与重写。

### 复习句子

```text
虚函数服务于多态：父类引用调用方法，真正执行哪个方法由运行时对象决定。
```

## 16. 抽象类规则

### 问题

抽象类有哪些核心规则？

### 摘要

抽象类可以理解为“半成品类”，它不能直接创建对象，主要用于规定子类必须实现的能力。

核心规则：

- 抽象类不能被实例化，只有非抽象子类可以创建对象。
- 抽象类中不一定有抽象方法，但有抽象方法的类必须是抽象类。
- 抽象方法只有声明，没有方法体。
- 构造方法不能声明为抽象方法。
- `static` 方法不能声明为抽象方法。
- 非抽象子类必须实现父类中的所有抽象方法。
- 如果子类仍然是抽象类，可以暂时不实现父类的抽象方法。

项目中的演示代码：

```text
src/AbstractClassDemo.java
```

### 复习句子

```text
抽象类负责定规则，普通子类负责补全实现。
```

## 17. 成员变量在方法中使用 this 和不使用 this 的区别

### 问题

类成员变量在类方法中不使用 `this` 和使用 `this` 有什么区别？

### 摘要

`this` 表示当前对象。

没有重名变量时，下面两种写法效果一样：

```java
public String getName() {
    return name;
}

public String getName() {
    return this.name;
}
```

有参数或局部变量重名时，必须用 `this` 区分成员变量：

```java
private String name;

public void setName(String name) {
    this.name = name;
}
```

这里：

```text
this.name：当前对象的成员变量
name：方法参数
```

如果写成：

```java
public void setName(String name) {
    name = name;
}
```

只是把参数赋值给参数自己，成员变量不会被修改。

### 注意

`static` 方法中不能使用 `this`，因为 `this` 代表当前对象，而 `static` 方法属于类本身，不属于某个具体对象。

### 复习句子

```text
没有重名时，this 可写可不写；有重名时，this 用来明确访问成员变量。
```
