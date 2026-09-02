# 会员办理中心 — Object、String 与包装类练习

**难度**: 🟡 Intermediate
**主题路径**: 常用核心 API / Object、String 与包装类
**前置**: 语言基础 ✅ 面向对象编程 ✅

---

## 🎯 Goal

实现一个小型「健身俱乐部会员办理中心」。它会接待多名会员，按**内容**判断是不是同一个人（防止重复办理）、生成会员卡片描述（toString），并把 CSV 行字符串解析成会员数据。

在这个过程中，你要亲手重写 `Object` 的三大方法、体会 `String` 的不可变与常用方法、并踩一次包装类自动装箱的坑。

## 📋 Background

健身俱乐部前台从 Excel 导出一批 CSV 数据，每行是一条会员记录：

```
张三,13800138000,三年卡
李四,13911112222,一年卡
张三,13800138000,三年卡      ← 重复的！只是文案抄了两遍
```

你需要：**把数据读进来 → 识别出重复的张三并拒绝办理 → 为每人打印会员卡片 → 转换积分数字**。

## ✅ Requirements

- [ ] **TODO 1** 重写 `Member.toString()`：返回 `"张三[13800138000]"` 这样的可读格式
- [ ] **TODO 2** 重写 `Member.equals()`：**姓名相同 且 手机号相同** → 视为同一个会员（不管卡类型）
- [ ] **TODO 3** 重写 `Member.hashCode()`：遵循「equals 相等则 hashCode 必相等」的铁律
- [ ] **TODO 4** 实现 `register(Member)`：会员列表中**已存在**（按内容相等判断）则拒绝并返回 false；否则加入并返回 true
- [ ] **TODO 5** 实现 `formatMembers()`：用 `StringBuilder` 把所有会员拼成一张卡片墙（见输出格式）
- [ ] **TODO 6** 实现 `parseLine(String)`：把一行 `"李四,13911112222,一年卡"` 解析成 `Member`，注意 `split` 后要 `trim` 掉空格
- [ ] **TODO 7** 实现 `comparePoints(Integer, Integer)`：正确比较两个积分是否相等——**用 `equals()`，不要用 `==`**（缓存范围陷阱）

## 📁 文件

```
ObjectStringWrapperPractice.java   ← 唯一文件，含 Member + Center + Main
```

## ▶️ 运行

```bash
java ./.learn/topics/java/exercises/object-string-wrapper/ObjectStringWrapperPractice.java
```

## 🧪 预期输出（做对后应看到）

```
=== 解析 CSV ===
新增: 张三[13800138000]
新增: 李四[13911112222]
重复会员被拒绝: 张三[13800138000]
共办理 2 位会员

=== 会员卡片墙 ===
———— 会员名单 ————
1. 张三[13800138000]
2. 李四[13911112222]
———— 共 2 人 ————

=== 包装类积分比较 ===
a == b ? true   (127 在缓存内，== 碰巧对)
c == d ? false  (128 超出缓存，== 是陷阱!)
c equals d ? true
张三积分 == 李四积分 ? false
张三积分 equals 李四积分 ? false
```

> 💡 注意：上面「a==b」那一行是**让你观察陷阱**的输出，不是让你写 ==。你写的 `comparePoints` 必须用 `equals`。

## 💡 Hints

<details>
<summary>Hint 1 — toString</summary>

用 `String` 拼接或 `+`：`return name + "[" + phone + "]"`。
</details>

<details>
<summary>Hint 2 — equals 的标准三步</summary>

```java
// 1. 是不是自己
if (this == obj) return true;
// 2. 是不是 null / 类型不符
if (obj == null || getClass() != obj.getClass()) return false;
// 3. 强转后按字段比
Member other = (Member) obj;
return name.equals(other.name) && phone.equals(other.phone);
```
</details>

<details>
<summary>Hint 3 — hashCode 直接偷懒</summary>

```java
return Objects.hash(name, phone);
```
`Objects.hash` 会自动把多个字段合成一个哈希值，保证和 equals 用的字段一致。
</details>

<details>
<summary>Hint 4 — 判重原理</summary>

列表判重用的是 `contains()` → 内部逐个调用 `equals()`。所以只要你把 equals 写好，`contains` 自动按内容判重。你**不需要**手写循环，直接 `list.contains(m)`。
</details>

<details>
<summary>Hint 5 — StringBuilder</summary>

```java
StringBuilder sb = new StringBuilder();
for (...) { sb.append(i).append(". ").append(m).append("\n"); }
```
</details>

<details>
<summary>Hint 6 — split + trim</summary>

```java
String[] parts = line.split(",");
// parts[0] 可能是 " 李四"，要 trim 掉前导空格
return new Member(parts[0].trim(), parts[1].trim(), parts[2].trim());
```
</details>

<details>
<summary>Hint 7 — Integer 比较（核心考点）</summary>

`comparePoints` 收到的参数是 `Integer`（对象）。用 `==` 比较的是**引用**，-128~127 有缓存碰巧 true，超出就 false。必须：
```java
return a.equals(b);   // 或用 a.intValue() == b.intValue()
```
</details>

## 📎 Related Concepts

- 面向对象编程 / 类与对象（重写方法、this）
- 语言基础 / 数组与字符串（split、equals、StringBuilder 初次接触）
- 集合与数据结构 / HashSet 与 HashMap（equals/hashCode 的用途——下一模块预告）

---

> 🟡 这一题集中轰炸三个易错点：**equals 忘重写导致判重失败**、**Integer 用 == 比较**、**split 后没 trim**。做完这题，你就把这堂课的坑都踩平了。
