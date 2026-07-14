package com.fuhao.flowcontrol;

public class Task5Multiplication {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + i * j + '\t');
            }
            System.out.println();
        }
    }
}
