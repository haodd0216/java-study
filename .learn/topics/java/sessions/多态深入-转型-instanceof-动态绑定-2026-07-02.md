# 多态深入：转型、instanceof 与动态绑定

日期：2026-07-02
主题：Java / 面向对象编程 / 多态 / 深入

## 1. 回顾：你已经知道了什么

你刚掌握了多态的核心：

```java
Shape s = new Circle(3.0);  // 父类引用 → 子类对象
s.area();                    // 运行时调用 Circle.area()
```

两个关键事实：
- **编译时**：编译器只认 `Shape`，只允许调用 `Shape` 里声明的方法
- **运行时**：JVM 看实际对象是 `Circle`，调用 `Circle` 重写的方法

现在深入三个紧密相关的子主题。

---

## 2. 向上转型（Upcasting）

### 2.1 什么是向上转型

> 把子类对象赋值给父类类型的变量。

```java
Circle c = new Circle(3.0);
Shape s = c;  // 向上转型：Circle → Shape，隐式、自动
```

"向上"的意思是：从子类 → 父类，在继承树中「向上」走。

### 2.2 为什么叫「向上」

```
       Object
         ↑
       Shape        ← 父类（更抽象）
         ↑
   ┌─────┴─────┐
 Circle    Rectangle  ← 子类（更具体）
```

从 `Circle` 到 `Shape` 是往树的上方走 → 向上转型。

### 2.3 向上转型的特点

**（1）隐式转换，不需要强制转型**

```java
Shape s = new Circle(3.0);  // 自动，不需要 (Shape)
```

因为「圆是图形」天然成立，编译器不需要你手动确认。

**（2）绝对安全**

圆一定是图形，向上转型永远不会出错。

**（3）功能收窄**

转型后只能访问父类中定义的方法：

```java
Shape s = new Circle(3.0);
s.area();       // ✓ Shape 有 area()
s.getName();    // ✓ Shape 有 getName()
s.getRadius();  // ✗ 编译错误！Shape 没有 getRadius()
```

### 2.4 向上转型最常见的场景

**场景一：数组/集合统一处理**

```java
Shape[] shapes = {
    new Circle(3.0),    // Circle → Shape（向上转型）
    new Rectangle(4, 5) // Rectangle → Shape（向上转型）
};
```

**场景二：方法参数**

```java
// 这个方法接受 Shape，但你可以传入任何子类
public static void printArea(Shape s) {
    System.out.println("面积 = " + s.area());
}

// 调用时，子类自动向上转型
printArea(new Circle(3.0));     // Circle → Shape
printArea(new Rectangle(4, 5)); // Rectangle → Shape
```

这正是多态的核心价值：**写一次方法，接受所有子类**。

---

## 3. 向下转型（Downcasting）

### 3.1 什么是向下转型

> 把父类类型的引用强制转回子类类型。

```java
Shape s = new Circle(3.0);   // 向上转型
Circle c = (Circle) s;       // 向下转型：Shape → Circle，需要强制转换
c.getRadius();               // ✓ 现在可以调用 Circle 特有方法了
```

"向下"的意思是：从父类 → 子类，在继承树中「向下」走。

### 3.2 为什么需要强制转型

```java
Shape s = new Circle(3.0);
Circle c = s;  // ✗ 编译错误！
```

编译器不知道 `s` 实际指向的是 `Circle` 还是 `Rectangle`。

你必须显式告诉编译器：「我知道它是个 Circle，相信我」：

```java
Circle c = (Circle) s;  // 强制转型，告诉编译器你的意图
```

### 3.3 向下转型的风险：ClassCastException

如果实际类型不匹配，运行时会抛异常：

```java
Shape s = new Rectangle(4, 5);  // 实际是 Rectangle
Circle c = (Circle) s;          // 编译通过，运行时报错！
// java.lang.ClassCastException: Rectangle cannot be cast to Circle
```

**编译器和运行时的差异在这里体现得很清楚：**

| 阶段 | 做什么 | 结果 |
|------|--------|------|
| 编译时 | 检查语法上转型是否可能（`Shape` 和 `Circle` 有继承关系） | 通过 |
| 运行时 | 检查 `s` 的真实类型是不是 `Circle` | 抛异常 |

### 3.4 转型前防御：instanceof

这就是下一节的内容——转型前先用 `instanceof` 检查。

---

## 4. instanceof 运算符

### 4.1 语法

```java
对象 instanceof 类型
```

返回 `true` / `false`，判断对象的**实际运行时类型**是否是该类型（或其子类型）。

### 4.2 基本用法：转型的安全守卫

```java
Shape s = new Circle(3.0);

if (s instanceof Circle) {
    Circle c = (Circle) s;  // 安全，已经确认是 Circle
    System.out.println("半径：" + c.getRadius());
}
```

这是向下转型的标准写法：**先判断，再转型**。

### 4.3 处理多种子类

```java
Shape[] shapes = {
    new Circle(3.0),
    new Rectangle(4, 5),
    new Circle(1.5)
};

for (Shape s : shapes) {
    if (s instanceof Circle) {
        Circle c = (Circle) s;
        System.out.println("圆形，半径 = " + c.getRadius());
    } else if (s instanceof Rectangle) {
        Rectangle r = (Rectangle) s;
        System.out.println("矩形，宽 = " + r.getWidth() + "，高 = " + r.getHeight());
    }
}
```

### 4.4 instanceof 和多态的关系

你可能会问：「不是刚说多态可以避免判断类型吗？怎么又开始 instanceof 了？」

关键理解：

> **多态是首选**，instanceof **是备选**。

| 场景 | 用什么 |
|------|--------|
| 所有子类都有的共同行为（如 area()） | 多态，父类引用直接调用 |
| 只有特定子类才有的行为（如 getRadius()） | instanceof + 向下转型 |

如果代码里大量 instanceof，说明设计上可能需要调整——尽量把行为放到共同父类或接口里。

### 4.5 Java 16+ 的便捷写法：模式匹配

```java
// 传统写法（Java 15 及以前）
if (s instanceof Circle) {
    Circle c = (Circle) s;
    System.out.println(c.getRadius());
}

// Java 16+ 模式匹配
if (s instanceof Circle c) {
    System.out.println(c.getRadius());  // 直接用 c，不需要再转型
}
```

一步完成「判断 + 转型 + 声明变量」。

---

## 5. 动态绑定的细节

### 5.1 编译时 vs 运行时

你已经知道核心概念，现在深入细节：

```java
Shape s = new Circle(3.0);
s.describe();
```

这条 `s.describe()` 调用，在编译时和运行时发生了不同的事：

| 阶段 | 谁在工作 | 做什么 |
|------|----------|--------|
| 编译时 | javac | 检查 `Shape` 有没有 `describe()` 方法 → 有，编译通过 |
| 运行时 | JVM | 查看 `s` 的实际类型是 `Circle`，从 `Circle` 开始往上找 `describe()` 的实现 |

**关键规则：**

> 编译器只看声明类型，JVM 看实际类型。

这就解释了为什么 `s.getRadius()` 编译不过——编译器在 `Shape` 里找不到 `getRadius()`，不管运行时 `s` 实际是不是 `Circle`。

### 5.2 方法查找顺序

当你调用 `circle.describe()` 时，JVM 怎么找到要执行的方法？

```
1. 看 Circle 有没有重写 describe()？
   → 你代码里 Circle 没有重写 describe() → 没有
2. 看父类 Shape 有没有 describe()？
   → 有 → 执行 Shape.describe()

在 Shape.describe() 内部：
  调用 this.area() →
  1. this 的实际类型是 Circle → 看 Circle 有没有 area()？
     → 有（重写了）→ 执行 Circle.area()
  2. 如果 Circle 没有重写，继续往上找 → Shape.area()
```

**一句话：从实际类型开始，沿着继承链往上找，找到的第一个就是执行的那个。**

### 5.3 哪些东西不参与动态绑定

这个知识点容易出错。

**（1）static 方法不参与动态绑定**

```java
class Shape {
    public static void info() {
        System.out.println("我是图形");
    }
}

class Circle extends Shape {
    public static void info() {  // 这是隐藏（hide），不是重写（override）
        System.out.println("我是圆形");
    }
}

Shape s = new Circle();
s.info();  // 输出「我是图形」——看的是声明类型 Shape，不是实际类型！
```

`static` 方法属于类，不属于对象，没有多态。

**（2）private 方法不参与动态绑定**

```java
class Shape {
    private void secret() {
        System.out.println("Shape 的秘密");
    }
}

class Circle extends Shape {
    private void secret() {  // 这不是重写！是 Circle 自己的独立方法
        System.out.println("Circle 的秘密");
    }
}
```

`private` 方法对子类不可见，子类的同名方法是一个**全新的方法**，跟父类的没有关系。

**（3）字段不参与多态**

```java
class Shape {
    public String type = "图形";
}

class Circle extends Shape {
    public String type = "圆形";  // 这是隐藏父类的字段
}

Shape s = new Circle();
System.out.println(s.type);  // 输出「图形」——访问的是 Shape.type！
```

字段访问看**声明类型**，不看实际类型。方法才有多态。

> **记住：只有实例方法（非 private、非 static）才参与动态绑定。**

### 5.4 动态绑定的底层原理（简要）

你不需要记住实现细节，但了解原理有助于建立直觉。

每个类在 JVM 里有一个**方法表（vtable）**，记录了每个方法实际执行哪个版本：

```
Shape 的方法表：           Circle 的方法表：
  area()    → Shape.area    area()    → Circle.area    ← 指向自己的重写版本
  describe()→ Shape.describe describe()→ Shape.describe ← 没重写，指向父类的
  getName() → Shape.getName  getName() → Shape.getName
                             getRadius()→ Circle.getRadius  ← 新增的
```

当执行 `s.area()` 时（`s` 实际是 `Circle`），JVM：
1. 找到 `s` 实际类型 → `Circle`
2. 查 `Circle` 的方法表 → `area()` 指向 `Circle.area`
3. 执行 `Circle.area()`

这比在代码里写一堆 if-else 判断类型快得多，是 JVM 级别的高效实现。

---

## 6. 完整例子：把转型、instanceof、动态绑定串起来

```java
class Shape {
    private String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double area() {
        return 0;
    }

    public void describe() {
        System.out.println("图形[" + name + "] 面积 = " + area());
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        super("圆形");
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    public double getRadius() {
        return radius;
    }
}

class Rectangle extends Shape {
    private double width, height;

    public Rectangle(double width, double height) {
        super("矩形");
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}

public class Main {
    public static void main(String[] args) {
        // 1. 向上转型：子类自动转为父类引用
        Shape[] shapes = {
            new Circle(3.0),
            new Rectangle(4, 5),
            new Circle(1.5)
        };

        // 2. 多态调用 + 动态绑定
        System.out.println("=== 所有图形 ===");
        double total = 0;
        for (Shape s : shapes) {
            s.describe();  // 动态绑定，自动调正确的 describe()
            total += s.area();  // 动态绑定，自动调正确的 area()
            System.out.println("---");
        }
        System.out.println("总面积 = " + total);

        // 3. instanceof + 向下转型：访问子类特有方法
        System.out.println("\n=== 子类特有信息 ===");
        for (Shape s : shapes) {
            if (s instanceof Circle) {
                Circle c = (Circle) s;  // 向下转型
                System.out.println(s.getName() + " 的半径 = " + c.getRadius());
            } else if (s instanceof Rectangle) {
                Rectangle r = (Rectangle) s;  // 向下转型
                System.out.println(s.getName() + " 的宽 = " + r.getWidth()
                    + "，高 = " + r.getHeight());
            }
        }

        // 4. 验证动态绑定：字段没有多态
        System.out.println("\n=== 字段没有多态 ===");
        Circle circle = new Circle(5.0);
        Shape shapeRef = circle;  // 向上转型
        System.out.println("circle.area()  = " + circle.area());    // Circle.area()
        System.out.println("shapeRef.area() = " + shapeRef.area()); // 也是 Circle.area()（动态绑定）
        // shapeRef.getRadius();   // 编译错误！Shape 里没有 getRadius()
    }
}
```

---

## 7. 苏格拉底式检查

请思考以下问题：

### 向上转型与向下转型

1. 为什么 `Shape s = new Circle(3.0);` 不需要强制转型，而 `Circle c = (Circle) s;` 需要？
2. 向上转型后，能调用的方法变多了还是变少了？为什么？
3. 如果 `Shape s = new Rectangle(4, 5);`，然后执行 `Circle c = (Circle) s;`，会发生什么？

### instanceof

4. 只用多态不用 instanceof 能写出所有程序吗？什么情况下你**不得不**用 instanceof + 向下转型？
5. 如果代码里到处都是 `if (s instanceof Circle) { ... } else if (s instanceof Rectangle) { ... }`，这说明什么设计问题？

### 动态绑定

6. 为什么 `static` 方法没有多态？（提示：static 修饰的东西属于谁？）
7. 父类方法里调用 `this.area()`，当子类对象执行这个方法时，`this.area()` 调用的是谁的 `area()`？为什么？
8. 子类里定义一个和父类 `private` 方法同名的方法，这是重写吗？运行时会有多态吗？

---

## 8. 一句话总结

- **向上转型**：隐式、安全，但功能收窄——只能调父类声明的方法
- **向下转型**：显式、有风险，必须先用 instanceof 确认类型
- **instanceof**：向下转型的安全守卫，但应该少用——多用多态代替
- **动态绑定**：只有实例方法参与，static/private/字段都不参与

> 多态让你写代码时面向父类；转型和 instanceof 让你在必要时突破父类限制，回到子类的具体能力。
