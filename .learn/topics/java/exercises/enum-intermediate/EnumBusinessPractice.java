/**
 * 枚举 — Intermediate 练习
 *
 * 会员卡业务规则：让 enum 不只保存固定值，还负责和卡类型强相关的计算。
 * 打开 README.md 阅读完整需求，完成下方 8 个 TODO。
 */
enum BusinessCardType {
    // ============================================================
    // TODO 1: 定义枚举常量
    // 要求：
    //   ONE_YEAR    -> "一年卡", 1, 399, 1.00
    //   THREE_YEARS -> "三年卡", 3, 999, 0.85
    //   LIFETIME    -> "终身卡", -1, 2999, 0.70
    // ============================================================

    // TODO: 在这里定义枚举常量
    ONE_YEAR("一年卡", 1, 399, 1.00),
    THREE_YEARS("三年卡", 3, 999, 0.85),
    TWO_YEARS("两年卡", 2, 699, 0.90),
    LIFETIME("终身卡", -1 , 2999, 0.70);

    private final String text;
    private final int years;
    private final int price;
    private final double renewDiscount;

    BusinessCardType(String text, int years, int price, double renewDiscount) {
        // TODO 1 的配套构造赋值也在这里完成
        this.text = text;
        this.years = years;
        this.price = price;
        this.renewDiscount = renewDiscount;
    }

    // ============================================================
    // TODO 2: 实现 3 个 getter
    // ============================================================
    String getText() {
        return this.text;
    }

    int getYears() {
        return this.years;
    }

    int getPrice() {
        return this.price;
    }

    // ============================================================
    // TODO 3: 判断是否为终身卡
    // 要求：使用 == 比较枚举
    // ============================================================
    boolean isLifetime() {
        return this == BusinessCardType.LIFETIME;
    }

    // ============================================================
    // TODO 4: 根据开始年份计算到期年份
    // 要求：
    //   普通卡：startYear + years
    //   终身卡：9999
    // ============================================================
    int expireYearFrom(int startYear) {
        if(this == LIFETIME) {
            return 9999;
        } else {
            return this.years + startYear;
        }
    }

    // ============================================================
    // TODO 5: 计算续费价格
    // 要求：price * renewDiscount，结果转成 int
    // ============================================================
    int renewPrice() {
        return (int) (this.price * this.renewDiscount);
    }

    // ============================================================
    // TODO 6: 返回权益说明
    // 要求：使用 switch (this)
    //   ONE_YEAR    -> "基础权益"
    //   THREE_YEARS -> "基础权益 + 专属客服"
    //   LIFETIME    -> "全部权益 + 终身服务"
    // ============================================================
    String benefits() {
        return switch (this) {
            case ONE_YEAR -> "基础权益";
            case TWO_YEARS, THREE_YEARS -> "基础权益 + 专属客服";
            case LIFETIME -> "全部权益 + 终身服务";
        };
    }

    // ============================================================
    // TODO 7: 根据中文名称解析枚举
    // 要求：支持前后空格；非法输入抛出：
    //   IllegalArgumentException("未知会员卡类型: " + trimmed)
    // ============================================================
    static BusinessCardType fromText(String text) {
        String trimmed = text.trim();
        for(BusinessCardType cardType : BusinessCardType.values()) {
            if(cardType.getText().equals(trimmed)) {
                return cardType;
            }
        }
        throw new IllegalArgumentException("未知会员卡类型: " + trimmed);
    }
}

class MemberCard {
    private final String memberName;
    private final BusinessCardType cardType;
    private final int startYear;

    MemberCard(String memberName, BusinessCardType cardType, int startYear) {
        this.memberName = memberName;
        this.cardType = cardType;
        this.startYear = startYear;
    }

    // ============================================================
    // TODO 8: 输出会员卡摘要
    // 格式：
    //   张三: 三年卡, 到期年份=2029, 续费价=849, 权益=基础权益 + 专属客服
    // 提示：复用 cardType 的方法，不要在这里重复写 switch
    // ============================================================
    String summary() {
        return this.memberName +
                "：" +
                this.cardType.getText() +
                "，到期年份=" +
                this.cardType.expireYearFrom(this.startYear) +
                "，" +
                "续费价=" +
                this.cardType.renewPrice() +
                "，" +
                "权益=" +
                this.cardType.benefits();
    }
}

public class EnumBusinessPractice {
    public static void main(String[] args) {
        System.out.println("=== 会员卡摘要 ===");
        MemberCard zhang = new MemberCard("张三", BusinessCardType.THREE_YEARS, 2026);
        MemberCard li = new MemberCard("李四", BusinessCardType.LIFETIME, 2026);
        System.out.println(zhang.summary());
        System.out.println(li.summary());

        System.out.println("\n=== 解析测试 ===");
        System.out.println(BusinessCardType.fromText("一年卡"));
        System.out.println(BusinessCardType.fromText("两年卡"));
        System.out.println(BusinessCardType.fromText(" 三年卡 "));
        System.out.println(BusinessCardType.fromText("终身卡"));

        System.out.println("\n=== 非法输入 ===");
        try {
            BusinessCardType.fromText("五年卡");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
