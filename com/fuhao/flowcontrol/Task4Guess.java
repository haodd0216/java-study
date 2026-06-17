package com.fuhao.flowcontrol;

public class Task4Guess {
    public static void main(String[] args) {
        int answer = 67;
        int[] guesses = {50, 80, 67};

        // 用 while + break 实现猜数字
        int index = 0;
        while (true) {
            int guess = guesses[index];
            if (guess < answer) {
                System.out.println("猜 " + guess + ": 猜小了");
            } else if (guess > answer) {
                System.out.println("猜 " + guess + ": 猜大了");
            } else {
                System.out.println("猜 " + guess + ": 猜对了！共猜 " + (index + 1) + " 次");
                break;
            }
            index++;
        }
    }
}
