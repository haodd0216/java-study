package com.fuhao.enum_pratice;
enum CardType {
    ONE_YEAR("一年卡", 1),
    THREE_YEARS("三年卡", 3),
    LIFETIME("终身卡", -1);

    private final String text;
    private final int years;

    CardType(String text, int years) {
        this.text = text;
        this.years = years;
    }

    public String getText() {
        return text;
    }

    public int getYears() {
        return years;
    }
}

class Member {
    String name;
    String phone;
    CardType cardType;

    Member(String name, String phone, CardType cardType) {
        this.name = name;
        this.phone = phone;
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return name + "[" + phone + "] - " + cardType.getText();
    }
}

public class EnumDemo {
    public static void main(String[] args) {
        Member m = new Member("张三", "13800138000", CardType.LIFETIME);

        System.out.println(m);

        if (m.cardType == CardType.THREE_YEARS) {
            System.out.println("三年卡会员");
        }

        System.out.println("有效年限：" + m.cardType.getYears());
    }
}