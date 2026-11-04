**TODO 1 正确，TODO 2 还缺续费日期计算。** 编译通过；运行时前两项检查通过，第三项报错 `AssertionError: 逾期从付款日重算`，后续检查尚未执行。

你的 `isActiveOn()` 正确包含了截止日当天；`renew()` 返回新对象、保留会员信息和终身资格的做法也正确。

问题在 [renew() 的第 53 行](</Volumes/External HD/code/java-study/.learn/topics/java/exercises/monthly-lifetime-card/MonthlyLifetimeCardPractice.java:53>)：直接把 `paymentDate` 当成新截止日，既没有区分提前续费和逾期补缴，也没有增加一个月。原截止日为 `2026-10-30` 时，按当前代码计算：

| 付款日 | 当前得到的截止日 | 应得到的截止日 |
| --- | --- | --- |
| 2026-11-03（逾期） | 2026-11-03 | 2026-12-03 |
| 2026-10-20（提前） | 2026-10-20 | 2026-11-30 |
| 2026-10-30（到期当天） | 2026-10-30 | 2026-11-30 |

修改提示：能否复用 `isActiveOn(paymentDate)` 选择起算日？仍有效时选原 `paidThrough`，逾期时选 `paymentDate`；然后用 `plus(billingCycle.period())` 得到新截止日，传给新对象。

再留意月末边界：`2026-01-31` 加一个日历月得到 `2026-02-28`，因此不要用固定 30 天代替一个月。第 52 行的 `System.out.println(paymentDate)` 属于调试输出，完成后可以删除。

<details>
<summary>本次提交与学习记录</summary>

日期：2026-11-04。概念：日期与时间 API。难度：挑战。练习：月付终身卡。

本次提交的两个 TODO：

```java
    // TODO 1：已付截止日当天仍可使用；次日起暂停。
    boolean isActiveOn(LocalDate date) {
        return date.isBefore(paidThrough()) || date.isEqual(paidThrough());
    }

    // TODO 2：付款成功后返回新对象。有效期内顺延，逾期从付款日重算。
    MonthlyLifetimeSubscription renew(LocalDate paymentDate) {
        System.out.println(paymentDate);
        return new MonthlyLifetimeSubscription(memberName, billingCycle, benefitTerm, paymentDate);
    }
```

评估：部分完成，续费起算日与日历周期运算仍需练习。状态：mastered → needs_practice；confidence：0.78 → 0.83；practice_count：2 → 3；last_practiced：2026-11-04。

已保存本次反馈并同步学习进度，练习源码保持原样。

</details>

---

**这版逻辑正确，练习通过。** 原有 7 项检查全部通过；补充检查了到期当天续费、新截止日边界、返回新对象、平年与闰年月末、连续续费和跨年补缴，7 项也全部通过。

[renew()](</Volumes/External HD/code/java-study/.learn/topics/java/exercises/monthly-lifetime-card/MonthlyLifetimeCardPractice.java:51>) 已正确区分：有效期内从原截止日顺延，逾期从付款日重算；终身资格保留，原对象不变。

一个可选改进：把两处 `plusMonths(1)` 改成 `plus(billingCycle.period())`，让续费计算使用枚举中定义的周期。当前写法符合本题的月付规则。

<details>
<summary>本次提交与学习记录</summary>

日期：2026-11-04。概念：日期与时间 API。难度：挑战。练习：月付终身卡，第 2 次检查。

本次提交的两个 TODO：

```java
    // TODO 1：已付截止日当天仍可使用；次日起暂停。
    boolean isActiveOn(LocalDate date) {
        return date.isBefore(paidThrough()) || date.isEqual(paidThrough());
    }

    // TODO 2：付款成功后返回新对象。有效期内顺延，逾期从付款日重算。
    MonthlyLifetimeSubscription renew(LocalDate paymentDate) {
        if(isActiveOn(paymentDate)) {
            return new MonthlyLifetimeSubscription(memberName, billingCycle, benefitTerm, paidThrough().plusMonths(1));
        }
        return new MonthlyLifetimeSubscription(memberName, billingCycle, benefitTerm, paymentDate.plusMonths(1));
    }
```

评估：完成良好。状态：needs_practice → mastered；confidence：0.83 → 0.93；practice_count：3 → 4；last_practiced：2026-11-04。

已追加复查反馈并同步学习进度，练习源码保持原样。

</details>
