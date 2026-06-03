// TODO 1: 在这上面添加 package 声明
// 提示: 这个文件在 com/fuhao/basic_practice/ 目录下
package com.fuhao.basic_practice;


// TODO 2: 添加 import 语句，引入 util 包下的 MathHelper 类
import com.fuhao.basic_practice.util.MathHelper;

/**
 * 程序入口
 *
 * TODO 3: 确认 public 类名和文件名 Main.java 一致
 * TODO 4: 写出完整正确的 main 方法签名
 *   提示: public static void main(String[] args)
 * TODO 5: 在 main 方法中:
 *    1) 调用 MathHelper.add(10, 20) 打印结果
 *    2) 调用 MathHelper.circleArea(5.0) 打印圆面积
 *    3) 用单行注释写一句话解释 System.out.println 的含义
 * TODO 6: 故意把一条语句的分号删掉，看编译器报什么错（观察完恢复）
 */
public class Main {

    // TODO 4: 在这里写 main 方法
    public static void main(String[] args) {
        MathHelper mathHelper = new MathHelper();
        System.out.println(mathHelper.add(10, 20));
        System.out.println(mathHelper.circleArea(5.0));
//      System.out.println就是在控制台输出语句，并且换行
        int i = 1;
        int a = ++i; //先赋值再自增
//        int a = ++i; 先自增再赋值
        System.out.println(a);
    }

}
