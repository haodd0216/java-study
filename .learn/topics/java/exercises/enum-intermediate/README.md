# 枚举进阶练习：会员卡业务规则

## 目标

把枚举从“固定选项”升级为“承载业务规则的类型”，练习枚举字段、方法、`switch`、字符串解析和业务计算。

## 背景

会员中心不只需要知道卡类型，还要根据卡类型计算到期年份、续费价格和权益说明。如果这些逻辑都写在 `MemberService` 的 `if-else` 里，后续新增卡类型会很容易漏改。这个练习要求你把和卡类型强相关的规则放回 `BusinessCardType` 自己身上。

## 业务规则

| 卡类型 | 中文名 | 年限 | 原价 | 续费折扣 |
| --- | --- | ---: | ---: | ---: |
| `BusinessCardType.ONE_YEAR` | 一年卡 | 1 | 399 | 1.00 |
| `BusinessCardType.THREE_YEARS` | 三年卡 | 3 | 999 | 0.85 |
| `BusinessCardType.LIFETIME` | 终身卡 | -1 | 2999 | 0.70 |

说明：

- 终身卡年限用 `-1` 表示。
- 到期年份：普通卡 = `startYear + years`，终身卡固定返回 `9999`。
- 续费价格：`price * renewDiscount`，向下取整为 `int`。
- 权益说明：
  - 一年卡：`基础权益`
  - 三年卡：`基础权益 + 专属客服`
  - 终身卡：`全部权益 + 终身服务`

## 要求

- [ ] TODO 1：定义 3 个枚举常量，并绑定中文名、年限、原价、续费折扣
- [ ] TODO 2：实现 `getText()`、`getYears()`、`getPrice()`
- [ ] TODO 3：实现 `isLifetime()`，使用 `==`
- [ ] TODO 4：实现 `expireYearFrom(int startYear)`
- [ ] TODO 5：实现 `renewPrice()`，返回折扣后的整数价格
- [ ] TODO 6：实现 `benefits()`，使用 `switch (this)`
- [ ] TODO 7：实现 `fromText(String text)`，支持前后空格，非法输入抛出 `IllegalArgumentException("未知会员卡类型: " + 输入)`
- [ ] TODO 8：完成 `MemberCard.summary()`，输出会员卡摘要

## 运行方式

```bash
javac ./.learn/topics/java/exercises/enum-intermediate/EnumBusinessPractice.java
java -cp ./.learn/topics/java/exercises/enum-intermediate EnumBusinessPractice
```

## 预期输出

```text
=== 会员卡摘要 ===
张三: 三年卡, 到期年份=2029, 续费价=849, 权益=基础权益 + 专属客服
李四: 终身卡, 到期年份=9999, 续费价=2099, 权益=全部权益 + 终身服务

=== 解析测试 ===
ONE_YEAR
THREE_YEARS
LIFETIME

=== 非法输入 ===
未知会员卡类型: 五年卡
```

## 提示

<details>
<summary>提示 1：续费价格怎么转 int？</summary>

可以这样写：

```java
return (int) (price * renewDiscount);
```

这会直接舍弃小数部分。

</details>

<details>
<summary>提示 2：switch 写法</summary>

可以使用传统 `switch`：

```java
switch (this) {
    case ONE_YEAR:
        return "基础权益";
    default:
        return "";
}
```

也可以使用新版 switch 表达式：

```java
return switch (this) {
    case ONE_YEAR -> "基础权益";
    default -> "";
};
```

</details>

## 相关概念

- 常用核心 API / 枚举
- 流程控制 / switch
- 面向对象编程 / 把数据和行为放在一起
