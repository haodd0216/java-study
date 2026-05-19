package com.fuhao.Interface;

/**
 * 稿费收入税率是20%
 */
public class RoyaltyIncome extends BaseIncome {

    public RoyaltyIncome(double income) {
        super(income);
    }
	// TODO
    @Override
    public double getTax() {
        return this.getIncome() * 0.2;
    }
}
