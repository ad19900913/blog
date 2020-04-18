# java基础

https://www.liaoxuefeng.com/wiki/1252599548343744

记录温习上述教程时一些有意思的点

## 一些名词

- JSR规范：Java Specification Request
- JCP组织：Java Community Process
- RI：Reference Implementation
- TCK：Technology Compatibility Kit

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