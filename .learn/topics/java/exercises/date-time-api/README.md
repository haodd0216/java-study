# 日期与时间 API 练习：会员卡到期日

## 目标

使用 `LocalDate`、`Period` 和 `DateTimeFormatter` 完成会员卡到期日计算，并正确判断到期日当天是否仍然有效。

## 背景

之前用 `int startYear` 计算会员卡到期年份，就像拿一把只刻了“年份”的尺子去量生日蛋糕，能量个大概，但一碰到月卡就露馅。现在改用 `LocalDate` 表达完整开卡日期，用 `Period` 表达会员卡周期。

## 要求

- [ ] TODO 1：定义 `DateCardType` 的 4 个枚举常量：`ONE_MONTH`、`ONE_YEAR`、`THREE_YEARS`、`LIFETIME`
- [ ] TODO 2：给每个枚举常量绑定中文名称和 `Period`，终身卡的 `Period` 用 `null`
- [ ] TODO 3：实现 `expireDateFrom(LocalDate startDate)`，普通卡返回 `startDate.plus(period)`，终身卡返回 `9999-12-31`
- [ ] TODO 4：实现 `fromText(String text)`，支持前后空格，非法输入抛出 `IllegalArgumentException("未知会员卡类型: " + trimmed)`
- [ ] TODO 5：实现 `DateMemberCard.isActiveOn(LocalDate date)`，到期日当天仍然算有效
- [ ] TODO 6：实现 `summary(DateTimeFormatter formatter)`，格式化输出开卡日和到期日
- [ ] TODO 7：实现 `parseLine(String line, DateTimeFormatter formatter)`，解析 CSV 行

## 运行方式

在项目根目录执行：

```bash
javac ./.learn/topics/java/exercises/date-time-api/DateTimeApiPractice.java
java -cp ./.learn/topics/java/exercises/date-time-api DateTimeApiPractice
```

## 预期输出

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

## 提示

<details>
<summary>提示 1：月卡不要用 30 天</summary>

月卡应该用：

```java
Period.ofMonths(1)
```

不是：

```java
Duration.ofDays(30)
```

用户理解的是“下个月同一天”，不是“固定 30 天”。不然 2 月会出来捣乱，像日历里的调皮同桌。

</details>

<details>
<summary>提示 2：到期日当天有效怎么判断？</summary>

如果到期日当天仍然有效，可以写：

```java
return !expireDate.isBefore(date);
```

含义是：到期日不是在目标日期之前，就仍然有效。

</details>

<details>
<summary>提示 3：解析日期字符串</summary>

```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
LocalDate date = LocalDate.parse("2026-09-23", formatter);
```

</details>

## 相关概念

- 常用核心 API / 日期与时间 API
- 常用核心 API / 枚举
- `LocalDate`
- `Period`
- `DateTimeFormatter`
