# 数组与字符串 — 动手练习

> **Topic:** java | **Path:** 语言基础/数组与字符串
> **Difficulty:** beginner+ | **5 道题**

---

## 练习前须知

1. 所有文件写在 `com/fuhao/arraystring/` 目录下
2. 每题目标输出已给出
3. 每个文件有 `TODO` 标记待你完成

---

## Task 1: 数组反转（数组基础）

**需求：** 有一个数组 `int[] arr = {1, 2, 3, 4, 5};`，将其反转后输出。

**目标输出：**
```
原数组: [1, 2, 3, 4, 5]
反转后: [5, 4, 3, 2, 1]
```

**文件：** `com/fuhao/arraystring/Task1Reverse.java`

**提示：** 用 for 循环交换首尾元素：`arr[i]` ↔ `arr[arr.length - 1 - i]`，循环到中间即可。用 `Arrays.toString()` 打印。

**覆盖：** 数组声明、索引操作、长度属性、Arrays.toString()

---

## Task 2: 数组统计（Arrays 工具类）

**需求：** `int[] scores = {85, 92, 58, 73, 66, 99, 45, 78};`，用 `java.util.Arrays` 计算：
- 排序后的最低分和最高分
- 平均分（double，保留一位小数）

**目标输出：**
```
最低分: 45, 最高分: 99
平均分: 74.5
```

**文件：** `com/fuhao/arraystring/Task2Stats.java`

**覆盖：** `Arrays.sort()`, `Arrays.copyOf()`, 数组遍历求和

---

## Task 3: 字符串分割与统计（String 方法）

**需求：** `String text = "Java,Python,C,Go,Rust,Java,Python";`，用 String 方法完成：
- 用 `split(",")` 分割
- 统计共多少个语言（计数）
- 去重后有多少种语言（用 Arrays 辅助）

**目标输出：**
```
语言列表: [Java, Python, C, Go, Rust, Java, Python]
共 7 个，去重后 5 种
```

**文件：** `com/fuhao/arraystring/Task3Languages.java`

**覆盖：** `split()`, `Arrays.toString()`, 数组长度

---

## Task 4: 构建 SQL 语句（StringBuilder + 数组）

**需求：** `String[] columns = {"name", "age", "score"};`，用 `StringBuilder` 构建一条 SQL 查询和插入语句：
- 查询：`SELECT name, age, score FROM students`
- 插入：`INSERT INTO students (name, age, score) VALUES (?, ?, ?)`

**目标输出：**
```
SELECT name, age, score FROM students
INSERT INTO students (name, age, score) VALUES (?, ?, ?)
```

**文件：** `com/fuhao/arraystring/Task4SqlBuilder.java`

**覆盖：** `StringBuilder` 拼接、数组遍历、`String.join()` 的替代实现

---

## Task 5: 敏感词过滤（综合）

**需求：** `String[] sensitiveWords = {"密码", "暴力", "毒品"};`，待过滤文本 `String text = "请输入密码以访问系统，禁止暴力内容";`。遍历敏感词数组，将文本中所有敏感词替换为 `***`。

**目标输出：**
```
过滤前: 请输入密码以访问系统，禁止暴力内容
过滤后: 请输入***以访问系统，禁止***内容
```

**文件：** `com/fuhao/arraystring/Task5Filter.java`

**覆盖：** 数组遍历, `String.replace()`, String 不可变

---
> 💡 `String.replace(old, new)` 返回新字符串，不会修改原字符串。敏感词循环替换时要记得重新赋值！
