# Practice Session - 2026-09-02

## Concept Practiced

- Concept: 枚举
- Difficulty: Intermediate
- Exercise Name: 会员卡业务规则

## User's Submitted Code

```java
enum BusinessCardType {
    ONE_YEAR("一年卡", 1, 399, 1.00),
    THREE_YEARS("三年卡", 3, 999, 0.85),
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

    String getText() {
        return this.text;
    }

    int getYears() {
        return this.years;
    }

    int getPrice() {
        return this.price;
    }

    boolean isLifetime() {
        return this == BusinessCardType.LIFETIME;
    }

    int expireYearFrom(int startYear) {
        return this.years + startYear;
    }

    int renewPrice() {
        return (int) (this.price * this.renewDiscount);
    }

    String benefits() {
        return switch (this) {
            case ONE_YEAR -> "基础权益";
            case THREE_YEARS -> "基础权益 + 专属客服";
            case LIFETIME -> "全部权益 + 终身服务";
        };
    }

    static BusinessCardType fromText(String text) {
        String trimmed = text.trim();
        BusinessCardType value = null;
        for(BusinessCardType cardType : BusinessCardType.values()) {
            if(cardType.getText().equals(trimmed)) {
                value = cardType;
            }
        }
        if(value == null) {
            throw new IllegalArgumentException("未知会员卡类型: " + trimmed);
        }
        return value;
    }
}
```

## AI Feedback

这次进阶练习的大方向是对的：你已经把卡类型相关的数据和行为都集中到了 `BusinessCardType` 里，而不是把规则散落到 `MemberCard` 或 `main` 里。枚举常量、字段赋值、getter、`==` 判断、续费价格、`switch (this)` 权益说明、`fromText` 解析都完成得不错。

实际运行输出：

```text
=== 会员卡摘要 ===
张三：三年卡，到期年份=2029，续费价=849，权益=基础权益 + 专属客服
李四：终身卡，到期年份=2025，续费价=2099，权益=全部权益 + 终身服务
```

关键问题在 `expireYearFrom`：

```java
int expireYearFrom(int startYear) {
    return this.years + startYear;
}
```

普通卡这样算没问题，但终身卡的 `years` 是 `-1`，所以 `2026 + (-1)` 变成了 `2025`。业务规则要求终身卡固定返回 `9999`。这里正好体现了进阶练习的核心：枚举不只是存字段，还要封装每个枚举值背后的特殊规则。

建议修正为：

```java
int expireYearFrom(int startYear) {
    if (isLifetime()) {
        return 9999;
    }
    return startYear + years;
}
```

`fromText` 也可以再简化：找到匹配项后直接 `return cardType`，不用先保存到 `value`。这不是错误，只是代码可以更直接。

`summary()` 的格式和 README 预期有一点标点差异：你用了中文冒号和中文逗号，README 里是英文冒号和英文逗号。语义没问题，如果按自检输出精确对齐，标点也要同步。

## Assessment

- Understanding: Solid
- Status update: in_progress → in_progress
- confidence: 0.32 → 0.48
- practice_count: 1 → 2
