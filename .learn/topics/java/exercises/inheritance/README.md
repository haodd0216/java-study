# 练习：继承

## 练习前须知

1. 代码文件写在 `com/fuhao/inheritance/` 目录下
2. 文件需要声明 `package com.fuhao.inheritance;`
3. `.learn/topics/java/exercises/inheritance/README.md` 只保存练习说明

## 目标

通过实现一个图形继承体系，练习：

- 用 `extends` 表达父子关系；
- 用 `super(...)` 调用父类构造方法；
- 子类扩展自己的字段；
- 方法重写 `@Override`；
- 用 `super.方法()` 复用父类方法；
- 继承与封装配合（父类字段私有，子类通过 getter 访问）。

## 文件

`com/fuhao/inheritance/ShapePractice.java`

## 背景

你要设计一个图形继承体系：

```text
Shape（图形）
├── Circle（圆形）
└── Rectangle（矩形）
```

## 要求

### 1. 父类 Shape

字段：

- `private String name;`

构造方法：

```java
public Shape(String name)
```

方法：

- `name` 只提供 getter，不提供 setter；
- `public double area()`：父类默认返回 `0`；
- `public void describe()`：打印 `图形[name] 面积 = area()`。

例如名称是 `圆形`、面积是 `12.56` 时，打印：

```text
图形[圆形] 面积 = 12.56
```

### 2. 子类 Circle

字段：

- `private double radius;`

构造方法：

```java
public Circle(double radius)
```

要求：

- 名称固定为 `圆形`，通过 `super("圆形")` 设置；
- `radius` 必须大于 `0`，不合法时打印 `半径必须大于0` 并 return；
- `radius` 提供 getter；
- 重写 `area()`，返回 `Math.PI * radius * radius`。

### 3. 子类 Rectangle

字段：

- `private double width;`
- `private double height;`

构造方法：

```java
public Rectangle(double width, double height)
```

要求：

- 名称固定为 `矩形`，通过 `super("矩形")` 设置；
- `width` 和 `height` 都必须大于 `0`，不合法时打印 `宽和高必须大于0` 并 return；
- `width`、`height` 提供 getter；
- 重写 `area()`，返回 `width * height`；
- 重写 `describe()`，先调用父类 `super.describe()`，再补充打印 `宽=width 高=height`。

### 4. main 方法测试

1. 创建 `Circle`，半径 `2`；
2. 调用它的 `describe()`；
3. 创建 `Rectangle`，宽 `3`，高 `4`；
4. 调用它的 `describe()`。

## 预期重点

这次练习重点是：

> 子类如何通过 super 复用父类，同时通过重写扩展或改变父类行为。

完成后告诉我“写好了”，我会读取代码并运行检查。