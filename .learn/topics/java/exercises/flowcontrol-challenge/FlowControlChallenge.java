import java.util.Arrays;

/**
 * 流程控制 Challenge 练习 — FlowControlChallenge
 *
 * 5 个 TODO，覆盖 labeled break、switch fall-through、labeled continue、do-while。
 */

public class FlowControlChallenge {
    public static void main(String[] args) {

        // ============================================================
        // TODO 1: labeled break — 跳出嵌套循环
        //
        // 需求：在一个二维字符网格中找第一个 'X'
        //   找到后直接跳出两层循环（不是只跳出内层！）
        //
        // 要求：用 labeled break（outer: for(...) { ... break outer; }）
        //
        // 预期输出: "在 [1][2] 找到 X"
        // ============================================================
        System.out.println("--- TODO 1: labeled break ---");

        char[][] grid = {
            {'O', 'O', 'O', 'O'},
            {'O', 'O', 'X', 'O'},
            {'O', 'X', 'O', 'O'},
            {'O', 'O', 'O', 'O'},
        };

        // === 在这里写你的 labeled break 代码 ===
        outer: for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 'X') {
                    System.out.println("在 [" + i + "][" + j + "] 找到 X");
                    break outer;
                }
            }
        }


        // ============================================================
        // TODO 2: switch fall-through — case 穿透
        //
        // 需求：根据月份输出该月的天数（不处理闰年）
        //
        // 要求：利用 fall-through 让 1,3,5,7,8,10,12 共用 "31 天"
        //              让 4,6,9,11 共用 "30 天"
        //              2 月 → "28 天"
        //
        // 测试：用 int month = 2 和 month = 7 分别验证
        // ============================================================
        System.out.println("\n--- TODO 2: switch fall-through ---");

        // === 在这里写你的 fall-through switch ===
        int month = 2;
        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println(month + "月有31天");
                break;
            case 2:
                System.out.println(month + "月有28天");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println(month + "月有30天");
                break;
        }

        // 换一个月份测试
        month = 7;

        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println(month + "月有31天");
                break;
            case 2:
                System.out.println(month + "月有28天");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println(month + "月有30天");
                break;
        }

        // ============================================================
        // TODO 3: labeled continue — 跳过外层循环的当前迭代
        //
        // 需求：打印二维数组，但跳过所有包含 0 的行（整行跳过）
        //
        // 关键：用 labeled continue 从内层循环直接跳到外层循环的下一次迭代
        //
        // 提示语法：
        //   outer:
        //   for (...) {
        //       for (...) {
        //           if (arr[i][j] == 0) continue outer;  // 跳过外层这行
        //       }
        //   }
        //
        // 预期输出：只打印不含 0 的行
        // ============================================================
        System.out.println("\n--- TODO 3: labeled continue ---");

        int[][] data = {
            {1, 2, 3},
            {4, 0, 6},     // ← 这行包含 0，整行跳过
            {7, 8, 9},
            {10, 0, 12},   // ← 跳过
            {13, 14, 15},
        };

        // === 在这里写你的 labeled continue 代码 ===
        outer:
        for(int i = 0 ; i < data.length; i++) {
            for(int j = 0; j < data[0].length; j++) {
                if(data[i][j] == 0) {
                    continue outer;
                }
            }
            System.out.println(Arrays.toString(data[i]));
        }


        // ============================================================
        // TODO 4: do-while — 至少执行一次
        //
        // 需求：模拟一个"至少输入一次，输入 0 结束"的数字输入循环
        //
        // 要求：用 do-while 实现，输入序列用数组模拟（至少执行一次！）
        //   int[] inputs = {5, 3, 8, 0, 2};  // 打印前 4 个数，到 0 停止
        //
        // 关键：do-while 和 while 的唯一区别 —— 条件判断在循环体之后
        //       即使用户一开始就输入 0，也会先执行一次（比如打印退出消息）
        //
        // 预期输出: "处理: 5" "处理: 3" "处理: 8" "退出"
        // ============================================================
        System.out.println("\n--- TODO 4: do-while ---");

        int[] inputs = {5, 3, 8, 0, 2};

        // === 在这里写你的 do-while 代码 ===
        int index = 0;
        do {
            if(inputs[index] == 0) {
                break;
            } else {
                System.out.print("处理：" + inputs[index] + "\t");
            }
            index++;
        } while (inputs[index] != 0);

        System.out.print("退出");

        // ============================================================
        // TODO 5: 综合 — 计算器
        //
        // 需求：一个简单计算器，用 switch + fall-through + while + break 组合
        //
        // 操作符序列：char[] ops = {'+', '-', '*', '/', 'q'};
        // 操作数：    int a = 12, b = 4;
        //
        // 要求：
        //   1. 用 while(true) 循环 + switch(ops[i])
        //   2. '+' '-' '*' '/' 执行对应运算
        //   3. 'q' — 用 break 退出循环
        //   4. default — 利用 switch fall-through 让所有运算符输出结果
        //      （提示：case '+': case '-': case '*': case '/': 共用输出格式？不是的）
        //   5. 用 for 循环遍历 ops 数组控制 i 递增
        //
        // 预期输出：4 行运算结果 + "计算器退出"
        // ============================================================
        System.out.println("\n--- TODO 5: 综合计算器 ---");

        char[] ops = {'+', '-', '*', '/', 'q'};
        int a = 12, b = 4;

        // === 在这里写你的综合代码 ===
        for (int i = 0; i < ops.length; i++) {
            switch (ops[i]) {
                case '+':
                    System.out.println(a + " + " + b + " = " + (a + b));
                    break;
                case '-':
                    System.out.println(a + " - " + b + " = " + (a - b));
                    break;
                case '*':
                    System.out.println(a + " * " + b + " = " + (a * b));
                    break;
                case '/':
                    System.out.println(a + " / " + b + " = " + (a / b));
                    break;
                case 'q':
                    System.out.println("计算器退出");
                    break;
            }
        }

    }
}
