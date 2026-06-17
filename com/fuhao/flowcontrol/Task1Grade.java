package com.fuhao.flowcontrol;

public class Task1Grade {
    public static void main(String[] args) {
        int score = 78;   // 可以改成其他分数测试

        // 用 if-else 判断等级并输出「分数: 78 → 等级: C」
        String grade;
        if (score >= 90) {
            grade = "A";
        } else if (score >= 80) {
            grade = "B";
        } else if (score >= 70) {
            grade = "C";
        } else if (score >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }
        System.out.println("分数: " + score + " → 等级: " + grade);
    }
}
