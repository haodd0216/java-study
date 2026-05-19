package com.fuhao.abstractClass;

/**
 * 定义抽象类Income
 */
public abstract class Income {
    protected double income;
	// TODO
    public abstract double getTax();

    public Income(double income) {
        this.income = income;
    }
}
