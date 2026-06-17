# 数组与字符串 — 动手练习

> **Topic:** java | **Path:** 语言基础/数组与字符串
> **Difficulty:** beginner | **6 道题**

---

## 练习前须知

1. 建议在 `com/fuhao/arraystring/` 目录下编写每个 Task
2. 每个 Task 是一个独立的 `public static void main(String[] args)` 方法（写在不同文件里）
3. 每题的目标输出已给出，你的代码输出必须与之一致
4. 先保证能写出结果，再考虑代码是否更简洁

---

## Task 1: 打印数组元素

**需求：** 给定一个整数数组：

```java
int[] numbers = {10, 20, 30, 40, 50};
```

使用 `for` 循环按顺序打印每个元素。

**目标输出：**

```text
第 0 个元素: 10
第 1 个元素: 20
第 2 个元素: 30
第 3 个元素: 40
第 4 个元素: 50
```

**文件：** `com/fuhao/arraystring/Task1PrintArray.java`

**覆盖：** 数组声明、数组下标、`length`、`for` 循环

---

## Task 2: 数组求和与平均值

**需求：** 给定一个分数数组：

```java
int[] scores = {88, 95, 72, 64, 100};
```

计算总分和平均分。平均分用整数除法即可。

**目标输出：**

```text
总分: 419
平均分: 83
```

**文件：** `com/fuhao/arraystring/Task2SumAverage.java`

**覆盖：** 累加变量、数组遍历、整数除法

---

## Task 3: 找最大值和最小值

**需求：** 给定数组：

```java
int[] numbers = {18, 6, 32, 44, 9, 27};
```

找出最大值和最小值。

**目标输出：**

```text
最大值: 44
最小值: 6
```

**文件：** `com/fuhao/arraystring/Task3MaxMin.java`

**覆盖：** 初始值选择、循环比较、`if` 条件判断

**提示：** 可以先让 `max = numbers[0]`、`min = numbers[0]`，再从下标 `1` 开始比较。

---

## Task 4: 统计及格人数

**需求：** 给定成绩数组：

```java
int[] scores = {59, 60, 75, 48, 90, 100};
```

统计及格人数和不及格人数。分数 `>= 60` 算及格。

**目标输出：**

```text
及格人数: 4
不及格人数: 2
```

**文件：** `com/fuhao/arraystring/Task4PassCount.java`

**覆盖：** 条件统计、计数器变量、数组遍历

---

## Task 5: 字符串数组查询

**需求：** 给定学生姓名数组：

```java
String[] names = {"张三", "李四", "王五", "赵六"};
String target = "王五";
```

查找 `target` 是否存在于数组中。

**目标输出：**

```text
找到了: 王五, 下标是 2
```

**文件：** `com/fuhao/arraystring/Task5FindName.java`

**覆盖：** `String[]`、字符串内容比较、`.equals()`、`break`

**提示：** 字符串内容比较使用 `target.equals(names[i])`。

---

## Task 6: 用数组重写星期转换

**需求：** 把你之前的 `Task2Weekday.java` 思路改成数组版本。给定：

```java
int day = 3;
```

用 `String[]` 保存星期名称，通过下标取出对应名称，并判断工作日或周末。

**目标输出：**

```text
星期三 是工作日
```

**文件：** `com/fuhao/arraystring/Task6WeekdayArray.java`

**覆盖：** `String[]`、下标换算、边界判断、字符串拼接

**提示：** 人类习惯的星期编号是 `1-7`，数组下标是 `0-6`，所以需要使用 `day - 1`。

---

## 推荐完成顺序

先做 Task 1 和 Task 2，确认自己能稳定遍历数组；再做 Task 3 和 Task 4，练习比较和统计；最后做 Task 5 和 Task 6，把字符串数组和之前的流程控制知识连起来。
