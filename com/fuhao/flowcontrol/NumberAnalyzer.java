package com.fuhao.flowcontrol;

public class NumberAnalyzer {
    public static void main(String[] args) {
        // 模拟菜单选择序列
        int[] menuChoices = {1, 2, 3, 4, 0};
        int[] arguments = {7, 5, 123, 6};
        int argIndex = 0;
        int choiceIndex = 0;

        // TODO: 用 while (true) 包住整个菜单逻辑
        // TODO: 根据 menuChoices[choiceIndex] 用 switch 分发到不同功能
        // TODO: 选项 0 时 break 退出循环
        // TODO: switch default 处理无效选项
        while(true) {
            System.out.println("===== 数字分析器 =====");
            System.out.println("1. 判断素数");
            System.out.println("2. 计算阶乘");
            System.out.println("3. 各位数字之和");
            System.out.println("4. 打印乘法表（指定行数）");
            System.out.println("0. 退出");
            System.out.println("请选择：" + menuChoices[choiceIndex]);
            switch (menuChoices[choiceIndex]) {
                case 1:
                    boolean isPrime = true;
                    System.out.println(Math.sqrt(arguments[argIndex]));
                    for(int i = 2; i <= Math.sqrt(arguments[argIndex]); i++) {
                        if(arguments[argIndex] % i == 0) {
                            isPrime = false;
                            break;
                        }
                    }
                    System.out.println(arguments[argIndex] + (isPrime ? " 是素数" : " 不是素数"));
                break;
                case 2:
                    int result = 1;
                    for(int i = 1; i <= arguments[argIndex]; i++) {
                        result = i * result;
                    }
                    System.out.println(arguments[argIndex] + "! = " + result);
                break;
                case 3:
                    int total = 0;
                    int temp = arguments[argIndex];
                    while (temp != 0) {
                        total += (temp % 10);
                        temp = temp / 10;
                    }
                    System.out.println(arguments[argIndex] + " 的各位数字之和 = " + total);
                break;
                case 4:
                    for(int i = 1; i <= arguments[argIndex]; i++) {
                        for (int j = 1; j <= i; j++) {
                            System.out.print(j + "*" + i + "=" + i*j + "\t");
                        }
                        System.out.println();
                    }
                break;
                default:
                    System.out.println("程序退出，再见！");
            }
            if(menuChoices[choiceIndex] == 0) {
                break;
            }
            argIndex++;
            choiceIndex++;
        }
    }
}
