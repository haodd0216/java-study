/**
 * 运算符与表达式练习 — OperatorPractice
 *
 * 完成 8 个 TODO，覆盖六大运算符类别 + 位运算。
 * 每个 TODO 要求：先在心里推测输出，再写代码验证。
 */

public class OperatorPractice {
    public static void main(String[] args) {

        // ============================================================
        // TODO 1: prefixPostfix — ++i vs i++
        //
        // 请先推测下面代码的输出：
        //   int x = 3;
        //   int y = x++ + ++x;
        //   此时 x = 5  y = 8
        //
        // 然后用代码打印验证，写在下面：
        // ============================================================
        System.out.println("--- TODO 1: prefixPostfix ---");
        // === 在这里写你的验证代码 ===
        int x = 3;
        int y = x++ + ++x;
        System.out.println(x);
        System.out.println(y);

        // ============================================================
        // TODO 2: integerDivision — 整数除法截断
        //
        // 请先推测下面代码的输出：
        //   int a = 10 / 3;    → 3
        //   int b = 10 % 3;    → 1
        //   double c = 10 / 3; → 3.0 (注意：右边是整数运算)
        //   double d = 10.0 / 3; → 3.3333333333333335
        //
        // 然后用代码打印验证：
        // ============================================================
        System.out.println("\n--- TODO 2: integerDivision ---");
        // === 在这里写你的验证代码 ===
        int a = 10 / 3;
        int b = 10 % 3;
        double c = 10 / 3;
        double d = 10.0 / 3;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);

        // ============================================================
        // TODO 3: shortCircuit — 短路求值
        //
        // 请先推测下面代码的输出（特别注意 a 的值是否变化）：
        //   int a = 10, b = 20;
        //   boolean r1 = (a > b) && (a++ > 0);
        //   此时 a = 10  (关键：a++ 执行了吗？) a++ 没有执行 此时 a < b 不成立 && 后面的不会执行
        //
        //   int c = 10, d = 20;
        //   boolean r2 = (c < d) || (c++ > 0);
        //   此时 c = 10  (关键：c++ 执行了吗？) c++ 没有执行 此时 c < d 成立 || 后面的不会执行
        //
        // 然后用代码打印 r1, r2, a, c 的值验证：
        // ============================================================
        System.out.println("\n--- TODO 3: shortCircuit ---");
        // === 在这里写你的验证代码 ===
        int a3 = 10, b3 = 20;
        boolean r1 = (a3 > b3) && (a3++ > 0);

        int c3 = 10, d3 = 20;
        boolean r2 = (c3 < d3) || (c3++ > 0);
        System.out.println(a3);
        System.out.println(b3);
        System.out.println(r1);
        System.out.println(c3);
        System.out.println(d3);
        System.out.println(r2);

        // ============================================================
        // TODO 4: ternary — 三元运算符
        //
        // 要求：写一行代码，用三元运算符根据 score 输出等级：
        //   score >= 90 → "优秀"
        //   score >= 80 → "良好"
        //   score >= 60 → "及格"
        //   否则        → "不及格"
        //
        // 提示：嵌套三元 (score >= 90) ? "优秀" : (score >= 80) ? "良好" : ...
        // 创建 int score = 85 来测试：
        // ============================================================
        System.out.println("\n--- TODO 4: ternary ---");
        // === 在这里写你的三元表达式 ===
        int score = 85;

        String grade = score >= 90 ? "优秀" : score >= 80 ? "良好" : score >= 60 ? "及格" : "不及格"; // ← 用三元替换


        System.out.println("score=" + score + " → " + grade);
        // 预期: score=85 → 良好


        // ============================================================
        // TODO 5: compoundAssignment — 复合赋值 += 的隐含类型转换
        //
        // 下面两段代码，哪段能编译通过？为什么？
        //
        // 代码A:
        //   byte b = 10;
        //   b = b + 5;       // ← 编译错误！
        //
        // 代码B:
        //   byte b = 10;
        //   b += 5;          // ← 编译通过！
        //
        // 提示：b + 5 结果是 int，直接赋给 byte 会报错
        //      但 b += 5 内部自动做了 (byte)(b + 5)
        //
        // 要求：用代码验证代码B可以编译运行，并打印 b 的值
        // ============================================================
        System.out.println("\n--- TODO 5: compoundAssignment ---");
        // === 在这里写验证代码 ===
        byte b5 = 10;
        b5 += 5;

        System.out.println(b5);
        // ============================================================
        // TODO 6: typePromotion — 表达式中的类型提升
        //
        // 请先推测下面代码的输出：
        //   byte b1 = 10, b2 = 20;
        //   // byte b3 = b1 + b2;   ← 为什么报错？ b1 + b2 实际值是int， int不能变量提升为byte
        //   int b3 = b1 + b2;       ← 为什么可以？ b1 + b2 实际值是int
        //
        //   int i = 5;
        //   double d = 2.5;
        //   double result = i + d;  → ? 7.5
        //
        // 要求：写出验证代码并打印结果
        // ============================================================
        System.out.println("\n--- TODO 6: typePromotion ---");
        // === 在这里写验证代码 ===
        byte b6_1 = 10, b6_2 = 20;
        int b6_3 = b6_1 + b6_2;
        int b6 = b6_1 + b6_2;

        int i6 = 5;
        double d6 = 2.5;
        double result = i6 + d6;
        System.out.println(result);
        // ============================================================
        // TODO 7: stringConcat — 字符串拼接
        //
        // 请先推测输出：
        //   System.out.println(1 + 2 + "3");      → ? "33"
        //   System.out.println("1" + 2 + 3);      → ? "123"
        //   System.out.println("1" + (2 + 3));    → ? "15"
        //
        // 关键：从左到右逐个运算，碰到字符串就开始拼接
        // ============================================================
        System.out.println("\n--- TODO 7: stringConcat ---");
        // === 在这里写验证代码 ===
        System.out.println(1 + 2 + "3");
        System.out.println("1" + 2 + 3);
        System.out.println("1" + (2 + 3));

        // ============================================================
        // TODO 8: bitwise — 位运算（子主题）
        //
        // 请先手算（然后代码验证）：
        //   int a = 5;      // 二进制: 0101
        //   int b = 3;      // 二进制: 0011
        //
        //   a & b  → 0001  (按位与：两位都是 1 才为 1)
        //   a | b  → 0111  (按位或：有一位是 1 就为 1)
        //   a ^ b  → 0110  (按位异或：不同为 1，相同为 0)
        //   ~a     → 1010  (按位取反)
        //   a << 1 → 1010  (左移一位，相当于 ×2)
        //   a >> 1 → 0010  (右移一位，相当于 ÷2)
        //
        // 提示：手算公式
        //   & = AND, | = OR, ^ = XOR
        //   0101 & 0011 = 0001 = 1
        //   0101 | 0011 = 0111 = 7
        //   0101 ^ 0011 = 0110 = 6
        //   ~5 = -(5+1) = -6
        //   5 << 1 = 10
        //   5 >> 1 = 2
        // ============================================================
        System.out.println("\n--- TODO 8: bitwise ---");
        // === 在这里写验证代码 ===
        int a8 = 5, b8 = 3;
        System.out.println("a & b = " + (a8 & b8));    // 1
        System.out.println("a | b = " + (a8 | b8));    // 7
        System.out.println("a ^ b = " + (a8 ^ b8));    // 6
        System.out.println("~a = " + (~a8));          // -6
        System.out.println("a << 1 = " + (a8 << 1));  // 10
        System.out.println("a >> 1 = " + (a8 >> 1));  // 2

    }
}
