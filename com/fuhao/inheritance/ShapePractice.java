package com.fuhao.inheritance;

public class ShapePractice {
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
}

// 父类：图形
abstract class Shape {
    // TODO: private 字段 name（图形名称）
    private final String name;
    public String category;

    // TODO: 构造方法，传入 name
    public Shape(String name) {
        this.name = name;
    }

    // TODO: name 提供 getter，不提供 setter
    public String getName() {
        return this.name;
    }

    // TODO: 方法 area()，返回 double，父类默认返回 0
    public abstract double area();

    // TODO: 方法 describe()，打印：图形[name] 面积 = area()
    public void describe() {
        System.out.println("图形[" + this.name + "]" + " 面积 = " + this.area());
    }
}

// 子类：圆形
class Circle extends Shape implements Drawable, Scalable {
    // TODO: private 字段 radius（半径）
    private double radius;
    public String category;
    // TODO: 构造方法，传入 radius，name 固定为 "圆形"，radius 必须大于 0
    public Circle(double radius) {
        super("圆形");
        if(radius < 0) {
            System.out.println("radius 必须大于 0");
            return;
        }
        this.radius = radius;
    }
    // TODO: radius 提供 getter
    public double getRadius() {
        return this.radius;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // TODO: 重写 area()，返回 Math.PI * radius * radius
    @Override
    public double area() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    @Override
    public void draw() {
        System.out.println("  ◯  (半径 = " + getRadius() + ")");
    }

    @Override
    public void scale(double factor) {
        this.radius *= factor;
    }
}

// 子类：矩形
class Rectangle extends Shape implements Drawable, Scalable {
    // TODO: private 字段 width、height
    private double width;
    private double height;
    // TODO: 构造方法，传入 width、height，name 固定为 "矩形"，两者必须大于 0
    public Rectangle(double width, double height) {
        super("矩形");
        if(width < 0 || height < 0) {
            System.out.println("width、height必须大于0");
            return;
        }
        this.width = width;
        this.height = height;
    }

    // TODO: width、height 提供 getter
    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }
    // TODO: 重写 area()，返回 width * height
    @Override
    public double area() {
        return width * height;
    }
    // TODO: 重写 describe()，先调用父类 describe()，再补充打印：宽=width 高=height
    @Override
    public void describe() {
        super.describe();
        System.out.println("宽=" + this.width + " " + "高=" + this.height);
    }

    @Override
    public void draw() {
        System.out.println("绘制矩形，宽=" + this.getWidth() + "高=" + this.getHeight());
    }

    @Override
    public void scale(double factor) {
        this.width *= factor;
        this.height *= factor;
    }
}

interface Drawable {
    void draw();
}

interface Scalable {
    void scale(double factor);
}