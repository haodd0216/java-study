package com.fuhao.arraystring;

public class Task5Filter {
    public static void main(String[] args) {
        String[] sensitiveWords = {"密码", "暴力", "毒品"};
        String text = "请输入密码以访问系统，禁止暴力、毒品等内容";

        System.out.println("过滤前: " + text);
        for (String word : sensitiveWords) {
            text = text.replace(word, "***");
        }
        System.out.println("过滤后: " + text);
    }
}
