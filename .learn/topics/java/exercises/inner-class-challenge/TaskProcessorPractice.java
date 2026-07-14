/**
 * 内部类与匿名类综合练习 — TaskProcessorPractice
 *
 * 完成 5 个 TODO，覆盖四种内部类的核心用法。
 */

// ============================================================
// TaskHandler 接口 — 所有任务的统一入口
// ============================================================
interface TaskHandler {
    /** 返回任务类型名称 */
    String getType();
    /** 执行任务内容 */
    void execute(String content);
}

// ============================================================
// TaskProcessorPractice — 外部类（你需要完成这个类）
// ============================================================
public class TaskProcessorPractice {
    private String processorName;

    public TaskProcessorPractice(String processorName) {
        this.processorName = processorName;
    }

    // TODO 1: 静态嵌套类 — TaskStatistics
    // 要求：
    //   - 用 static class 定义
    //   - 包含一个 static int totalTasks 字段
    //   - 包含一个 static void increment() 方法，每次调用 totalTasks + 1
    //   - 包含一个 static int getTotal() 方法，返回 totalTasks
    //
    // 提示：静态嵌套类不需要外部类实例，直接 new TaskProcessorPractice.TaskStatistics()
    //
    // === 在这里写你的 TaskStatistics 静态嵌套类 ===
    public static class TaskStatistics {
        static int totalTasks = 0;

        public static void increment() {
            totalTasks++;
        }

        public static int getTotal() {
            return totalTasks;
        }
    }

    // TODO 2: 成员内部类 — TaskLogger
    // 要求：
    //   - 定义 class TaskLogger（不加 static）
    //   - 包含一个 void log(String taskType, String result) 方法
    //   - log 方法输出格式："[processorName] taskType: result"
    //     （其中 processorName 是外部类的 private 字段 — 成员内部类可以直接访问！）
    //
    // 提示：成员内部类可以访问外部类的所有成员，包括 private
    // 创建方式：processor.new TaskLogger()
    //
    // === 在这里写你的 TaskLogger 成员内部类 ===

    class TaskLogger {
        void log(String taskType, String result) {
            System.out.println("[" + processorName + "]" + taskType + ": " + result);
        }
    }


    // TODO 3: 局部内部类 — 在 processTask 方法内定义
    // 要求：
    //   - 在 processTask 方法**内部**定义一个局部内部类 ResultFormatter
    //   - ResultFormatter 有一个 String format(String type, String content) 方法
    //   - format 返回格式化字符串："✅ [type] 任务「content」— 处理完成"
    //   - 在 processTask 方法内创建 ResultFormatter 对象并调用 format
    //
    public void processTask(TaskHandler handler, String content) {
        // === 在这里定义你的局部内部类 ResultFormatter ===
        class ResultFormatter {
            String format(String type, String content) {
                return "[" + type + "] 任务「" + content + "」- 处理完成";
            }
        }

        ResultFormatter resultFormatter = new ResultFormatter();
        // === 用 ResultFormatter 格式化输出 ===
        System.out.println(resultFormatter.format(handler.getType(), content));
    }

    // ============================================================
    // 运行入口 — TODO 4 & 5 在这里
    // ============================================================
    public static void main(String[] args) {
        TaskProcessorPractice processor = new TaskProcessorPractice("任务处理器v1");

        // --- 测试 TODO 1: 静态嵌套类 ---
        // 直接通过外部类名访问，不需要外部类实例
        System.out.println("--- 静态嵌套类测试 ---");
        TaskProcessorPractice.TaskStatistics stats = new TaskProcessorPractice.TaskStatistics();
        // === 调用 TaskStatistics.increment() 和 getTotal()，并打印结果 ===
        TaskStatistics.increment();
        TaskStatistics.increment();
        System.out.println(TaskStatistics.getTotal());

        // --- 测试 TODO 2: 成员内部类 ---
        System.out.println("--- 成员内部类测试 ---");

        // === 通过 processor 对象创建 TaskLogger，调用 log 方法 ===
        TaskProcessorPractice.TaskLogger logger = processor.new TaskLogger();
        logger.log("execute", "success");

        // --- 测试 TODO 3: 局部内部类 ---
        System.out.println("--- 局部内部类测试 ---");

        // === 调用 processor.processTask(...) ===
        TaskHandler exportHandler = new TaskHandler() {
            @Override
            public String getType() {
                return "导出";
            }

            @Override
            public void execute(String content) {
                System.out.println("正在导出: " + content);
            }
        };
        processor.processTask(exportHandler, "export.pdf");

        // ============================================================
        // TODO 4: 匿名内部类 — 实现 TaskHandler，表示「打印任务」
        // 要求：
        //   - getType() 返回 "打印"
        //   - execute(content) 输出 "🖨️ 正在打印: " + content
        //
        // 提示语法：new TaskHandler() { /* 实现方法 */ }
        // ============================================================
        System.out.println("--- 匿名内部类测试 ---");

        TaskHandler printHandler = new TaskHandler() {
            @Override
            public String getType() {
                return "打印";
            }

            @Override
            public void execute(String content) {
                System.out.println("🖨️ 正在打印: " + content);
            }
        };  // ← 用匿名内部类替换这个 null


        // ============================================================
        // TODO 5: 匿名内部类 — 实现 TaskHandler，表示「计算任务」
        // 要求：
        //   - getType() 返回 "计算"
        //   - execute(content) 输出 "🧮 正在计算: " + content
        // ============================================================

        TaskHandler calcHandler = new TaskHandler() {
            @Override
            public String getType() {
                return "计算";
            }

            @Override
            public void execute(String content) {
                System.out.println("🧮 正在计算: " + content);
            }
        };  // ← 用匿名内部类替换这个 null


        // --- 调用两个 handler，验证所有 TODO ---
        if (printHandler != null) {
            processor.processTask(printHandler, "report.pdf");
        }
        if (calcHandler != null) {
            processor.processTask(calcHandler, "1+2+3+...+100");
        }
    }
}
