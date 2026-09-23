# Practice Session - 2026-09-23

## Concept Practiced

- Concept: 日期与时间 API
- Difficulty: Beginner
- Exercise Name: 会员卡到期日

## User's Submitted Code

```java
enum DateCardType {
    ONE_MONTH("月卡", Period.ofMonths(1)),
    ONE_YEAR("一年卡", Period.ofYears(1)),
    THREE_YEARS("三年卡", Period.ofYears(3)),
    LIFETIME("终身卡", null);

    private final String text;
    private final Period period;

    DateCardType(String text, Period period) {
        this.text = text;
        this.period = period;
    }

    boolean isLifetime() {
        return this == LIFETIME;
    }

    LocalDate expireDateFrom(LocalDate startDate) {
        if(isLifetime()) {
            return LocalDate.of(9999, 12, 31);
        } else {
            return startDate.plus(period);
        }
    }

    static DateCardType fromText(String text) {
        String trimmed = text.trim();
        return switch (trimmed) {
            case "月卡" -> DateCardType.ONE_MONTH;
            case "一年卡"  -> DateCardType.ONE_YEAR;
            case "三年卡" -> DateCardType.THREE_YEARS;
            case "终身卡"  -> DateCardType.LIFETIME;
            default -> throw new IllegalArgumentException("未知会员卡类型: " + trimmed);
        };
    }
}

class DateMemberCard {
    private final String memberName;
    private final DateCardType cardType;
    private final LocalDate startDate;

    LocalDate expireDate() {
        return cardType.expireDateFrom(startDate);
    }

    boolean isActiveOn(LocalDate date) {
        return !expireDate().isBefore(date);
    }

    String summary(DateTimeFormatter formatter) {
        return memberName +
                ": " +
                cardType.getText() +
                ", 开卡日=" +
                startDate.format(formatter) +
                ", 到期日=" +
                expireDate();
    }
}
```

## AI Feedback

运行结果完全符合预期：

```text
=== 会员卡摘要 ===
张三: 月卡, 开卡日=2026-09-23, 到期日=2026-10-23
李四: 三年卡, 开卡日=2026-09-23, 到期日=2029-09-23
王五: 终身卡, 开卡日=2026-09-23, 到期日=9999-12-31

=== 有效性判断 ===
张三 2026-10-23 是否有效: true
张三 2026-10-24 是否有效: false

=== 非法输入 ===
未知会员卡类型: 五年卡
```

完成得很稳：你正确使用了 `LocalDate` 表示开卡日，使用 `Period.ofMonths/ofYears` 表示业务周期，没有把月卡误写成固定 30 天；终身卡用特殊到期日 `9999-12-31` 处理；`isActiveOn` 使用 `!expireDate().isBefore(date)`，准确表达了“到期日当天仍然有效”的业务规则；`DateTimeFormatter` 用于解析输入日期，CSV 解析也处理了 `trim()`。

一个小的代码质量建议：`summary(DateTimeFormatter formatter)` 里到期日目前直接拼了 `expireDate()`，因为 `LocalDate.toString()` 默认刚好是 `yyyy-MM-dd`，所以输出正确。但既然方法接收了 `formatter`，最好也显式格式化到期日：

```java
", 到期日=" + expireDate().format(formatter)
```

这样如果以后格式改成 `yyyy/MM/dd`，开卡日和到期日会一起变化，不会一个听指挥、一个自己开小差。

另一个小点：`fromText` 用 `switch` 写死中文值没问题，适合当前练习。如果枚举值变多，遍历 `values()` 并比较 `getText()` 会更少重复。

## Assessment

- Understanding: Solid
- Status update: in_progress → in_progress
- confidence: 0.34 → 0.55
- practice_count: 0 → 1
