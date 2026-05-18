package com.fuhao.extend;

public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("xiaoming");
        student.setAge(13);
        student.setScore(98);
        System.out.println(student.getName() + " " + student.getAge() + " " + student.getScore());
        System.out.println(student.sayHello());
    }
}
