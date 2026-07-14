# 内部类与匿名类 — 综合练习

**难度**: Beginner (入门)  
**目标**: 在一个小项目中同时使用四种内部类，理解各自的语法和适用场景

---

## 场景

构建一个「任务处理器」——接收不同类型的任务（打印任务、计算任务），处理并输出结果。

你需要完成 `TaskProcessorPractice.java` 中的 **5 个 TODO**，每个 TODO 对应一种内部类的使用。

---

## TODO 清单

| # | TODO | 类型 | 说明 |
|---|------|------|------|
| 1 | `TaskStatistics` | **静态嵌套类** | 统计任务数量，不依赖 TaskProcessor 实例 |
| 2 | `TaskLogger` | **成员内部类** | 日志记录，需要访问外部类的 private 字段 |
| 3 | `formatResult` 方法内 | **局部内部类** | 在方法内临时格式化输出 |
| 4-5 | `main` 方法中 | **匿名内部类** | 用匿名类创建两种 TaskHandler 实现 |

---

## 涉及的接口

```java
interface TaskHandler {
    String getType();       // 返回任务类型名
    void execute(String content);  // 执行任务
}
```

---

## 完成后

1. 编译运行：`javac -d out TaskProcessorPractice.java && java -cp out TaskProcessorPractice`
2. 预期输出应显示 4 种内部类各产生一行以上输出

---

## 检查要点

- [ ] 静态嵌套类是否通过 `new TaskProcessorPractice.TaskStatistics()` 创建？
- [ ] 成员内部类是否通过 `processor.new TaskLogger()` 创建？
- [ ] 匿名内部类语法是否为 `new TaskHandler() { ... }`？
- [ ] `outer.new Inner()` 和 `new Outer.StaticNested()` 的区别是否清楚？
