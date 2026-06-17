package com.fuhao.flowcontrol;

import java.util.Arrays;

public class Task5Multiplication {
    public static void main(String[] args) {
        // TODO: 用嵌套 for 打印左下三角九九乘法表
        // 格式: 每个算式用 \t 分隔
        for(int i = 1; i <= 9; i++) {
            for(int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + i * j + '\t');
            }
            System.out.println();
        }

        int[] arr = {10, 20, 30, 40};
        Arrays.copyOf(arr, 5);
        System.out.println(Arrays.toString(arr));
    }
}
