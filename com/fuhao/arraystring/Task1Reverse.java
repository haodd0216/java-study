package com.fuhao.arraystring;

import java.util.Arrays;

public class Task1Reverse {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        // TODO: 反转数组并输出
        System.out.println("原数组: " + Arrays.toString(arr));
        for(int i = 0 ; i < arr.length; i++) {
            if(i == (arr.length / 2)) {
                break;
            }
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        System.out.println("反转后: " + Arrays.toString(arr));
    }
}
