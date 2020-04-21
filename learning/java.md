# java基础

https://www.liaoxuefeng.com/wiki/1252599548343744

记录温习上述教程时一些有意思的点

## 一些名词

- JSR规范：Java Specification Request
- JCP组织：Java Community Process
- RI：Reference Implementation
- TCK：Technology Compatibility Kit

<!-- more -->

## var关键字

```java
//旧变量赋值语句
StringBuilder sb = new StringBuilder();
//新变量赋值语句
var sb = new StringBuilder();
```

## 多行字符串

从Java 13开始，字符串可以用"""..."""表示多行字符串

```java
String s = """
           SELECT * FROM
             users
           WHERE id > 100
           ORDER BY name DESC
           """;
System.out.println(s);
```

## switch表达式

从Java 12开始，switch语句升级为更简洁的表达式语法，使用类似模式匹配（Pattern Matching）的方法，保证只有一种路径会被执行，并且不需要break语句

```java
int x = new Random().nextInt();
switch (x) {
    case 1 -> System.out.println("1");
    case 2 -> {
        System.out.println("2");
        System.out.println("3");
    }
    default -> System.out.println("default");
}
```

switch表达式还可以用来给变量赋值：

```java
String fruit = "apple";
int opt = switch (fruit) {
    case "apple" -> 1;
    case "pear", "mango" -> 2;
    default -> 0;
}; // 注意赋值语句要以;结束
System.out.println("opt = " + opt);
```

大多数时候，在switch表达式内部，我们会返回简单的值。
但是，如果需要复杂的语句，我们也可以写很多语句，放到{...}里，然后，用yield返回一个值作为switch语句的返回值：

```java
String fruit = "orange";
int opt = switch (fruit) {
    case "apple" -> 1;
    case "pear", "mango" -> 2;
    default -> {
        int code = fruit.hashCode();
        yield code; // switch语句返回值
    }
};
System.out.println("opt = " + opt);
```

## 记录类

从Java 14开始，提供新的record关键字，可以非常方便地定义Data Class：

使用record定义的是不变类；

可以编写Compact Constructor对参数进行验证；

可以定义静态方法。

```java
public static void main(String[] args) {
    Point p = new Point(123, 456);
    System.out.println(p.x());
    System.out.println(p.y());
    System.out.println(p);
}

public record Point(int x, int y) {
    public Point {
        // 这是我们编写的Compact Constructor:
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException();
        }
    }

    public static Point of() {
        return new Point(0, 0);
    }

    public static Point of(int x, int y) {
        return new Point(x, y);
    }
}
```

## NullPointerException

从Java 14开始，如果产生了NullPointerException，JVM可以给出详细的信息告诉我们null对象到底是谁。

```java
public class Test {

    public static void main(String[] args) {
        Person p = new Person();
        System.out.println(p.address.city.toLowerCase());
    }

}

class Person {
    String[] name = new String[2];
    Address address = new Address();
}

class Address {
    String city;
    String street;
    String zipcode;
}
```

可以在NullPointerException的详细信息中看到类似... because "<local1>.address.city" is null，意思是city字段为null，这样我们就能快速定位问题所在。

这种增强的NullPointerException详细信息是Java 14新增的功能，但默认是关闭的，我们可以给JVM添加一个-XX:+ShowCodeDetailsInExceptionMessages参数启用它

## 注解

几种常用的元注解

### @Target

- 类或接口：ElementType.TYPE；
- 字段：ElementType.FIELD；
- 方法：ElementType.METHOD；
- 构造方法：ElementType.CONSTRUCTOR；
- 方法参数：ElementType.PARAMETER。

### @Retention

- 仅编译期：RetentionPolicy.SOURCE；
- 仅class文件：RetentionPolicy.CLASS；
- 运行期：RetentionPolicy.RUNTIME。