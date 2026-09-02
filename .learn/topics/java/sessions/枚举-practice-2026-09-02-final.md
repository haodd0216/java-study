# Practice Session - 2026-09-02

## Concept Practiced

- Concept: 枚举
- Difficulty: Intermediate
- Exercise Name: 枚举章节收尾复评

## User's Submitted Code / Answers

```text
String使用==比较的话，比较的是引用地址不是比的内容；Interger有范围-128-127，如果超出这个范围即使值相同，使用==比较也会返回false
到期时间属于的是卡片类型的业务，不应该在会员卡业务里处理
如果增加一个月卡，需要改的地方就有到期时间，当前设计中到期时间只到了年份，不能具体到月份，以及月卡的权益
```

```java
enum BusinessCardType {
    ONE_YEAR("一年卡", 1, 399, 1.00),
    THREE_YEARS("三年卡", 3, 999, 0.85),
    TWO_YEARS("两年卡", 2, 699, 0.90),
    LIFETIME("终身卡", -1 , 2999, 0.70);

    private final String text;
    private final int years;
    private final int price;
    private final double renewDiscount;

    BusinessCardType(String text, int years, int price, double renewDiscount) {
        this.text = text;
        this.years = years;
        this.price = price;
        this.renewDiscount = renewDiscount;
    }

    boolean isLifetime() {
        return this == BusinessCardType.LIFETIME;
    }

    int expireYearFrom(int startYear) {
        if(this == LIFETIME) {
            return 9999;
        } else {
            return this.years + startYear;
        }
    }

    String benefits() {
        return switch (this) {
            case ONE_YEAR -> "基础权益";
            case TWO_YEARS, THREE_YEARS -> "基础权益 + 专属客服";
            case LIFETIME -> "全部权益 + 终身服务";
        };
    }

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
```

## AI Feedback

最终复评通过。你已经能解释枚举与 `String`、包装类比较的差异：`String ==` 比较引用而不是内容，`Integer ==` 会受 `-128` 到 `127` 缓存范围影响，而枚举常量是固定的唯一实例，所以适合用 `==` 做身份判断。

你也正确识别了业务归属：到期时间和卡类型规则强相关，放在 `BusinessCardType` 内部比放在 `MemberCard.summary()` 中更合适。这样 `MemberCard` 只负责组合展示，不负责判断每种卡的具体规则。

新增 `TWO_YEARS` 后，你同步扩展了枚举常量、解析测试和权益分支，并且程序运行通过：

```text
=== 解析测试 ===
ONE_YEAR
TWO_YEARS
THREE_YEARS
LIFETIME
```

你对“月卡”扩展点的判断也很好：它不只是新增一个常量，还会暴露当前模型只能按年份计算到期时间的局限。这个判断说明你已经从“会写 enum”进入到“会审视 enum 背后的业务模型是否合适”。

小提醒：`Integer` 拼写不要写成 `Interger`。另外，`fromText` 里现在已经直接 `return cardType`，可以删除不再使用的 `value` 变量。

## Assessment

- Understanding: Excellent
- Status update: in_progress → mastered
- confidence: 0.48 → 0.74
- practice_count: 2 → 3
