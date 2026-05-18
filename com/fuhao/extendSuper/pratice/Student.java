package com.fuhao.extendSuper.pratice;

public class Student extends Person{
    protected int score;

    public Student(String name, int age, int score) {
        super(name, age);
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
