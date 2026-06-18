package com.fuhao.arraystring;

import java.util.Arrays;

public class Task3Languages {
    public static void main(String[] args) {
        String text = "Java,Python,C,Go,Rust,Java,Python";

        // TODO: 分割、统计总数、去重种类数
        String[] languages = text.split(",");
        System.out.println("语言列表: " + Arrays.toString(languages));

        // 去重：排序后相邻相同只算一次
        String[] sorted = Arrays.copyOf(languages, languages.length);
        Arrays.sort(sorted);
        int unique = 1;
        for (int i = 1; i < sorted.length; i++) {
            if (!sorted[i].equals(sorted[i - 1])) {
                unique++;
            }
        }
        System.out.println("共 " + languages.length + " 个，去重后 " + unique + " 种");
    }
}
