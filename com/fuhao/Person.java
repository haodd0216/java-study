package com.fuhao;

public class Person {
    private String name;
    private int age;
    private double height;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if(age <= 0 || age> 100) {
            throw new IllegalArgumentException("invalid age value");
        }
        this.age = age;
    }
}
