# 类与对象 — Intermediate 练习

**难度**: Intermediate  
**目标**: 通过学生成绩管理系统，深入理解构造器重载、this 链式调用、引用语义和对象交互

---

## 场景

构建一个 `Student` 类和一个 `Classroom` 类，「班级」对象可以添加、「学生」对象之间可以比较成绩。

---

## TODO 清单（6个）

| # | TODO | 测试知识点 |
|---|------|-----------|
| 1 | 构造器重载 + `this()` 链 | 多构造器互相调用，避免重复代码 |
| 2 | 标准构造器 | this.name = name, 字段初始化 |
| 3 | `compareScore` 方法 | 对象交互 — 一个对象接收另一个对象 |
| 4 | 静态字段 `studentCount` | 类级别变量 vs 实例变量 |
| 5 | 构造器中递增计数 | 每次 new 时自动 +1 |
| 6 | `Classroom` 类 + 对象数组 | 聚合关系，遍历对象数组 |

---

## 文件结构

```
ClassroomPractice.java    ← 包含 Student + Classroom + Main
```

---

## 检查要点

- [ ] `this(参数)` 是调用其他构造器吗？（必须在第一行）
- [ ] 静态字段通过 `Student.getStudentCount()` 访问？
- [ ] `compareScore` 中 `this.score` vs `other.score` 区别清晰？
- [ ] Classroom 成功聚合了多个 Student 对象？
