# Object、String 与包装类 — 学习会话

> **日期:** 2026-07-15
> **主题:** Java
> **路径:** 常用核心 API / Object、String 与包装类
> **等级:** intermediate

---

## 定位：为什么这三个放一起学？

你已经在 `面向对象编程` 里用过继承——但你有没有想过：**继承的尽头是什么？**

答案是 `Object`。Java 里**每一个类都直接或间接继承自 `Object`**。你写的 `Student`、`TaskProcessorPractice`、`EventBus`，它们的"老祖宗"都是 `Object`。

```java
class Student { }   // 看起来没继承任何东西
// 实际上等价于：
class Student extends Object { }   // Java 自动帮你补上
```

所以 `Object` 是所有类的根，它定义了三个所有对象都拥有的方法：`equals()`、`hashCode()`、`toString()`。理解它们，你才算真正"懂了对象"。

而 `String` 和包装类，是 `Object` 这套体系里最常用的两个"亲戚"：

| 类 | 是什么 | 你之前用过吗 |
|----|--------|-------------|
| `Object` | 所有类的祖先 | 间接用了（`equals` 等） |
| `String` | 文本字符串 | 天天用（`"hello"`、拼接） |
| 包装类 | 基本类型的"对象外衣" | 可能没意识到 |

---

## 类比：Object = 所有物品的共同"出厂标签"

想象一个超市，货架上摆着：苹果、牛奶、手机、衣服……五花八门。

但它们**都**有一些共同的东西：

- 都有一个**条码**（`toString()` —— 打印出来看看是什么）
- 都能**比较是否相同**（`equals()` —— 判断两个是不是同一个东西）
- 都能**计算一个分类编号**（`hashCode()` —— 快速分拣到货架）

`Object` 就是那个"所有商品的共同出厂标签"。任何东西拿到手，你都能调用这三个方法。

---

## 核心机制一：Object 的三大方法

### 1. `equals()` — 判断"相等"

这是**最容易被误解**的方法。先看最关键的对比：

```java
// == 比较的是"引用地址"（是不是同一个对象）
// equals() 比较的是"内容"（值相不相等）

String a = new String("hello");
String b = new String("hello");

System.out.println(a == b);       // false —— 两个不同的对象
System.out.println(a.equals(b));  // true  —— 内容相同
```

**为什么 `String` 的 `equals()` 能比较内容？** 因为 `String` 类**重写**了 `Object` 的 `equals()`。

而你自己写的类，默认的 `equals()` 继承自 `Object`，行为等同于 `==`（比较地址）：

```java
class Student {
    String name;
    Student(String name) { this.name = name; }
}

Student s1 = new Student("张三");
Student s2 = new Student("张三");

System.out.println(s1.equals(s2));  // false！因为 Student 没重写 equals
```

**面试必考**：如果你想让两个 `Student` 按 `name` 相等，就必须在 `Student` 里重写 `equals()`。

### 2. `hashCode()` — 哈希值

配合 `equals()` 使用，是 HashMap/HashSet 的基石（后面集合模块会深入）。现在只需记住一条铁律：

> **如果两个对象 `equals()` 相等，它们的 `hashCode()` 必须相等。**

### 3. `toString()` — 字符串表示

```java
Student s = new Student("张三");
System.out.println(s);  
// 默认输出：com.fuhao.Student@1b6d3586 （类名@哈希值的十六进制）
// 因为没重写 toString()
```

重写 `toString()` 后，打印对象就能看到有意义的内容——调试利器。

---

## 核心机制二：String 的不可变性 ⭐

String 最重要的特性：**不可变（immutable）**。一旦创建，内容永远不变。

```java
String s = "hello";
s.toUpperCase();     // 返回新字符串 "HELLO"，但没接住
System.out.println(s);  // 还是 "hello"！原字符串没变

// 必须重新赋值：
s = s.toUpperCase();   // 现在 s 指向新字符串 "HELLO"
```

**为什么设计成不可变？** 三个原因：安全（字符串经常存密码、路径）、性能（字符串常量池复用）、线程安全（多线程不用加锁）。

### 字符串常量池

```java
String s1 = "abc";       // 字面量，放入常量池
String s2 = "abc";       // 池里已有 "abc"，直接复用同一个对象
System.out.println(s1 == s2);  // true！同一个对象

String s3 = new String("abc");  // 强制 new 新对象，绕过常量池
System.out.println(s1 == s3);   // false
```

这就是之前思考题 1 的答案：字面量字符串会被常量池复用，所以 `s1 == s2` 是 `true`。

### 常用方法速查

```java
String s = "  Hello, Java World  ";

s.trim()                // "Hello, Java World" —— 去首尾空格
s.toUpperCase()         // "  HELLO, JAVA WORLD  "
s.toLowerCase()         // "  hello, java world  "
s.split(", ")           // ["  Hello", "Java World  "] —— 按分隔符拆分
s.substring(2, 7)       // "Hello" —— 截取 [2,7)
s.indexOf("Java")       // 9 —— 子串位置
s.contains("World")     // true —— 是否包含
s.replace("Java", "C")  // "  Hello, C World  "
s.length()              // 22 —— 长度
s.charAt(0)             // ' ' —— 第 0 个字符
s.isEmpty()             // false —— 是否为空
s.equals("...")         // 内容比较
```

你之前在 `ScoreManager` 里用的 `Arrays.asList(names).indexOf(...)`，内部其实就是逐个调用 `equals()` 比较。

---

## 核心机制三：包装类

Java 有 8 种基本类型（int、double、boolean...），它们是"值"，不是"对象"。但有时候你必须用对象——比如放进 `ArrayList`（集合只能存对象）。

包装类就是给基本类型套上"对象外衣"：

| 基本类型 | 包装类 |
|----------|--------|
| `int` | `Integer` |
| `double` | `Double` |
| `char` | `Character` |
| `boolean` | `Boolean` |
| `long` | `Long` |
| ... | ... |

### 自动装箱 / 拆箱（Java 5 起自动进行）

```java
Integer num = 42;     // 自动装箱：int → Integer
int n = num;          // 自动拆箱：Integer → int

List<Integer> list = new ArrayList<>();
list.add(10);         // 自动装箱：int 10 → Integer
int x = list.get(0);  // 自动拆箱
```

### Integer 缓存（又一个易错点）

```java
Integer a = 127;
Integer b = 127;
System.out.println(a == b);   // true！-128~127 之间有缓存

Integer c = 128;
Integer d = 128;
System.out.println(c == d);   // false！超出缓存范围，各自 new 新对象
```

> 记住：**比较 Integer 内容用 `equals()`，不要用 `==`**。缓存范围 -128~127 是坑。

### 包装类常用方法

```java
Integer.parseInt("123")   // String → int（返回 123）
Integer.valueOf("123")    // String → Integer
Integer.toString(123)     // int → String
Double.parseDouble("3.14")
Integer.MAX_VALUE         // 2147483647
Integer.MIN_VALUE
```

---

## 完整代码示例

```java
public class ObjectStringDemo {
    public static void main(String[] args) {
        // ---- Object: equals vs == ----
        String a = new String("hello");
        String b = new String("hello");
        System.out.println("== : " + (a == b));        // false
        System.out.println("equals : " + a.equals(b)); // true

        // ---- String 不可变 ----
        String s = "hello";
        s.toUpperCase();
        System.out.println("原样: " + s);               // hello
        s = s.toUpperCase();
        System.out.println("重赋值后: " + s);           // HELLO

        // ---- String 常用方法 ----
        String text = "apple,banana,cherry";
        String[] fruits = text.split(",");
        for (String f : fruits) {
            System.out.println(f.trim().toUpperCase());
        }
        // APPLE / BANANA / CHERRY

        // ---- 包装类 ----
        Integer i1 = 127;
        Integer i2 = 127;
        System.out.println("127 == : " + (i1 == i2));      // true
        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println("128 == : " + (i3 == i4));      // false
        System.out.println("128 equals : " + i3.equals(i4)); // true

        // ---- 包装类转换 ----
        int parsed = Integer.parseInt("42");
        System.out.println(parsed + 1);  // 43
    }
}
```

---

## 常见误区

**误区一："`==` 和 `equals()` 都是比较值"** → `==` 比较引用地址（基本类型除外），`equals()` 比较内容（前提是类重写了它）。

**误区二："String 可以被修改"** → String 不可变，所有"修改"方法都返回新字符串，不接住就丢了。

**误区三："`new String("abc")` 和 `"abc"` 完全一样"** → 前者强制创建新对象，后者走常量池，二者 `==` 不相等。

**误区四："Integer 用 `==` 比较没问题"** → 缓存范围 -128~127 内没问题，超出就出 bug。永远用 `equals()`。

**误区五："自定义类不需要重写 equals"** → 如果你想把两个对象按字段相等比较（放进集合去重等），必须重写 `equals()` 和 `hashCode()`。

---

## 苏格拉底式检验 🧠

> **问题 1**：`String s1 = "abc"; String s2 = new String("abc");` 问 `s1 == s2` 是 true 还是 false？为什么？
>
> **问题 2**：为什么 `Integer` 比较要用 `equals()` 而不是 `==`？（提示：缓存范围）
>
> **问题 3**：你之前写的 `Student` 类（有 name 字段），如果放进 `HashSet` 去重，两个 name 相同的 Student 会被当成重复吗？为什么？怎么修复？
>
> **问题 4**：`String s = "hello"; s.concat(" world");` 执行后 s 的值是什么？

---

## 一句话总结

- **Object**：所有类的根，`equals()`/`hashCode()`/`toString()` 三大方法，自定义类通常要重写
- **`==` vs `equals()`**：前者比地址，后者比内容（需重写）
- **String**：不可变，字面量走常量池，所有修改返回新字符串
- **包装类**：基本类型的对象外衣，自动装箱/拆箱，比较用 `equals()`，注意 Integer 缓存 -128~127

---

## 下一步：子主题入口

掌握本概念后，可以继续深入：

1. **重写 equals 与 hashCode 的完整实践**（集合模块的前置知识）
2. **StringBuilder vs String** 的性能对比（大量拼接场景）
3. **Integer 缓存的源码原理**（valueOf 的实现）
