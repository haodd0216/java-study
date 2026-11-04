import java.time.LocalDate;
import java.time.Period;

/** 按月续费的终身卡：完成两个 TODO 后运行 main 检查业务规则。 */
enum BillingCycle {
    MONTHLY(Period.ofMonths(1));

    private final Period period;

    BillingCycle(Period period) {
        this.period = period;
    }

    Period period() {
        return period;
    }
}

enum BenefitTerm {
    LIFETIME
}

final class MonthlyLifetimeSubscription {
    private final String memberName;
    private final BillingCycle billingCycle;
    private final BenefitTerm benefitTerm;
    private final LocalDate paidThrough;

    MonthlyLifetimeSubscription(String memberName, BillingCycle billingCycle,
                                BenefitTerm benefitTerm, LocalDate paidThrough) {
        this.memberName = memberName;
        this.billingCycle = billingCycle;
        this.benefitTerm = benefitTerm;
        this.paidThrough = paidThrough;
    }

    LocalDate paidThrough() {
        return paidThrough;
    }

    BenefitTerm benefitTerm() {
        return benefitTerm;
    }

    // TODO 1：已付截止日当天仍可使用；次日起暂停。
    boolean isActiveOn(LocalDate date) {
        throw new UnsupportedOperationException("TODO 1: 判断权益是否可用");
    }

    // TODO 2：付款成功后返回新对象。有效期内顺延，逾期从付款日重算。
    MonthlyLifetimeSubscription renew(LocalDate paymentDate) {
        throw new UnsupportedOperationException("TODO 2: 付款并续费");
    }
}

public class MonthlyLifetimeCardPractice {
    public static void main(String[] args) {
        MonthlyLifetimeSubscription card = new MonthlyLifetimeSubscription(
                "张三", BillingCycle.MONTHLY, BenefitTerm.LIFETIME,
                LocalDate.of(2026, 10, 30));

        check(card.isActiveOn(LocalDate.of(2026, 10, 30)), "到期日当天仍可用");
        check(!card.isActiveOn(LocalDate.of(2026, 10, 31)), "逾期次日暂停");

        MonthlyLifetimeSubscription lateRenewal = card.renew(LocalDate.of(2026, 11, 3));
        check(lateRenewal.paidThrough().equals(LocalDate.of(2026, 12, 3)), "逾期从付款日重算");
        check(lateRenewal.isActiveOn(LocalDate.of(2026, 11, 3)), "补缴当天恢复");
        check(lateRenewal.benefitTerm() == BenefitTerm.LIFETIME, "终身资格保留");

        MonthlyLifetimeSubscription earlyRenewal = card.renew(LocalDate.of(2026, 10, 20));
        check(earlyRenewal.paidThrough().equals(LocalDate.of(2026, 11, 30)), "提前续费从原截止日顺延");
        check(card.paidThrough().equals(LocalDate.of(2026, 10, 30)), "原卡不变");

        System.out.println("全部检查通过");
    }

    private static void check(boolean condition, String label) {
        if (!condition) {
            throw new AssertionError(label);
        }
        System.out.println("通过：" + label);
    }
}
