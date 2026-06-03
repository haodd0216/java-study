// TODO 1: 在这上面添加 package 声明
// 提示: 这个文件在 com/fuhao/basic_practice/util/ 目录下
// 包名必须和目录结构一致
package com.fuhao.basic_practice.util;


/**
 * 数学工具类
 *
 * TODO 2: 检查类的命名是否符合 PascalCase？
 * TODO 3: 声明一个常量 PI = 3.14159（用 final，遵循 UPPER_SNAKE_CASE）
 * TODO 4: 写两个方法:
 *    - add(int a, int b) → 返回两数之和
 *    - circleArea(double radius) → 返回圆的面积（用常量 PI）
 *   注意方法名遵循 camelCase
 */
// TODO 5: 下面这些标识符哪些合法？把非法的注释掉或删除，合法的补全声明
// int 2days = 2;          // 不合法 不能以数字开头
// int $price = 100;       // 合法
// int my-age = 25;        // 不合法 不能用中划线，会被认为是减号做运算
// int class = 10;         // 不合法 使用了关键字
// int _count = 5;         // 合法
// int player1 = 7;        // 合法

public class MathHelper {        // 确认类名和文件名一致

    // TODO 3: 在这里声明常量 PI
    public static final double PI = 3.14159;
    // TODO 4: 在这里写 add 方法
    public static int add(int a, int b) {
        return a + b;
    }

    // TODO 4: 在这里写 circleArea 方法
    public static double circleArea(double radius) {
        return PI * radius * radius;
    }
}
