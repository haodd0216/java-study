package com.fuhao.extend;

public class Student extends Person {
    private double score;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    //继承有个特点，子类无法访问父类的private字段修饰的变量或者方法，
//    public String sayHello() {
//        return "Hello + " + name;
//    }

    //父类protected修饰的变量或方法可以在子类中访问
    public int howAge() {
        return age;
    }

    /*
    * super（超类）
    * 这里使用super，this都是一样的效果
    * */
    public String sayHello() {
        return "Hello " + super.getName() + " " + "Hello " + this.getName();
    }
}
