package com.fuhao.Interface;

/**
 * 在抽象类中，抽象方法本质上是定义接口规范：即规定高层类的接口，从而保证所有子类都有相同的接口实现，这样，多态就能发挥出威力。
 * 如果一个抽象类没有字段，所有方法全部都是抽象方法：
 * abstract class Person {
 *     public abstract void run();
 *     public abstract String getName();
 * }
 * 就可以把该抽象类改写为接口：interface。
 * 在Java中，使用interface可以声明一个接口：
 */
public class Main {
    public static void main(String[] args) {
        // TODO: 用接口给一个有工资收入和稿费收入的小伙伴算税:
        Income[] incomes = new BaseIncome[] {new SalaryIncome(7500), new RoyaltyIncome(12000) };
        double total = 0;
        // TODO:
        for(Income income : incomes) {
            total += income.getTax();
        }
        System.out.println(total);
    }
}
