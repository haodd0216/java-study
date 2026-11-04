# 日期与时间 API 挑战练习：月付终身卡

## 背景

会员拥有终身资格，但按月付款。某个月没有续费，权益暂停；后来补缴，从补缴日恢复。请把“终身资格”和“已付有效期”分开建模。

## 目标

完成 [MonthlyLifetimeCardPractice.java](./MonthlyLifetimeCardPractice.java) 中的 TODO，让所有检查通过。不要修改检查用例。

## 业务规则

- `benefitTerm` 始终是 `LIFETIME`；暂停付款不会删除终身资格。
- `paidThrough` 是**已付截止日，含当天**。在这一天权益仍可用，次日起暂停。
- `isActiveOn(date)` 根据已付截止日判断权益当天是否可用。
- `renew(paymentDate)` 表示付款成功，返回一张新的卡，保留会员名、付款周期和终身资格。
- 如果付款日仍在已付有效期内，从原 `paidThrough` 顺延一个日历月。
- 如果付款日已超过 `paidThrough`，从 `paymentDate` 起算一个日历月；付款当天立即恢复权益。
- 原卡对象不变。不要用 `9999-12-31` 计算下次付款日。

## 需要完成

- [ ] TODO 1：实现 `isActiveOn(LocalDate date)`。
- [ ] TODO 2：实现 `renew(LocalDate paymentDate)`。
- [ ] 运行程序，确认到期当天、逾期暂停、补缴恢复、提前续费和原对象不变这几种情况都通过。

## 运行

```bash
javac ./.learn/topics/java/exercises/monthly-lifetime-card/MonthlyLifetimeCardPractice.java
java -cp ./.learn/topics/java/exercises/monthly-lifetime-card MonthlyLifetimeCardPractice
```

## 提示

<details>
<summary>续费从哪一天起算？</summary>

先比较 `paymentDate` 和 `paidThrough`。仍有效时从 `paidThrough` 顺延；逾期时从 `paymentDate` 起算。使用 `BillingCycle.period()` 取得 `Period`。

</details>

<details>
<summary>为什么不能只看 LIFETIME？</summary>

`LIFETIME` 表示终身资格；付款状态控制当前权益能否使用。两者回答的是不同问题。

</details>

## 后续思考

`LocalDate.of(2026, 1, 31).plusMonths(1)` 会得到哪一天？如果产品要求每月都以“31 号”为续费锚点，现有规则还够用吗？先完成本练习，再讨论这个边界。
