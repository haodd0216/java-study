# 流程控制 — 动手练习

> **Topic:** java | **Path:** 语言基础/流程控制
> **Difficulty:** beginner+ | **5 道题**

---

## 练习前须知

1. 在 `com/fuhao/flowcontrol/` 目录下编写每个 Task
2. 每个 Task 是一个独立的 `public static void main(String[] args)` 方法（写在不同文件里）
3. 每题的目标输出已给出，你的代码输出必须与之一致
4. 全部通过才算完成

---

## Task 1: 等级评定（if-else）

**需求：** 输入一个分数 `int score = 78;`，根据以下规则打印等级：
- ≥90 → "A"
- ≥80 → "B"
- ≥70 → "C"
- ≥60 → "D"
- <60 → "F"

**目标输出：**
```
分数: 78 → 等级: C
```

**文件：** `com/fuhao/flowcontrol/Task1Grade.java`

**覆盖：** if-else if-else 链、条件边界、(选做)用卫语句处理 <0 或 >100 的非法输入

---

## Task 2: 星期转换（switch）

**需求：** `int day = 3;`，用 switch 输出对应中文星期名，并判断是工作日还是周末。
- 1-5 → "工作日"
- 6-7 → "周末"

**目标输出：**
```
星期三 是工作日
```

**文件：** `com/fuhao/flowcontrol/Task2Weekday.java`

**覆盖：** switch 分支、(选做)Java 14 箭头语法

---

## Task 3: 水仙花数（for + if）

**需求：** 找出 100-999 之间所有的水仙花数（各位数字的立方和等于该数本身，如 153 = 1³ + 5³ + 3³）。

**目标输出：**
```
水仙花数: 153 370 371 407
```

**文件：** `com/fuhao/flowcontrol/Task3Narcissus.java`

**覆盖：** for 循环、取余/取整提取各位数字、if 条件判断

---

## Task 4: 猜数字（while + break）

**需求：** 模拟猜数字游戏。预设答案 `int answer = 67;`，给你一组猜测值 `int[] guesses = {50, 80, 67};`。用 while 循环依次猜，每次输出提示「猜大了」或「猜小了」，猜中时用 break 退出。

**目标输出：**
```
猜 50: 猜小了
猜 80: 猜大了
猜 67: 猜对了！共猜 3 次
```

**文件：** `com/fuhao/flowcontrol/Task4Guess.java`

**覆盖：** while 循环、break 退出、计数器变量

---

## Task 5: 九九乘法表（嵌套 for）

**需求：** 打印标准的九九乘法表（左下三角格式），格式要求：每个算式用 `\t` 分隔。

**目标输出（前 4 行）：**
```
1*1=1
1*2=2	2*2=4
1*3=3	2*3=6	3*3=9
1*4=4	2*4=8	3*4=12	4*4=16
...
```

**文件：** `com/fuhao/flowcontrol/Task5Multiplication.java`

**覆盖：** 嵌套 for 循环、格式化输出、循环变量边界控制

---
> 💡 提示：写完一个 Task 后就可以先编译运行看效果，不用等全部写完。
