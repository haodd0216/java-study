import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * 日期与时间 API — Beginner 练习
 *
 * 会员卡到期日：用 LocalDate 表示开卡日，用 Period 表示有效期，
 * 并判断到期日当天是否仍然有效。
 *
 * 打开 README.md 阅读完整需求，完成下方 7 个 TODO。
 */
enum DateCardType {
    // ============================================================
    // TODO 1: 定义 4 个枚举常量
    // 要求：
    //   ONE_MONTH   -> "月卡", Period.ofMonths(1)
    //   ONE_YEAR    -> "一年卡", Period.ofYears(1)
    //   THREE_YEARS -> "三年卡", Period.ofYears(3)
    //   LIFETIME    -> "终身卡", null
    // ============================================================

    // TODO: 在这里定义枚举常量
    ONE_MONTH("月卡", Period.ofMonths(1)),
    ONE_YEAR("一年卡", Period.ofYears(1)),
    THREE_YEARS("三年卡", Period.ofYears(3)),
    LIFETIME("终身卡", null);

    private final String text;
    private final Period period;

    // ============================================================
    // TODO 2: 编写枚举构造方法，给 text 和 period 赋值
    // ============================================================
    DateCardType(String text, Period period) {
        this.text = text;
        this.period = period;
    }

    String getText() {
        return text;
    }

    boolean isLifetime() {
        return this == LIFETIME;
    }

    // ============================================================
    // TODO 3: 根据开卡日计算到期日
    // 要求：
    //   普通卡：startDate.plus(period)
    //   终身卡：LocalDate.of(9999, 12, 31)
    // ============================================================
    LocalDate expireDateFrom(LocalDate startDate) {
        if(isLifetime()) {
            return LocalDate.of(9999, 12, 31);
        } else {
            return startDate.plus(period);
        }
    }

    // ============================================================
    // TODO 4: 根据中文名称解析枚举
    // 要求：
    //   fromText("月卡")     -> DateCardType.ONE_MONTH
    //   fromText(" 三年卡 ") -> DateCardType.THREE_YEARS
    //   fromText("五年卡")   -> 抛出 IllegalArgumentException
    // ============================================================
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

    DateMemberCard(String memberName, DateCardType cardType, LocalDate startDate) {
        this.memberName = memberName;
        this.cardType = cardType;
        this.startDate = startDate;
    }

    LocalDate expireDate() {
        return cardType.expireDateFrom(startDate);
    }

    // ============================================================
    // TODO 5: 判断指定日期是否有效
    // 要求：到期日当天仍然算有效
    // 提示：!expireDate().isBefore(date)
    // ============================================================
    boolean isActiveOn(LocalDate date) {
        return !expireDate().isBefore(date);
    }

    // ============================================================
    // TODO 6: 输出会员卡摘要
    // 格式：
    //   张三: 月卡, 开卡日=2026-09-23, 到期日=2026-10-23
    // 提示：日期要用 formatter 格式化
    // ============================================================
    String summary(DateTimeFormatter formatter) {
        return memberName +
                ": " +
                cardType.getText() +
                ", 开卡日=" +
                startDate.format(formatter) +
                ", 到期日=" +
                expireDate().format(formatter);
    }
}

public class DateTimeApiPractice {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("=== 会员卡摘要 ===");
        DateMemberCard zhang = parseLine("张三,月卡,2026-09-23", formatter);
        DateMemberCard li = parseLine("李四,三年卡,2026-09-23", formatter);
        DateMemberCard wang = parseLine("王五,终身卡,2026-09-23", formatter);

        System.out.println(zhang.summary(formatter));
        System.out.println(li.summary(formatter));
        System.out.println(wang.summary(formatter));

        System.out.println("\n=== 有效性判断 ===");
        LocalDate expireDay = LocalDate.parse("2026-10-23", formatter);
        LocalDate afterExpireDay = LocalDate.parse("2026-10-24", formatter);
        System.out.println("张三 2026-10-23 是否有效: " + zhang.isActiveOn(expireDay));
        System.out.println("张三 2026-10-24 是否有效: " + zhang.isActiveOn(afterExpireDay));

        System.out.println("\n=== 非法输入 ===");
        try {
            parseLine("赵六,五年卡,2026-09-23", formatter);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // ============================================================
    // TODO 7: 解析 CSV 行
    // 输入格式：姓名,卡类型,开卡日期
    // 示例："张三,月卡,2026-09-23"
    // 要求：
    //   姓名 trim
    //   卡类型用 DateCardType.fromText 解析
    //   开卡日期用 LocalDate.parse(parts[2].trim(), formatter) 解析
    // ============================================================
    static DateMemberCard parseLine(String line, DateTimeFormatter formatter) {
        String[] parts = line.split(",");
        String memberName = parts[0].trim();
        DateCardType cardType = DateCardType.fromText(parts[1]);
        LocalDate startDate = LocalDate.parse(parts[2].trim(), formatter);
        return new DateMemberCard(memberName, cardType, startDate);
    }
}
