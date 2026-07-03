# 练习：多态综合挑战 — 通知系统

## 背景

你需要设计一个**通知系统**，支持多种通知渠道。用抽象类 + 接口 + 多态的能力来构建它。

## 难度：🔥🔥 挑战级

这次不提供骨架注释，只给规格。你需要自己设计类结构。

## 文件

在 `com/fuhao/inheritance/` 目录下创建 `NotificationSystem.java`

## 规格

### 1. 抽象类 Notification

```
abstract class Notification
├── 字段: private String sender（发送者）
├── 字段: private String content（内容）
├── 构造方法: Notification(String sender, String content)
├── 具体方法: getSender(), getContent()
├── 抽象方法: public abstract void send()
└── 具体方法: public void preview()
             打印: [预览] 发送者: xxx | 内容: xxx
```

### 2. 接口 Trackable（可追踪）

```
interface Trackable
├── String getDeliveryStatus()    // 返回配送状态字符串
└── String getChannelName()       // 返回渠道名称，如 "邮件" / "短信" / "推送"
```

### 3. 子类 EmailNotification

```
class EmailNotification extends Notification implements Trackable
├── 字段: private String emailAddress（目标邮箱）
├── 构造方法: EmailNotification(String sender, String content, String emailAddress)
├── send(): 打印 "📧 发送邮件到 xxx | 发送者: xxx | 内容: xxx"
├── getDeliveryStatus(): 返回 "邮件已送达" 或 "发送失败: 邮箱不存在"（随机选一个）
├── getChannelName(): 返回 "邮件"
└── getEmailAddress() 的 getter
```

### 4. 子类 SMSNotification

```
class SMSNotification extends Notification implements Trackable
├── 字段: private String phoneNumber（目标手机号）
├── 构造方法: SMSNotification(String sender, String content, String phoneNumber)
├── send(): 打印 "📱 发送短信到 xxx | 发送者: xxx | 内容: xxx"
├── getDeliveryStatus(): 返回 "短信已送达" 或 "发送失败: 号码无效"（随机选一个）
├── getChannelName(): 返回 "短信"
└── getPhoneNumber() 的 getter
```

### 5. 子类 PushNotification

```
class PushNotification extends Notification implements Trackable
├── 字段: private String deviceToken（设备令牌）
├── 构造方法: PushNotification(String sender, String content, String deviceToken)
├── send(): 打印 "🔔 推送通知到 xxx | 发送者: xxx | 内容: xxx"
├── getDeliveryStatus(): 返回 "推送已送达" 或 "发送失败: 设备离线"（随机选一个）
├── getChannelName(): 返回 "推送"
└── getDeviceToken() 的 getter
```

### 6. main 方法（在 NotificationSystem 类中）

```java
public static void main(String[] args) {
    // ① 创建 Notification 数组（向上转型），放入 3 种通知各 1 个
    // ② 遍历数组：调用 preview() 和 send()（多态！）
    // ③ 再遍历一次：用 instanceof 判断类型，向下转型，打印每种特有字段
    // ④ 用 Trackable 数组收集所有通知，遍历调用 getChannelName() + getDeliveryStatus()
    // ⑤ 验证：下面这行代码如果取消注释会怎样？
    //     Notification n = new Notification("test", "test");
}
```

### 7. 验证项目（在你的代码中实现）

```java
// 在 main 末尾加上这段验证
Shape s = new Circle(2.0);
// s.getRadius();  // 取消注释会编译报错，为什么？

if (s instanceof Circle) {
    Circle c = (Circle) s;
    System.out.println("验证通过：需要 instanceof 才能调用 getRadius()");
}
```

## 预期输出（参考）

```
=== 通知预览 ===
[预览] 发送者: 系统管理员 | 内容: 您的账户有异常登录
[预览] 发送者: 验证码服务 | 内容: 您的验证码是 123456
[预览] 发送者: 物流助手 | 内容: 您的包裹已发货

=== 发送通知 ===
📧 发送邮件到 admin@example.com | 发送者: 系统管理员 | 内容: 您的账户有异常登录
📱 发送短信到 138xxxx1234 | 发送者: 验证码服务 | 内容: 您的验证码是 123456
🔔 推送通知到 device_token_abc | 发送者: 物流助手 | 内容: 您的包裹已发货

=== 渠道详情 ===
渠道: 邮件 | 目标: admin@example.com
渠道: 短信 | 目标: 138xxxx1234
渠道: 推送 | 目标: device_token_abc

=== 发送状态追踪 ===
[邮件] 邮件已送达
[短信] 发送失败: 号码无效
[推送] 推送已送达

验证通过：需要 instanceof 才能调用 getRadius()
```

## 检查清单

完成后自检：
- [ ] Notification 是 abstract class，不能 new
- [ ] send() 是 abstract 方法
- [ ] 三个子类都 implements Trackable
- [ ] Notification[] 数组（抽象类类型多态）
- [ ] Trackable[] 数组（接口类型多态）
- [ ] instanceof + 向下转型访问特有字段
- [ ] preview() 用的是父类的具体方法，但内部调用的是子类实际类型

完成后告诉我「写好了」。
