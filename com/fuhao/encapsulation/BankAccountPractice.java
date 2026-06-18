package com.fuhao.encapsulation;

public class BankAccountPractice {
    public static void main(String[] args) {
        // 在这里创建 BankAccount 对象并测试
        BankAccount bankAccount = new BankAccount("A001", "小明");
        System.out.println(bankAccount.getAccountNo());
        System.out.println(bankAccount.getOwnerName());
        System.out.println(bankAccount.getBalance());
        bankAccount.deposit(100);
        bankAccount.withdraw(30);
        bankAccount.withdraw(1000);
        bankAccount.deposit(-10);
        bankAccount.setOwnerName("小王");
        System.out.println(bankAccount.getOwnerName());
        System.out.println(bankAccount.getBalance());
    }
}

class BankAccount {
    // TODO: 定义 private 字段：accountNo、ownerName、balance
    private final String accountNo;
    private String ownerName;
    private double balance;
    // TODO: 编写构造方法：传入 accountNo 和 ownerName，balance 初始为 0
    public BankAccount(String accountNo, String ownerName) {
        this.accountNo = accountNo;
        setOwnerName(ownerName);
        this.balance = 0;
    }
    // TODO: 为 accountNo 提供 getter，不提供 setter
    public String getAccountNo() {
        return this.accountNo;
    }
    // TODO: 为 ownerName 提供 getter 和 setter，setter 中校验 null 和空字符串
    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        if(ownerName == null || ownerName.isEmpty()) {
            return;
        }
        this.ownerName = ownerName;
    }

    // TODO: 为 balance 提供 getter，不提供 setter
    public double getBalance() {
        return this.balance;
    }
    // TODO: 编写 deposit 方法
    public void deposit(double amount) {
        if(amount <= 0) {
            System.out.println("存款金额需大于0");
            return;
        }
        this.balance += amount;
    }

    // TODO: 编写 withdraw 方法
    public void withdraw(double amount) {
        if(amount <= 0) {
            System.out.println("取款金额必须大于0");
            return;
        }
        if(amount > this.balance) {
            System.out.println("余额不足");
            return;
        }
        this.balance -= amount;
    }
}
