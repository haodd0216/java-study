package com.fuhao.inheritance;

public class NotificationSystem {
    public static void main(String[] args) {
        // TODO ①: 创建 Notification[] 数组（向上转型）
        Notification[] notifications = {
            new EmailNotification("系统管理员", "您的账户有异常登录", "admin@example.com"),
            new SMSNotification("验证码服务", "您的验证码是 123456", "138xxxx1234"),
            new PushNotification("物流助手", "您的包裹已发货", "device_token_abc")
        };

        // TODO ②: 遍历数组，调用 preview() 和 send()（多态！）
        System.out.println("=== 通知预览 ===");
        for (Notification n : notifications) {
            n.preview();
        }

        System.out.println("\n=== 发送通知 ===");
        for (Notification n : notifications) {
            n.send();
        }

        // TODO ③: instanceof + 向下转型，打印渠道名和特有目标
        System.out.println("\n=== 渠道详情 ===");
        for (Notification n : notifications) {
            if (n instanceof EmailNotification e) {
                System.out.println("渠道: " + e.getChannelName() + " | 目标: " + e.getEmailAddress());
            } else if (n instanceof SMSNotification s) {
                System.out.println("渠道: " + s.getChannelName() + " | 目标: " + s.getPhoneNumber());
            } else if (n instanceof PushNotification p) {
                System.out.println("渠道: " + p.getChannelName() + " | 目标: " + p.getDeviceToken());
            }
        }

        // TODO ④: 创建 Trackable[] 数组，遍历打印渠道名和送达状态
        System.out.println("\n=== 发送状态追踪 ===");
        Trackable[] trackables = new Trackable[notifications.length];
        for (int i = 0 ; i < notifications.length ; i++) {
            trackables[i] = (Trackable) notifications[i];
            System.out.println("渠道名：" + trackables[i].getChannelName() + "，送达状态：" + trackables[i].getDeliveryStatus());
        }

        // TODO ⑤: Notification n = new Notification("test", "test");
        // → 编译错误，抽象类不能 new

        // 验证: Shape s = new Circle(2.0);
        // s.getRadius();  → 编译错误，父类引用只能调父类声明的方法

    }
}

// TODO: 抽象类 Notification（sender, content, preview(), abstract send()）
abstract class Notification {
    private String sender;
    private String content;

    public Notification(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void preview() {
        System.out.println("[预览] 发送者: " + this.sender + " | 内容: " + this.content);
    }
    
    public abstract void send();
}

// TODO: 接口 Trackable（getDeliveryStatus(), getChannelName()）

interface Trackable {
    String getDeliveryStatus();
    String getChannelName();
}

// TODO: EmailNotification extends Notification implements Trackable

class EmailNotification extends Notification implements Trackable {
    private String emailAddress;

    public EmailNotification(String sender, String content, String emailAddress) {
        super(sender, content);
        this.setEmailAddress(emailAddress);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        if(emailAddress.isEmpty()) {
            throw new IllegalArgumentException("邮箱地址不正确");
        }
        this.emailAddress = emailAddress;
    }

    @Override
    public void send() {
        System.out.println("发送邮件到" + this.getEmailAddress() + " 发送人：" + this.getSender() + " | 邮件内容：" + this.getContent());
    }

    @Override
    public String getDeliveryStatus() {
        double random = Math.random();
        return random < 0.3 ? "邮件发送失败" : "邮件发送成功";
    }

    @Override
    public String getChannelName() {
        return "邮件";
    }
}

// TODO: SMSNotification extends Notification implements Trackable
class SMSNotification extends Notification implements Trackable {
    private String phoneNumber;

    public SMSNotification(String sender, String content, String phoneNumber) {
        super(sender, content);
        this.setPhoneNumber(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("手机号不正确");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send() {
        System.out.println("发送短信到：" + this.getPhoneNumber() + " 发送人：" + this.getSender() + " | 发送内容：" + this.getContent());
    }

    @Override
    public String getDeliveryStatus() {
        return Math.random() < 0.4 ? "短信发送失败" : "短信发送成功";
    }

    @Override
    public String getChannelName() {
        return "短信";
    }
}

// TODO: PushNotification extends Notification implements Trackable
class PushNotification extends Notification implements Trackable {
    private String deviceToken;

    public PushNotification(String sender, String content, String deviceToken) {
        super(sender, content);
        this.setDeviceToken(deviceToken);
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        if(deviceToken.isEmpty()) {
            throw new IllegalArgumentException("设备令牌不能为空");
        }
        this.deviceToken = deviceToken;
    }

    @Override
    public void send() {
        System.out.println("推送通知到：" + this.getDeviceToken() + " 推送者：" + this.getSender() + " | 推送内容：" + this.getContent());
    }

    @Override
    public String getDeliveryStatus() {
       return "推送成功";
    }

    @Override
    public String getChannelName() {
        return "推送";
    }
}
