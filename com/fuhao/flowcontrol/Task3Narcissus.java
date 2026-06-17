package com.fuhao.flowcontrol;

public class Task3Narcissus {
    public static void main(String[] args) {
        // 用 for 循环找出 100-999 的所有水仙花数
        // 水仙花数: 各位数字的立方和等于该数本身
        // 输出格式: 水仙花数: 153 370 371 407
        System.out.print("水仙花数:");
        for (int i = 100; i <= 999; i++) {
            int hundreds = i / 100;          // 百位
            int tens = i / 10 % 10;          // 十位
            int ones = i % 10;               // 个位
            // 用整数乘法计算立方，避免 Math.pow 的 double 精度问题
            if (hundreds * hundreds * hundreds
                    + tens * tens * tens
                    + ones * ones * ones == i) {
                System.out.print(" " + i);
            }
        }
        System.out.println();
    }
}
