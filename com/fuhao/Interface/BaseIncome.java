package com.fuhao.Interface;

abstract class BaseIncome implements Income {
    private final double income;

    public BaseIncome(double income) {
        if(income < 0) {
            throw new IllegalArgumentException("income is negative");
        }
        this.income = income;
    }

    public double getIncome() {
        return income;
    }
}
