package com.fuhao.arraystring;

import java.util.Arrays;

public class ScoreManager {
    public static void main(String[] args) {
        // 数据
        String[] names = {"张三", "李四", "王五", "赵六", "陈七"};
        int[] scores = {85, 92, 58, 73, 66};

        // 模拟输入
        int[] menuChoices = {1, 2, 3, 4, 4, 5, 1, 0};
        String[] searchNames = {"王五", "刘八"};
        String[] newStudentNames = {"刘八"};
        int[] newStudentScores = {88};
        int menuIndex = 0;
        int searchIndex = 0;

        // TODO: while(true) 菜单循环
        while (true) {
            switch (menuChoices[menuIndex]) {
                case 1:
                    StringBuilder str = new StringBuilder();
                    str.append("===== 成绩单 =====");
                    for(int i = 0; i < names.length; i++) {
                        str.append("\n")
                                .append(names[i])
                                .append("\t\t")
                                .append(scores[i])
                                .append("\t")
                                .append(getGrade(scores[i]));
                    }
                    str.append("\n")
                            .append("==================")
                            .append("\n")
                            .append("共 ")
                            .append(names.length)
                            .append("名学生");
                    System.out.println(str);
                break;
                case 2:
                    int[] copyScores = Arrays.copyOf(scores, scores.length);
                    Arrays.sort(copyScores);
                    int total = 0;
                    for(int i = 0; i < scores.length; i++) {
                        if(scores[i] == copyScores[copyScores.length - 1]) {
                            System.out.println("最高分: " + scores[i] + " (" + names[i] + ")");
                        }
                        if(scores[i] == copyScores[0]) {
                            System.out.println("最低分: " + scores[i] + " (" + names[i] + ")");
                        }
                        total += scores[i];
                    }
                    System.out.println("平均分: " + String.format("%.1f", (double)total / scores.length));
                break;
                case 3:
                    int A = 0;
                    int B = 0;
                    int C = 0;
                    int D = 0;
                    int F = 0;
                    for(int i = 0; i < scores.length; i++) {
                        switch (getGrade(scores[i])) {
                            case 'A':
                                A++;
                                break;
                            case 'B':
                                B++;
                                break;
                            case 'C':
                                C++;
                                break;
                            case 'D':
                                D++;
                                break;
                            default:
                                F++;
                        }
                    }
                    System.out.println("A (90-100): " + A + " 人");
                    System.out.println("B (80-89): " + B + " 人");
                    System.out.println("C (70-79): " + C + " 人");
                    System.out.println("D (60-69): " + D + " 人");
                    System.out.println("F (0-59): " + F + " 人");
                break;
                case 4:
                    System.out.println("请输入姓名: " + searchNames[searchIndex]);
                    int index = Arrays.asList(names).indexOf(searchNames[searchIndex]);
                    if(index == -1) {
                        System.out.println("未找到学生: " + searchNames[searchIndex]);
                    } else {
                        System.out.println(names[index] + " → " + scores[index] + " 分");
                    }
                    searchIndex++;
                    break;
                case 5:
                    StringBuilder stringBuilder = new StringBuilder();
                    for(int i = 0; i < newStudentNames.length; i++) {
                        stringBuilder.append("添加学生：")
                                        .append(newStudentNames[i])
                                        .append("，分数：")
                                        .append(newStudentScores[i])
                                        .append("\n");
                    }
                    String[] newStudents = Arrays.copyOf(names, names.length + newStudentNames.length);
                    int[] newScores = Arrays.copyOf(scores, scores.length + newStudentScores.length);
                    System.arraycopy(newStudentNames, 0, newStudents, names.length, newStudentNames.length);
                    System.arraycopy(newStudentScores, 0, newScores, names.length, newStudentNames.length);
                    stringBuilder.append("添加成功！现有 ")
                                    .append(newStudents.length)
                                    .append(" 名学生")
                                    .append("\n")
                                    .append("打印成绩单确认：")
                                    .append("\n===== 成绩单 =====");
                    for(int i = 0; i < newStudents.length; i++) {
                        stringBuilder.append("\n")
                                .append(newStudents[i])
                                .append("\t\t")
                                .append(newScores[i])
                                .append("\t")
                                .append(getGrade(newScores[i]));
                    }
                    names = newStudents;
                    scores = newScores;
                    System.out.println(stringBuilder);
                    break;
                default:
                    break;
            }

            if(menuChoices[menuIndex] == 0) {
                break;
            }
            menuIndex++;
            System.out.println();
        }
        // TODO: switch 分发菜单选项
        // TODO: 选项 1 — 生成成绩单 (StringBuilder + String.format)
        // TODO: 选项 2 — 统计信息 (Arrays.sort)
        // TODO: 选项 3 — 等级分布
        // TODO: 选项 4 — 查找学生 (String.equals)
        // TODO: 选项 5 — 添加学生 (Arrays.copyOf)
        // TODO: 选项 0 — break 退出
    }

    // 可选：抽取计算等级的方法
     static char getGrade(int score) {
        if(score >= 90) {
            return 'A';
        } else if(score >= 80) {
            return 'B';
        } else if(score >= 70) {
            return 'C';
        } else if(score >= 60) {
            return 'D';
        } else {
            return 'F';
        }
     }
}
