package com.fuhao.inheritance;

public class PolymorphismDeepPractice {
    public static void main(String[] args) {
        // TODO 1: 创建 Shape[] 数组（向上转型）
        // 放入 2 个 Circle 和 2 个 Rectangle
        Shape[] shapes = { new Circle(2), new Circle(3), new Rectangle(4, 5), new Rectangle(6, 7) };

        // TODO 2: 多态遍历
        // 遍历数组，调用 describe()、累加 area()、打印分隔线
        // 最后打印总面积
        double totalArea = 0;
        for(Shape shape : shapes) {
            shape.describe();
            System.out.println("-----------");
            totalArea += shape.area();
        }
        System.out.println("总面积=" + totalArea);
        // TODO 3: instanceof + 向下转型
        // 再次遍历，判断每个元素的实际类型
        // 打印子类特有信息（Circle的半径 / Rectangle的宽和高）
        for(Shape shape : shapes) {
            if(shape instanceof Circle) {
                Circle circle = (Circle) shape;
                System.out.println("半径是" + circle.getRadius());
            }
            if(shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                System.out.println("宽是" + rectangle.getWidth() + " 高是" + rectangle.getHeight());
            }
        }

        // TODO 4（选做）: 验证「字段没有多态」
        // 在 Shape 和 Circle 中分别添加 public String category 字段
        // 观察用父类引用和子类引用访问 category 的差异
        Shape shape = new Circle(2);
        shape.category = "图形";
        System.out.println(shape.category);
        Circle circle = (Circle) shape;
        circle.category = "圆";
        System.out.println(circle.category);
        // TODO 5（选做）: 验证「不安全向下转型」的后果
        // 把 Rectangle 对象赋给 Shape 引用，强制转型成 Circle
        // 用 try-catch 捕获 ClassCastException
        try {
            Rectangle rectangle = (Rectangle) shape;
        } catch (ClassCastException e) {
            System.out.println(e);
        }
    }
}
