package com.fuhao.extendSuper;

public class Student extends Person {
    private int score;

    /*
     * 如果父类没有默认的构造方法，子类就要相应的显式调用super()
     */
    public Student(String name, int age, int score) {
        this.score = score;
    }
}
