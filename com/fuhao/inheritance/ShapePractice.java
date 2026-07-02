package com.fuhao.inheritance;

public class ShapePractice {
    public static void main(String[] args) {
        // 在这里创建 Circle 和 Rectangle 对象并测试
        Circle circle = new Circle(3.2);
        circle.describe();

        Rectangle rectangle = new Rectangle(4.5, 2);
        rectangle.describe();
    }
}

// 父类：图形
class Shape {
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
    public double area() {
        return 0;
    }

    // TODO: 方法 describe()，打印：图形[name] 面积 = area()
    public void describe() {
        System.out.println("图形[" + this.name + "]" + " 面积 = " + this.area());
    }
}

// 子类：圆形
class Circle extends Shape {
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

    // TODO: 重写 area()，返回 Math.PI * radius * radius
    @Override
    public double area() {
        return Math.PI * Math.pow(this.radius, 2);
    }
}

// 子类：矩形
class Rectangle extends Shape {
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
}
