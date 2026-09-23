import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 日期与时间 API — Intermediate 练习
 *
 * 会员续费中心：练习 LocalDate、Period、DateTimeFormatter、
 * ChronoUnit.DAYS.between，以及续费时的日期比较。
 *
 * 打开 README.md 阅读完整需求，完成下方 8 个 TODO。
 */
enum RenewCardType {
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

    RenewCardType(String text, Period period) {
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
    // TODO 2: 根据起算日计算到期日
    // 要求：
    //   普通卡：startDate.plus(period)
    //   终身卡：LocalDate.of(9999, 12, 31)
    // ============================================================
    LocalDate expireDateFrom(LocalDate startDate) {
        if(isLifetime()) {
            return LocalDate.of(9999, 12, 31);
        }
        return startDate.plus(period);
    }

    // ============================================================
    // TODO 3: 根据中文名称解析枚举
    // 要求：支持前后空格；非法输入抛出：
    //   IllegalArgumentException("未知会员卡类型: " + trimmed)
    // ============================================================
    static RenewCardType fromText(String text) {
        String trimmed = text.trim();
        return switch (trimmed) {
            case "月卡" -> RenewCardType.ONE_MONTH;
            case "一年卡" -> RenewCardType.ONE_YEAR;
            case "三年卡" -> RenewCardType.THREE_YEARS;
            case "终身卡" -> RenewCardType.LIFETIME;
            default -> throw new IllegalArgumentException("未知会员卡类型: " + trimmed);
        };
    }
}

class RenewMemberCard {
    private final String memberName;
    private final RenewCardType cardType;
    private final LocalDate startDate;

    RenewMemberCard(String memberName, RenewCardType cardType, LocalDate startDate) {
        this.memberName = memberName;
        this.cardType = cardType;
        this.startDate = startDate;
    }

    LocalDate expireDate() {
        return cardType.expireDateFrom(startDate);
    }

    // ============================================================
    // TODO 4: 判断指定日期是否有效
    // 要求：到期日当天仍然有效
    // ============================================================
    boolean isActiveOn(LocalDate date) {
        return !expireDate().isBefore(date);
    }

    // ============================================================
    // TODO 5: 计算 today 当天还剩多少天
    // 要求：
    //   已过期返回 0
    //   到期日当天返回 0
    //   未过期返回 ChronoUnit.DAYS.between(today, expireDate())
    // ============================================================
    long remainingDaysOn(LocalDate today) {
        if(isActiveOn(today) && !today.isEqual(expireDate())) {
            return ChronoUnit.DAYS.between(today, expireDate());
        }
        return 0;
    }

    // ============================================================
    // TODO 6: 续费
    // 要求：
    //   如果当前会员在 today 仍有效，从当前 expireDate() 开始顺延
    //   如果当前会员在 today 已过期，从 today 开始重新计算
    //   返回新的 RenewMemberCard，会员名不变，卡类型变成 newType
    // ============================================================
    RenewMemberCard renew(RenewCardType newType, LocalDate today) {
        LocalDate newStartDate = isActiveOn(today) ? expireDate() : today;
        return new RenewMemberCard(memberName, newType, newStartDate);
    }

    // ============================================================
    // TODO 7: 输出摘要
    // 格式：
    //   张三: 月卡, 开卡日=2026-09-01, 到期日=2026-10-01
    // 要求：开卡日、到期日都使用 formatter 格式化
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

public class DateTimeRenewPractice {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.parse("2026-09-23", formatter);

        System.out.println("=== 当前会员 ===");
        RenewMemberCard zhang = parseLine("张三,月卡,2026-09-01", formatter);
        RenewMemberCard li = parseLine("李四,一年卡,2025-01-01", formatter);
        System.out.println(zhang.summary(formatter));
        System.out.println(li.summary(formatter));

        System.out.println("\n=== 剩余天数 ===");
        System.out.println("张三剩余天数: " + zhang.remainingDaysOn(today));
        System.out.println("李四剩余天数: " + li.remainingDaysOn(today));

        System.out.println("\n=== 续费后 ===");
        RenewMemberCard renewedZhang = zhang.renew(RenewCardType.ONE_YEAR, today);
        RenewMemberCard renewedLi = li.renew(RenewCardType.ONE_MONTH, today);
        System.out.println(renewedZhang.summary(formatter));
        System.out.println(renewedLi.summary(formatter));

        System.out.println("\n=== 终身卡 ===");
        RenewMemberCard wang = parseLine("王五,终身卡,2026-09-23", formatter);
        System.out.println(wang.summary(formatter));

        System.out.println("\n=== 非法输入 ===");
        try {
            parseLine("赵六,五年卡,2026-09-23", formatter);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // ============================================================
    // TODO 8: 解析 CSV 行
    // 输入格式：姓名,卡类型,开卡日期
    // 示例："张三,月卡,2026-09-01"
    // ============================================================
    static RenewMemberCard parseLine(String line, DateTimeFormatter formatter) {
        String[] parts = line.split(",");
        String memberName = parts[0].trim();
        RenewCardType cardType = RenewCardType.fromText(parts[1]);
        LocalDate startDate = LocalDate.parse(parts[2].trim(), formatter);
        return new RenewMemberCard(memberName, cardType, startDate);
    }
}
