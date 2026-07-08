# Java 基础问题回顾笔记

> 适合复习本轮学习中的几个重点：枚举、package、反射、`getDeclaredMethod` 参数、`Class<?>`。

## 1. Java 枚举 enum

### 核心概念

`enum` 是 Java 中用于定义一组固定常量的类型。

常见例子：

```java
public enum PayChannel {
    ALIPAY,
    WECHAT,
    BANK_CARD
}
```

枚举适合表示数量固定、取值范围明确的内容，比如：

- 支付渠道：支付宝、微信、银行卡
- 订单状态：待支付、已支付、已发货、已完成
- 用户等级：普通用户、会员用户
- 操作类型：新增、删除、修改、查询

### 常用方法

```java
PayChannel channel = PayChannel.ALIPAY;

channel.name();       // 获取枚举常量名：ALIPAY
channel.ordinal();    // 获取枚举顺序：0，不建议存数据库
PayChannel.values();  // 获取所有枚举值
PayChannel.valueOf("ALIPAY"); // 根据枚举名获取枚举对象
```

### 开发建议

不要把 `ordinal()` 存入数据库，因为枚举顺序一旦调整，历史数据含义就会错乱。

更推荐给枚举增加稳定业务编码：

```java
public enum PayChannel {
    ALIPAY("alipay", "支付宝"),
    WECHAT("wechat", "微信支付");

    private final String code;
    private final String displayName;

    PayChannel(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
```

本项目中已写过完整演示：

```text
src/EnumDemo.java
```

## 2. Java package

### 核心概念

`package` 可以理解为 Java 类的“命名空间”和“目录分组”。

它主要解决两个问题：

- 分类管理代码
- 避免类名冲突

例如：

```java
package com.demo.user;

public class UserService {
}
```

通常对应目录结构：

```text
src/
  com/
    demo/
      user/
        UserService.java
```

### 使用其他包的类

不同包之间使用类，需要 `import`：

```java
package com.demo.order;

import com.demo.user.UserService;

public class OrderService {
    private UserService userService;
}
```

同一个包下的类不需要导入。

### 常见分包方式

按功能模块分包：

```text
com.demo.user
com.demo.order
com.demo.product
```

按分层结构分包：

```text
com.demo.controller
com.demo.service
com.demo.mapper
com.demo.entity
com.demo.util
```

模块和分层结合：

```text
com.demo.user.controller
com.demo.user.service
com.demo.user.entity

com.demo.order.controller
com.demo.order.service
com.demo.order.entity
```

### 访问权限补充

如果类、方法、字段不写 `public`、`protected`、`private`，默认是包内可见。

```java
class UserValidator {
}
```

这个类只能被同一个 `package` 下的代码访问。

## 3. Java 反射 Reflection

### 核心概念

反射是 Java 在运行时获取和操作类信息的能力。

普通调用：

```java
User user = new User();
user.sayHello();
```

反射调用：

```java
Class<?> clazz = User.class;
Object user = clazz.getDeclaredConstructor().newInstance();
Method method = clazz.getDeclaredMethod("sayHello");
method.invoke(user);
```

反射的关键点是：程序可以在运行时才决定操作哪个类、哪个字段、哪个方法。

### 获取 Class 对象的方式

```java
Class<User> c1 = User.class;

User user = new User();
Class<?> c2 = user.getClass();

Class<?> c3 = Class.forName("com.demo.User");
```

可以把 `Class` 理解成“类的说明书”。

### 反射常见操作

获取构造方法并创建对象：

```java
Constructor<?> constructor = clazz.getDeclaredConstructor();
Object obj = constructor.newInstance();
```

获取字段并赋值：

```java
Field field = clazz.getDeclaredField("name");
field.setAccessible(true);
field.set(obj, "张三");
```

获取方法并调用：

```java
Method method = clazz.getDeclaredMethod("sayHello", String.class);
method.invoke(obj, "张三");
```

读取注解：

```java
MyAnnotation annotation = clazz.getAnnotation(MyAnnotation.class);
```

### 常见使用场景

- Spring 创建 Bean、依赖注入、读取注解
- MyBatis、Hibernate 做对象和数据库字段映射
- Jackson、Gson 做 JSON 序列化和反序列化
- JUnit 查找并执行 `@Test` 方法
- 插件化、通用工具、框架底层能力

### 优缺点

优点：

- 灵活，运行时可以动态操作类
- 适合框架、工具、通用组件
- 可以配合注解实现自动化配置

缺点：

- 性能比直接调用差一些
- 可能破坏封装，例如访问 `private` 字段
- 编译期不容易发现错误，很多问题会变成运行时报错
- 代码可读性比普通调用差

普通业务代码不要滥用反射，框架和工具类中更常见。

## 4. 为什么 `getDeclaredMethod("sayHello", String.class)` 有两个参数

### 方法含义

```java
clazz.getDeclaredMethod("sayHello", String.class)
```

表示：

```text
在 clazz 代表的类中，查找方法名为 sayHello，并且参数类型为 String 的方法。
```

方法签名是：

```java
getDeclaredMethod(String name, Class<?>... parameterTypes)
```

第一个参数是方法名。

第二个及后面的参数是方法的参数类型。

### 为什么需要参数类型

因为 Java 支持方法重载，同一个类里可能有多个同名方法：

```java
public void sayHello() {
}

public void sayHello(String name) {
}

public void sayHello(int age) {
}
```

反射查找时必须区分具体是哪一个：

```java
clazz.getDeclaredMethod("sayHello");               // 查找 sayHello()
clazz.getDeclaredMethod("sayHello", String.class); // 查找 sayHello(String name)
clazz.getDeclaredMethod("sayHello", int.class);    // 查找 sayHello(int age)
```

### 查找方法和调用方法的区别

查找方法时传参数类型：

```java
Method method = clazz.getDeclaredMethod("sayHello", String.class);
```

调用方法时传实际参数：

```java
method.invoke(obj, "张三");
```

记忆方式：

```text
getDeclaredMethod("方法名", 参数类型.class)
invoke(对象, 实际参数)
```

## 5. `Class<?>` 是什么意思

### 核心概念

```java
Class<?> clazz
```

表示：`clazz` 是一个 `Class` 类型对象，但它代表的具体类未知。

`?` 是泛型通配符，意思是“未知类型”。

### 对比写法

明确代表 `User` 类：

```java
Class<User> clazz = User.class;
```

代表某个未知类型的类：

```java
Class<?> clazz = User.class;
```

反射里经常这样写：

```java
Class<?> clazz = Class.forName("com.demo.User");
```

因为通过字符串加载类时，程序运行前不一定知道它到底是哪一个类。

### 为什么不直接写 `Class`

可以写：

```java
Class clazz = User.class;
```

但这是原始类型，会丢失泛型信息，编译器通常会给警告。

更推荐：

```java
Class<?> clazz = User.class;
```

### 记忆方式

```text
Class<User> 表示明确是 User 这个类的 Class 对象
Class<?>    表示某个未知类型的 Class 对象
?           表示未知类型
```

## 6. 总结

本轮问题可以串起来这样理解：

```text
enum     用来表达固定取值，减少魔法字符串和 if/else。
package  用来组织类、避免重名、控制包内访问范围。
反射     用来在运行时查看和操作类、方法、字段、注解。
Class<?> 是反射中常见写法，表示未知具体类型的 Class 对象。
```

开发中一般优先写清晰的普通代码；只有在框架、通用工具、注解扫描、对象映射等场景下，才更常使用反射。
