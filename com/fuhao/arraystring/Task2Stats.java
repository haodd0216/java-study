package com.fuhao.arraystring;

import java.util.Arrays;

public class Task2Stats {
    public static void main(String[] args) {
        int[] scores = {85, 92, 58, 73, 66, 99, 45, 78};

        // TODO: 排序、找最高最低、求平均分
        // 提示: 排序前先 copyOf，避免修改原数组
        int[] scores2 = Arrays.copyOf(scores, scores.length);
        Arrays.sort(scores2);
        System.out.println("最低分: " + scores2[0] + ", 最高分: " + scores2[scores2.length - 1]);

        int sum = 0;
        for (int score : scores) {
            sum += score;
        }

        double avg = (double) sum / scores.length;
        System.out.println("平均分: " + avg);
    }
}
