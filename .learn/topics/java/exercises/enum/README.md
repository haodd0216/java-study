# 枚举练习：会员卡类型升级

## 目标

把会员卡类型从容易写错的 `String` 升级为类型安全的 `enum`，并练习枚举字段、构造方法、方法、`==` 比较和字符串解析。

## 背景

你刚完成的会员办理中心里，`cardType` 还是字符串。真实业务里，会员卡类型通常是有限集合，例如“一年卡、三年卡、终身卡”。这类值适合用枚举建模。

## 要求

- [ ] TODO 1：定义 `CardType` 的 3 个枚举常量：`ONE_YEAR`、`THREE_YEARS`、`LIFETIME`
- [ ] TODO 2：给每个枚举常量绑定中文名称 `text` 和有效年限 `years`
- [ ] TODO 3：实现 `getText()`，返回中文名称
- [ ] TODO 4：实现 `getYears()`，返回有效年限，其中终身卡用 `-1`
- [ ] TODO 5：实现 `isLifetime()`，用 `==` 判断当前枚举是否为 `LIFETIME`
- [ ] TODO 6：实现 `fromText(String text)`，把 `"一年卡"`、`"三年卡"`、`"终身卡"` 解析成对应枚举，输入前后空格要忽略
- [ ] TODO 7：把 `Member.cardType` 改成 `CardType`，并让 `toString()` 输出 `张三[13800138000] - 三年卡`

## 运行方式

在项目根目录执行：

```bash
javac ./.learn/topics/java/exercises/enum/EnumPractice.java
java -cp ./.learn/topics/java/exercises/enum EnumPractice
```

如果使用较新的 JDK，也可以直接运行：

```bash
java ./.learn/topics/java/exercises/enum/EnumPractice.java
```

## 预期输出

```text
=== 枚举基础 ===
三年卡
3
true

=== 解析会员 ===
张三[13800138000] - 三年卡
李四[13911112222] - 终身卡

=== 非法输入 ===
未知会员卡类型: 五年卡
```

## 提示

<details>
<summary>提示 1：枚举常量如何带参数？</summary>

枚举常量可以像调用构造方法一样传参数：

```java
ONE_YEAR("一年卡", 1)
```

多个常量之间用逗号分隔，最后一个常量后面用分号结束。

</details>

<details>
<summary>提示 2：fromText 可以怎么写？</summary>

可以遍历 `CardType.values()`，逐个比较 `type.text` 和输入文本是否相等。输入文本记得先 `trim()`。

</details>

<details>
<summary>提示 3：枚举比较为什么用 ==？</summary>

枚举常量是固定的唯一实例，所以 `this == LIFETIME` 是判断当前枚举是不是终身卡的常见写法。

</details>

## 相关概念

- 常用核心 API / 枚举
- Object、String 与包装类 / `String.equals`
- 面向对象编程 / 类与对象
