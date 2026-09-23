# 日期与时间 API 进阶练习：会员续费中心

## 目标

练习 `LocalDate`、`Period`、`DateTimeFormatter`、`ChronoUnit.DAYS.between` 和日期比较，完成一个更接近真实业务的会员续费中心。

## 背景

真实会员系统里，续费不是简单地“从今天开始重新算”。如果会员还没过期，续费应该从原到期日顺延；如果已经过期，续费才从今天重新开始。否则用户昨天刚买的会员今天续费，系统把剩余时间吞掉，用户会比看到周一早会还难受。

## 业务规则

- 到期日当天仍然有效。
- 如果会员在 `today` 仍有效，续费从当前到期日开始顺延。
- 如果会员在 `today` 已过期，续费从 `today` 开始重新计算。
- 终身卡续费后仍然是 `9999-12-31`。
- 剩余天数：
  - 已过期返回 `0`
  - 到期日当天返回 `0`
  - 未过期返回 `today` 到 `expireDate` 的天数

## 要求

- [ ] TODO 1：定义 `RenewCardType` 枚举常量，使用 `Period` 表达月卡、一年卡、三年卡、终身卡
- [ ] TODO 2：实现 `expireDateFrom(LocalDate startDate)`
- [ ] TODO 3：实现 `fromText(String text)`，支持前后空格
- [ ] TODO 4：实现 `RenewMemberCard.isActiveOn(LocalDate date)`，到期日当天仍然有效
- [ ] TODO 5：实现 `remainingDaysOn(LocalDate today)`，使用 `ChronoUnit.DAYS.between`
- [ ] TODO 6：实现 `renew(RenewCardType newType, LocalDate today)`，按业务规则选择续费起算日
- [ ] TODO 7：实现 `summary(DateTimeFormatter formatter)`，格式化输出会员信息
- [ ] TODO 8：实现 `parseLine(String line, DateTimeFormatter formatter)`，解析 CSV

## 运行方式

```bash
javac ./.learn/topics/java/exercises/date-time-api-intermediate/DateTimeRenewPractice.java
java -cp ./.learn/topics/java/exercises/date-time-api-intermediate DateTimeRenewPractice
```

## 预期输出

```text
=== 当前会员 ===
张三: 月卡, 开卡日=2026-09-01, 到期日=2026-10-01
李四: 一年卡, 开卡日=2025-01-01, 到期日=2026-01-01

=== 剩余天数 ===
张三剩余天数: 8
李四剩余天数: 0

=== 续费后 ===
张三: 一年卡, 开卡日=2026-10-01, 到期日=2027-10-01
李四: 月卡, 开卡日=2026-09-23, 到期日=2026-10-23

=== 终身卡 ===
王五: 终身卡, 开卡日=2026-09-23, 到期日=9999-12-31

=== 非法输入 ===
未知会员卡类型: 五年卡
```

## 提示

<details>
<summary>提示 1：当天有效怎么写？</summary>

```java
return !expireDate().isBefore(date);
```

翻译成人话：只要到期日不是在目标日期之前，就仍然有效。

</details>

<details>
<summary>提示 2：剩余天数怎么算？</summary>

```java
ChronoUnit.DAYS.between(today, expireDate())
```

注意要先处理已过期情况，否则可能得到负数。负数剩余天数就像“欠系统 3 天会员”，听起来像系统在催债。

</details>

<details>
<summary>提示 3：续费起算日</summary>

```java
LocalDate renewStartDate = isActiveOn(today) ? expireDate() : today;
```

未过期从原到期日顺延；已过期从今天重新开始。

</details>

## 相关概念

- 常用核心 API / 日期与时间 API
- `LocalDate`
- `Period`
- `DateTimeFormatter`
- `ChronoUnit.DAYS.between`
- 枚举
