/**
 * 内部类与匿名类 Intermediate 练习 — EventBus
 *
 * 完成 6 个 TODO，聚焦匿名内部类 + 回调模式、effectively final、局部内部类。
 */

import java.time.LocalDateTime;

// ============================================================
// 已提供：Event 静态嵌套类 — 作为 EventBus 的事件数据载体
// 注意：这是一个静态嵌套类 (static class)，不依赖外部类实例
// ============================================================
class EventBus {
    public static class Event {
        String type;
        String message;
        LocalDateTime timestamp;

        public Event(String type, String message) {
            this.type = type;
            this.message = message;
            this.timestamp = LocalDateTime.now();
        }

        public String summary() {
            return "[" + type + "] " + message;
        }
    }

    // ============================================================
    // 已提供：EventListener 接口（回调）
    // ============================================================
    public interface EventListener {
        void onEvent(Event e);
    }

    // ============================================================
    // 已提供：字段、注册、发送
    // ============================================================
    private EventListener emailListener;
    private EventListener smsListener;

    public void setEmailListener(EventListener l) { this.emailListener = l; }
    public void setSMSListener(EventListener l) { this.smsListener = l; }

    // TODO 1: 在 send 方法内使用匿名内部类 — 调用父类方法
    //
    // 要求：
    //   在 send(Event e, String channel) 方法内：
    //   1. 用匿名内部类覆盖 Event 的 summary() 方法
    //      - 格式变为："📨 [channel] [type] message"
    //      - 用 super.summary() 获取父类原来的字符串，再包装
    //      - 提示语法：
    //        Event enriched = new Event(e.type, e.message) {
    //            @Override
    //            public String summary() {
    //                return "📨 [...] " + super.summary();
    //            }
    //        };
    //   2. 根据 channel 分发：
    //      - "email" → 调用 emailListener.onEvent(enriched)
    //      - "sms"   → 调用 smsListener.onEvent(enriched)
    //
    public void send(Event e, String channel) {
        // === TODO 1: 匿名内部类重写 summary() + 分发 ===

        Event event = new Event (e.type, e.message) {
            @Override
            public String summary() {
                super.summary();
                return "📨 [" + channel + "] " + super.summary();
            }
        };

        if(channel.equals("email")) {
            emailListener.onEvent(event);
        } else if(channel.equals("sms")) {
            smsListener.onEvent(event);
        }
    }

    // TODO 2: 匿名内部类访问局部变量 — 理解 effectively final
    //
    // 要求：
    //   实现方法 Event cloneEvent(Event original)：
    //   1. 声明局部变量 String prefix = "[CLONE]"
    //   2. 用匿名内部类继承 Event，在 summary() 中使用 prefix
    //      → 输出格式："[CLONE] [type] message"
    //   3. 思考：为什么 prefix 不需要 final 修饰也能被匿名类访问？
    //      （答案写在 : effectively final 变量可以被匿名类捕获）
    //
    public Event cloneEvent(Event original) {
        // === TODO 2: 匿名内部类访问局部变量 ===

        String prefix = "[CLONE]";

        return new Event(original.type, original.message) {
            @Override
            public String summary() {
                return prefix + super.summary();
            }
        };
    }
}

// ============================================================
// EventBusDemo — 运行入口，完成 TODO 3-6
// ============================================================
public class EventBusDemo {
    public static void main(String[] args) {
        EventBus bus = new EventBus();

        // ============================================================
        // TODO 3: 匿名内部类实现 EventListener — email 回调
        // 要求：
        //   - onEvent 输出："📧 EMAIL 发送: " + e.summary()
        // ============================================================
        bus.setEmailListener(new EventBus.EventListener() {
                                 @Override
                                 public void onEvent(EventBus.Event e) {
                                     System.out.println("📧 EMAIL 发送: " + e.summary());
                                 }
                             }  // ← 用匿名内部类替换整个参数
        );
        // 提示：bus.setEmailListener(new EventBus.EventListener() { ... });


        // ============================================================
        // TODO 4: 匿名内部类实现 EventListener — SMS 回调
        // 要求：
        //   - onEvent 输出："📱 SMS 发送: " + e.summary()
        // ============================================================
        bus.setSMSListener(new EventBus.EventListener() {
                               @Override
                               public void onEvent(EventBus.Event e) {
                                   System.out.println("📱 SMS 发送: " + e.summary());
                               }
                           }  // ← 用匿名内部类替换整个参数
        );


        // --- 测试发送 ---
        System.out.println("=== TODO 1,3,4 测试: 事件分发 ===");
        bus.send(new EventBus.Event("ALERT", "服务器负载过高"), "email");
        bus.send(new EventBus.Event("NOTIFY", "您有新消息"), "sms");
        // 预期：
        // 📧 EMAIL 发送: 📨 [email] [ALERT] 服务器负载过高
        // 📱 SMS 发送: 📨 [sms] [NOTIFY] 您有新消息


        // ============================================================
        // TODO 5: 匿名内部类 — 作为方法参数 + 继承抽象类
        //
        // 创建一个 AutoLogger（继承自抽象类 Logger）并传给 bus
        //
        // Logger 是一个抽象类：
        //   abstract class Logger {
        //       abstract void log(EventBus.Event e);
        //   }
        //
        // 要求：
        //   用匿名内部类（new Logger() { ... }）创建 AutoLogger
        //   - log 方法输出："🪵 [AUTO] " + e.summary()
        //   然后调用 autoLogger.log(...) 验证
        // ============================================================
        System.out.println("\n=== TODO 5 测试: 匿名类继承抽象类 ===");

        // 定义 Logger 抽象类（局部，但匿名类需要继承它）
        abstract class Logger {
            abstract void log(EventBus.Event e);
        }

        // === TODO 5: 在这里用匿名内部类 new Logger() { ... } ===

        Logger autoLogger = new Logger() {
            @Override
            void log(EventBus.Event e) {
                System.out.println("🪵 [AUTO] " + e.summary());
            }
        };  // ← 替换


        if (autoLogger != null) {
            autoLogger.log(new EventBus.Event("INFO", "系统启动完成"));
        }


        // ============================================================
        // TODO 6: 局部内部类 — EventDescriber
        //
        // 在 main 方法的**末尾**（或其他位置）：
        //   定义一个局部内部类 EventDescriber
        //   包含方法: String describe(EventBus.Event e)
        //     → 返回："事件类型=<type>, 内容=<message>, 时间=<timestamp>"
        //
        //   然后创建实例并调用 describe 打印一个事件
        // ============================================================
        System.out.println("\n=== TODO 6 测试: 局部内部类 ===");

        // === TODO 6: 在这里定义 EventDescriber 局部内部类并使用 ===
        class EventDescriber {
            String describe(EventBus.Event e) {
                return "事件类型=<" + e.type + ">, 内容=<" + e.message + ">, 时间=<" + e.timestamp + ">";
            }
        }
        EventDescriber describer = new EventDescriber();
        System.out.println(describer.describe(new EventBus.Event("INFO", "输出日志")));
    }
}
