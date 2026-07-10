# Java 类型系统学习笔记

> 面向有 TypeScript 基础的 Java 初学者，重点理解：类型、类、变量、引用、对象以及泛型之间的关系。

## 一、核心摘要

Java 变量必须先确定类型，变量中保存的是与该类型兼容的值。

```java
int age = 18;
User user = new User();
```

- `int` 是基本类型，`age` 直接保存整数值 `18`。
- `User` 是类名，同时也是一个引用类型。
- `user` 是引用变量，它保存指向 `User` 对象的引用。
- `new User()` 才是在运行时创建对象。

一句话概括：

> 类负责描述对象，也会定义一个同名的引用类型；变量使用类型约束自己能保存什么值。

## 二、拆解对象初始化语句

```java
User user = new User();
```

可以拆成三部分：

```text
User        user        new User()
变量类型     变量名       创建对象
```

只声明变量不会创建对象：

```java
User user;
```

创建对象并赋值：

```java
user = new User();
```

概念上可以理解为：

```text
User 类型的变量 user ─────> User 对象
```

## 三、Java 类型的两大类别

```text
Java 类型
├── 基本类型
│   ├── byte、short、int、long
│   ├── float、double
│   ├── char
│   └── boolean
└── 引用类型
    ├── 类 class
    ├── 接口 interface
    ├── 数组
    ├── 枚举 enum
    └── record 等
```

### 1. 基本类型

```java
int age = 18;
double price = 9.9;
boolean passed = true;
```

基本类型变量保存基本值，不能使用 `new int()` 创建，也不能赋值为 `null`。

```java
// 编译错误：基本类型不能保存 null。
// int age = null;
```

### 2. 引用类型

```java
User user = new User();
String name = "张三";
int[] numbers = new int[3];
```

`User`、`String` 和 `int[]` 都是引用类型，引用类型变量可以赋值为 `null`：

```java
User user = null;
```

此时变量没有指向对象，调用方法会产生 `NullPointerException`：

```java
// 运行时抛出 NullPointerException。
user.introduce();
```

## 四、类、对象、变量和引用

下面的类定义了一种名为 `User` 的引用类型，并规定了对象拥有的状态和行为：

```java
/**
 * 表示一个用户，演示类、对象与引用变量之间的关系。
 */
class User {
    String name;
    int age;

    /**
     * 输出当前用户的自我介绍。
     */
    void introduce() {
        System.out.println("我是" + name + "，今年" + age + "岁");
    }
}
```

创建并使用对象：

```java
User user = new User();
user.name = "张三";
user.age = 18;
user.introduce();
```

两个变量可以引用同一个对象：

```java
User user1 = new User();
User user2 = user1;

// user1 和 user2 指向同一个对象，所以能观察到同一次修改。
user1.name = "张三";
System.out.println(user2.name); // 张三
```

关系如下：

```text
user1 ──┐
        ├────> 同一个 User 对象
user2 ──┘
```

如果分别调用两次 `new`，则会创建两个对象：

```java
User user1 = new User();
User user2 = new User();
```

## 五、声明类型与实际类型

Java 允许父类变量引用子类对象：

```java
/**
 * 表示所有动物共有的行为。
 */
class Animal {
    /**
     * 输出动物进食行为，子类可以重写。
     */
    void eat() {
        System.out.println("动物吃东西");
    }
}

/**
 * 表示一种具体动物，并扩展狗特有的行为。
 */
class Dog extends Animal {
    /**
     * 重写父类行为，提供狗的具体实现。
     */
    @Override
    void eat() {
        System.out.println("狗吃骨头");
    }

    /**
     * 输出狗特有的叫声。
     */
    void bark() {
        System.out.println("汪汪");
    }
}
```

使用多态：

```java
Animal animal = new Dog();
```

```text
Animal animal = new Dog();
──────         ─────────
声明类型         实际类型
编译时类型       运行时类型
```

- 能否调用某个成员，主要由左边的声明类型决定。
- 重写方法实际执行哪个版本，由右边的实际对象决定。

```java
animal.eat(); // 合法，实际输出“狗吃骨头”。

// 编译错误：Animal 类型中没有声明 bark 方法。
// animal.bark();
```

记忆口诀：

> 能不能调用看左边；重写方法执行哪个版本看实际对象。

## 六、向上转型与向下转型

子类对象赋值给父类变量称为向上转型，Java 会自动完成：

```java
Animal animal = new Dog();
```

向下转型前要确认实际对象类型：

```java
if (animal instanceof Dog dog) {
    // 模式匹配同时完成类型判断和安全转换。
    dog.bark();
}
```

如果实际对象不是 `Dog`，强制转换会抛出 `ClassCastException`。

## 七、接口也可以作为变量类型

```java
/**
 * 规定对象必须具备飞行能力。
 */
interface Flyable {
    /**
     * 执行飞行动作。
     */
    void fly();
}

/**
 * 使用鸟实现 Flyable 接口。
 */
class Bird implements Flyable {
    /**
     * 提供鸟类的具体飞行实现。
     */
    @Override
    public void fly() {
        System.out.println("鸟在飞");
    }
}
```

接口类型变量可以引用实现类对象：

```java
Flyable flyable = new Bird();
flyable.fly();
```

它表达的是：调用方只依赖“可以飞”这个能力，不必依赖具体的 `Bird` 类。

Java 集合经常使用同样的写法：

```java
List<String> names = new ArrayList<>();
```

- `List<String>` 是声明类型。
- `ArrayList<String>` 是实际对象类型。
- `ArrayList` 实现了 `List` 接口，因此赋值合法。

## 八、包装类与自动装箱

Java 的基本类型都有对应的包装类：

| 基本类型 | 包装类 |
| --- | --- |
| `int` | `Integer` |
| `long` | `Long` |
| `double` | `Double` |
| `boolean` | `Boolean` |
| `char` | `Character` |

```java
int number1 = 10;
Integer number2 = 10; // 自动装箱，概念上类似 Integer.valueOf(10)。
```

`Integer` 是类，因此变量可以为 `null`；`int` 是基本类型，不能为 `null`。

```java
Integer number = null;

// 自动拆箱时需要调用对象，因此这里会抛出 NullPointerException。
int result = number;
```

## 九、数组也是引用类型

```java
int[] numbers = new int[3];
```

`numbers` 引用一个数组对象，数组创建后内容是：

```text
[0, 0, 0]
```

创建引用类型数组时，不会自动创建其中的元素对象：

```java
User[] users = new User[3];
```

初始内容是：

```text
[null, null, null]
```

需要单独创建对象：

```java
users[0] = new User();
```

## 十、方法参数和返回值也是类型约束

```java
/**
 * 根据源用户创建一个新用户。
 *
 * @param source 提供初始数据的用户对象
 * @return 新创建的 User 对象
 */
static User copyUser(User source) {
    User result = new User();
    result.name = source.name;
    result.age = source.age;
    return result;
}
```

其中三个 `User` 都是类型约束：

```text
User copyUser(User source)
↑             ↑
返回类型        参数类型

User result
↑
局部变量类型
```

## 十一、泛型是在进一步描述类型

```java
List<String> names = new ArrayList<>();
List<User> users = new ArrayList<>();
```

- `List<String>` 表示按照字符串元素使用的列表。
- `List<User>` 表示按照用户元素使用的列表。
- 泛型在复用代码的同时，保留编译期类型检查。

自定义泛型类：

```java
/**
 * 保存一个指定类型的值。
 *
 * @param <T> 被保存的数据类型
 */
class Box<T> {
    private T value;

    /**
     * 更新盒子中的值。
     *
     * @param value 新值
     */
    void setValue(T value) {
        this.value = value;
    }

    /**
     * 获取盒子中的值。
     *
     * @return 当前保存的值
     */
    T getValue() {
        return value;
    }
}
```

使用时确定 `T` 的具体类型：

```java
Box<String> stringBox = new Box<>();
stringBox.setValue("Java");
String text = stringBox.getValue();

Box<Integer> integerBox = new Box<>();
integerBox.setValue(100);
Integer number = integerBox.getValue();
```

泛型常见语法摘要：

| 语法 | 含义 |
| --- | --- |
| `<T>` | 声明一个类型参数 |
| `Box<T>` | 使用类型参数定义泛型类 |
| `<T> T method(...)` | 定义泛型方法 |
| `<T extends Number>` | `T` 必须是 `Number` 或其子类 |
| `<?>` | 某种未知类型 |
| `<? extends T>` | 适合读取 `T` |
| `<? super T>` | 适合写入 `T` |

PECS 记忆法：

```text
Producer Extends：数据提供者使用 extends
Consumer Super：数据接收者使用 super
```

## 十二、Java 与 TypeScript 类型系统对照

### 1. 基本声明

TypeScript：

```typescript
let age: number = 18;
let name: string = "张三";
let passed: boolean = true;
```

Java：

```java
int age = 18;
String name = "张三";
boolean passed = true;
```

主要区别：

- TypeScript 的 `number` 同时覆盖整数和浮点数。
- Java 根据范围和精度分成 `int`、`long`、`float`、`double` 等。
- Java 的 `String` 是类，`boolean` 是基本类型。

### 2. 结构类型与标称类型

TypeScript 主要使用结构类型：只要结构兼容，两个类型就可能互相赋值。

```typescript
class Cat {
    name = "";
}

class Dog {
    name = "";
}

const animal: Cat = new Dog();
```

Java 主要使用标称类型：即使成员相同，也必须明确存在 `extends` 或 `implements` 关系。

```java
class Cat {
    String name;
}

class Dog {
    String name;
}

// 编译错误：Cat 与 Dog 没有声明继承或实现关系。
// Cat animal = new Dog();
```

记忆方式：

```text
TypeScript：结构兼容时，长得像就可能算这个类型。
Java：必须通过 extends 或 implements 明确建立类型关系。
```

### 3. Java 的 `var` 不是 TypeScript 的 `any`

```java
var user = new User(); // 编译器将 user 推断为 User 类型。
```

推断完成后，类型仍然固定：

```java
// 编译错误：user 已经是 User 类型。
// user = "张三";
```

`var` 只是省略局部变量类型的书写，不会关闭类型检查。

## 十三、统一分析模板

以后看到下面的代码：

```java
List<User> users = new ArrayList<>();
```

依次问五个问题：

1. 左边的声明类型是什么？——`List<User>`。
2. 右边创建了什么对象？——`ArrayList<User>` 对象。
3. 为什么可以赋值？——`ArrayList` 实现了 `List`。
4. 变量能使用哪些成员？——主要由声明类型 `List<User>` 决定。
5. 重写方法执行哪个实现？——由运行时的 `ArrayList` 对象决定。

最终总结：

```text
类型：规定值能做什么、能赋给谁。
类：描述对象结构，并定义一个同名引用类型。
对象：运行时根据类创建的具体实例。
变量：带有类型的存储位置。
引用：引用类型变量与对象之间的联系。
泛型：为类型增加类型参数，在复用代码时保留类型安全。
```

最常见的面向对象写法是：

```java
父类或接口类型 变量 = new 具体实现类();
```

例如：

```java
Animal animal = new Dog();
Flyable flyable = new Bird();
List<String> names = new ArrayList<>();
Map<String, Integer> scores = new HashMap<>();
```

左边表达调用方需要的能力，右边决定运行时使用的具体实现。
