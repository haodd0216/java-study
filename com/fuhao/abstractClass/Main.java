package com.fuhao.abstractClass;

/**
 * 抽象类定 “必须做什么”，子类负责 “具体怎么做”，用的时候只看 “规矩” 不管 “是谁”。
 */
public class Main {

	public static void main(String[] args) {
		// TODO: 用抽象类给一个有工资收入和稿费收入的小伙伴算税:
		Income[] incomes = new Income[] { new SalaryIncome(3000), new SalaryIncome(7500), new RoyaltyIncome(12000) };
		double total = 0;
		// TODO:
		for(Income income : incomes) {
			total += income.getTax();
		}
		System.out.println(total);
	}

}
