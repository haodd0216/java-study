package com.fuhao.Interface;

public class SalaryIncome extends BaseIncome {

	// TODO
    public SalaryIncome(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        if(this.getIncome() <= 5000) {
            return 0;
        }
        return (this.getIncome() - 5000) * 0.2;
    }
}
