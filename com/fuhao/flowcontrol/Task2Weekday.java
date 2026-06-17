package com.fuhao.flowcontrol;

public class Task2Weekday {
    public static void main(String[] args) {
        int day = 3;   // 可以改成 1-7 测试

        // 用 switch 输出中文星期 + 工作日/周末
        String weekType = switch (day) {
            case 1, 2, 3, 4, 5 -> "工作日";
            case 6, 7             -> "周末";
            default               -> "未知";
        };
        String name = switch (day) {
            case 1 -> "星期一";
            case 2 -> "星期二";
            case 3 -> "星期三";
            case 4 -> "星期四";
            case 5 -> "星期五";
            case 6 -> "星期六";
            case 7 -> "星期天";
            default -> "未知";
        };
        System.out.println(name + " 是" + weekType);
    }
}
