# 练习：抽象类与接口

## 练习前须知

1. 代码文件写在 `com/fuhao/inheritance/` 目录下
2. 文件需要声明 `package com.fuhao.inheritance;`
3. 基于已有的 Shape / Circle / Rectangle 进行改造
4. 如果改造影响了 PolymorphismDeepPractice 的编译，需要同步调整

## 目标

将现有 Shape 体系改造为「抽象类 + 接口」的设计，练习：

- `abstract class` 和 `abstract` 方法的定义
- 验证抽象类不能 new
- `interface` 的定义和 `implements` 实现
- 一个类实现多个接口（多实现）
- 接口类型引用 → 多态调用

## 文件

`com/fuhao/inheritance/ShapePractice.java` — 改造现有的 Shape/Circle/Rectangle

## 要求

### 1. 将 Shape 改造为抽象类

- 把 `class Shape` 改为 `abstract class Shape`
- 把 `area()` 改为 `abstract` 方法（去掉方法体 `{ return 0; }`）
- 其他字段和方法保持不变

### 2. 创建 Drawable 接口

在 `ShapePractice.java` 中新增：

```java
interface Drawable {
    void draw();
}
```

### 3. 创建 Scalable 接口

```java
interface Scalable {
    void scale(double factor);
}
```

### 4. Circle 实现接口

```java
class Circle extends Shape implements Drawable, Scalable {
    // ... 原有代码保持不变

    @Override
    public void draw() {
        System.out.println("  ◯  (半径 = " + getRadius() + ")");
    }

    @Override
    public void scale(double factor) {
        // radius *= factor（由于 radius 是 private，需要通过构造方式或添加 setter 间接实现）
        // 提示：给 Circle 添加一个 setRadius 方法，或者把 radius 改为非 private
    }
}
```

### 5. Rectangle 实现接口

同样实现 `Drawable` 和 `Scalable`。

### 6. main 方法测试

在 `ShapePractice` 的 `main` 方法中：

```java
public static void main(String[] args) {
    // 1. 验证：不能 new 抽象类
    // Shape s = new Shape("测试");  // 取消注释会编译报错

    // 2. 正常创建对象
    Circle circle = new Circle(3.0);
    Rectangle rectangle = new Rectangle(4.0, 5.0);

    // 3. Shape 引用 → 多态调用 area() 和 describe()
    System.out.println("=== 用 Shape 引用 ===");
    Shape s1 = circle;
    Shape s2 = rectangle;
    s1.describe();
    s2.describe();

    // 4. Drawable 引用 → 多态调用 draw()
    System.out.println("\n=== 用 Drawable 引用 ===");
    Drawable d1 = circle;
    Drawable d2 = rectangle;
    d1.draw();
    d2.draw();

    // 5. Scalable 引用 → 多态调用 scale()
    System.out.println("\n=== 用 Scalable 引用 ===");
    Scalable sc1 = circle;
    Scalable sc2 = rectangle;
    sc1.scale(2.0);
    sc2.scale(0.5);
    System.out.println("缩放后：");
    s1.describe();  // 圆放大了2倍
    s2.describe();  // 矩形缩小了一半
}
```

## 预期重点

1. `abstract class` 不能 new —— 编译期就阻止了不完整的对象
2. `abstract` 方法没有 `{ ... }` —— 只有声明，没有实现
3. 一个类可以 `implements Drawable, Scalable` —— 实现多个接口
4. 同一个对象可以用不同引用类型访问 —— 每种引用只能看到对应类型的方法
5. 接口类型的引用也是多态 —— `Drawable d = circle; d.draw();` 实际调用 `Circle.draw()`

完成后告诉我「写好了」。
