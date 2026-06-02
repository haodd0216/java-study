package com.fuhao.string_;

public class Main {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "HELLO".toLowerCase();
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        //是否包含子串
        System.out.println(s1.contains("ll"));
    }
}
