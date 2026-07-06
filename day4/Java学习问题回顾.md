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
