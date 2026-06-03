package com.fuhao.variables_practice;

/**
 * 变量与数据类型 — 动手练习
 *
 * 依次完成每个 TODO 练习。
 * IDE 中右键 → Run 'VariablePractice.main()' 即可运行验证。
 */
public class VariablePractice {

    public static void main(String[] args) {
        System.out.println("========== 练习 1：声明 8 种基本类型 ==========");
        practice1_PrimitiveTypes();

        System.out.println("\n========== 练习 2：类型转换 ==========");
        practice2_TypeCasting();

        System.out.println("\n========== 练习 3：整数除法陷阱 ==========");
        practice3_IntegerDivision();

        System.out.println("\n========== 练习 4：常量 ==========");
        practice4_Constants();

        System.out.println("\n========== 练习 5：引用类型 ==========");
        practice5_ReferenceTypes();

        System.out.println("\n========== 练习 6：综合挑战 ==========");
        practice6_Challenge();
    }

    // ==================== 练习 1 ====================
    // TODO: 声明下面 8 种类型的变量，分别赋值并打印
    // byte → 一个年龄（1-127 之间）
    // short → 一个年份（比如 2026）
    // int → 你的学号
    // long → 中国人口（约 14 亿，注意加 L）
    // float → 圆周率 3.14（注意加 f）
    // double → 你的 GPA
    // char → 你的成绩等级（'A' / 'B' / 'C'）
    // boolean → 今天是否写了代码
    public static void practice1_PrimitiveTypes() {
        // TODO: 在这里写代码
        // 提示：System.out.println("年龄: " + age);
        byte age = 29;
        short year = 2026;
        int studentId = 1001;
        long chinesePopulation = 1_400_000_000L;
        float pi = 3.14f;
        double gpa = 94.5;
        char grade = 'A';
        boolean codeToday = true;
        System.out.println("年龄：" + age);
        System.out.println("今年是：" + year);
        System.out.println("学号：" + studentId);
        System.out.println("中国人口：" + chinesePopulation);
        System.out.println("Π：" + pi);
        System.out.println("平均成绩：" + gpa);
        System.out.println("成绩等级：" + grade);
        System.out.println("今天写代码了吗：" + codeToday);
    }

    // ==================== 练习 2 ====================
    // TODO: 完成以下类型转换练习
    // 1) int → double 自动转换，打印结果
    // 2) double → int 强制转换，观察小数部分是否被截断
    // 3) char → int，打印 'A' 的 ASCII/Unicode 值
    // 4) 试试 long → float（会丢失精度但能编译），打印看看
    public static void practice2_TypeCasting() {
        // TODO: 在这里写代码
        int i = 5;
        double d = i;
        System.out.println(i + " " + d );
        double d2 = 3.99;
        int i2 = (int)d2;
        System.out.println(d2 + " " + i2);
        char a = 'A';
        int i3 = (int)a;
        System.out.println(a + " " + i3);
        long l = 13548721235345234L;
        float f = (float)l;
        System.out.println(l + " " + f);
    }

    // ==================== 练习 3 ====================
    // 常见陷阱：整数相除
    // 下面代码输出什么？修复它让它输出 2.5
    public static void practice3_IntegerDivision() {
        int a = 5;
        int b = 2;
        System.out.println("修复前: " + (a / b)); // 这个输出 2

        // TODO: 修改这行代码，让输出变成 2.5（提示：把其中一个转成 double）
        System.out.println("修复后: " + /* 在这里改 */ (a / (double)b));
    }

    // ==================== 练习 4 ====================
    // TODO: 声明一个常量 MAX_SCORE = 100
    // 再声明一个变量 yourScore = 87
    // 打印 "得分: 87 / 100"
    // 试试给 MAX_SCORE 重新赋值，看编译器报什么错（观察完注释掉即可）
    public static void practice4_Constants() {
        // TODO: 在这里写代码
        final int MAX_SCORE = 100;
        int yourScore = 87;
        System.out.println("得分：" + yourScore + " / " + MAX_SCORE);
//        MAX_SCORE = 99;
    }

    // ==================== 练习 5 ====================
    // TODO:
    // 1) 创建一个 String 变量存储你的名字
    // 2) 创建一个 int[] 数组存储三门课的成绩
    // 3) 计算平均分并打印
    public static void practice5_ReferenceTypes() {
        // TODO: 在这里写代码
        String name = "foo";
        int[] scores = {99, 95, 89};
        int total = 0;
        for (int score:scores) {
            total += score;
        }
        System.out.println(name + "的平均成绩是：" + (double)total / scores.length );
    }

    // ==================== 练习 6：综合挑战 ====================
    // 写一个温度转换器：摄氏度 → 华氏度
    // 公式：F = C × 9/5 + 32
    // 要求：
    //  - 摄氏度用 double
    //  - 9/5 要注意整数除法问题！
    //  - 最终华氏度用 int（四舍五入还是截断？自己决定并输出）
    public static void practice6_Challenge() {
        double celsius = 36.5; // 体温

        // TODO: 在这里完成转换
         double fahrenheit = celsius * ((double) 9 / 5) + 32;
         System.out.println((int)fahrenheit);
    }
}
