package com.fuhao.arraystring;

public class Task4SqlBuilder {
    public static void main(String[] args) {
        String[] columns = {"name", "age", "score"};

        // TODO: 用 StringBuilder 构建两条 SQL 语句
        StringBuilder selectSqlBuilder = new StringBuilder();
        selectSqlBuilder.append("SELECT ");
        for (int i = 0; i < columns.length; i++) {
            selectSqlBuilder.append(columns[i]);
            if(i != columns.length - 1) selectSqlBuilder.append(", ");
        }
        selectSqlBuilder.append(" FROM students");
        System.out.println(selectSqlBuilder);

        StringBuilder insertSqlBuilder = new StringBuilder();
        insertSqlBuilder.append("INSERT INTO students (");
        for (int i = 0; i < columns.length; i++) {
            if (i > 0) insertSqlBuilder.append(", ");
            insertSqlBuilder.append(columns[i]);
        }
        insertSqlBuilder.append(") VALUES (");
        for (int i = 0; i < columns.length; i++) {
            if (i > 0) insertSqlBuilder.append(", ");
            insertSqlBuilder.append("?");
        }
        insertSqlBuilder.append(")");
        System.out.println(insertSqlBuilder);
    }
}
