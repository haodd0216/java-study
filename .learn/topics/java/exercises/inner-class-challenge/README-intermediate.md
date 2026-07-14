# 内部类与匿名类 — Intermediate 练习

**难度**: Intermediate  
**上一轮**: Beginner (87%)  
**核心考点**: 匿名内部类 + 回调模式、effectively final、匿名类访问方法局部变量

---

## 场景

实现一个简易 **EventBus（事件总线）**。你可以注册监听器来处理不同类型的事件——这是匿名内部类最经典的真实场景。

---

## TODO 清单（6个）

| # | 位置 | 知识点 |
|---|------|--------|
| 1 | `send` 方法 | **匿名内部类调用父类方法** — 用 `super.xxx()` |
| 2 | `cloneEvent` 方法 | **匿名内部类访问局部变量** — 理解 effectively final |
| 3 | `registerEmailHandler` | **匿名类实现接口做回调** |
| 4 | `registerSMSHandler` | **匿名类实现接口做回调** |
| 5 | `main` 中 | **匿名内部类继承抽象类** — EventBus 的 AutoLogger |
| 6 | `describe` 方法 | **局部内部类** — 在方法中格式化事件描述 |

---

## 类/接口结构

```
Event (static nested class)      ← 事件数据：type + message + timestamp
EventListener (interface)        ← 回调接口：void onEvent(Event e)
EventBus (external class)        ← 事件总线：注册/发送/日志
EventBusDemo (main)              ← 运行入口
```
