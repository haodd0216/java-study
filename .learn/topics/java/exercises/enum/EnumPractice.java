/**
 * 枚举 — Beginner 练习
 *
 * 会员卡类型升级：把 String 卡类型改成 enum，练习枚举字段、构造方法、
 * 方法、== 比较，以及从字符串解析枚举。
 *
 * 打开 README.md 阅读完整需求，完成下方 7 个 TODO。
 */
enum CardType {
    // ============================================================
    // TODO 1: 定义 3 个枚举常量
    // 要求：
    //   ONE_YEAR     -> "一年卡", 1
    //   THREE_YEARS  -> "三年卡", 3
    //   LIFETIME     -> "终身卡", -1
    // 提示：最后一个枚举常量后面要用分号 ;
    // ============================================================
    // TODO: 在这里定义枚举常量
    ONE_YEAR("一年卡", 1),
    THREE_YEARS("三年卡", 3),
    LIFETIME("终身卡", -1);

    private final String text;
    private final int years;

    // ============================================================
    // TODO 2: 编写枚举构造方法
    // 要求：接收 text 和 years，并赋值给上面的字段
    // 注意：枚举构造方法不能写 public
    // ============================================================
    CardType(String text, int years) {
        // TODO: 实现
        this.text = text;
        this.years = years;
    }

    // ============================================================
    // TODO 3: 返回中文名称
    // ============================================================
    String getText() {
        return this.text; // TODO: 实现
    }

    // ============================================================
    // TODO 4: 返回有效年限
    // ============================================================
    int getYears() {
        return this.years; // TODO: 实现
    }

    // ============================================================
    // TODO 5: 判断是否为终身卡
    // 要求：使用 == 比较枚举，不要用 text 字符串比较
    // ============================================================
    boolean isLifetime() {
        return this == LIFETIME; // TODO: 实现
    }

    // ============================================================
    // TODO 6: 根据中文名称解析枚举
    // 要求：
    //   fromText("一年卡")   -> CardType.ONE_YEAR
    //   fromText(" 三年卡 ") -> CardType.THREE_YEARS
    //   fromText("终身卡")   -> CardType.LIFETIME
    //   fromText("五年卡")   -> 抛出 IllegalArgumentException
    // 提示：遍历 CardType.values()
    // ============================================================
    static CardType fromText(String text) {
        CardType value = null;
        for (CardType cardType : CardType.values()) {
            if (cardType.text.equals(text)) value = cardType;
        }
        if (value == null) {
            throw new IllegalArgumentException("错误的枚举值");
        };
        return value; // TODO: 实现
    }
}

class Member2 {
    String name;
    String phone;

    // ============================================================
    // TODO 7-1: 把 cardType 的类型从 String 改成 CardType
    // ============================================================
    CardType cardType; // TODO: 改成 CardType

    // ============================================================
    // TODO 7-2: 构造方法里的 cardType 类型也要同步改成 CardType
    // ============================================================
    Member2(String name, String phone, CardType cardType) {
        this.name = name;
        this.phone = phone;
        this.cardType = cardType;
    }

    // ============================================================
    // TODO 7-3: 输出 "张三[13800138000] - 三年卡"
    // 提示：复用 cardType.getText()
    // ============================================================
    @Override
    public String toString() {
        return name + "[" + phone + "] - " + cardType.getText(); // TODO: 实现
    }
}

public class EnumPractice {
    public static void main(String[] args) {
        System.out.println("=== 枚举基础 ===");
        CardType type = CardType.THREE_YEARS;
        System.out.println(type.getText());
        System.out.println(type.getYears());
        System.out.println(CardType.LIFETIME.isLifetime());

        System.out.println("\n=== 解析会员 ===");
        Member2 zhang = parseMember("张三,13800138000, 三年卡");
        Member2 li = parseMember("李四,13911112222,终身卡");
        System.out.println(zhang);
        System.out.println(li);

        System.out.println("\n=== 非法输入 ===");
        try {
            parseMember("王五,13700000000,五年卡");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    static Member2 parseMember(String line) {
        String[] parts = line.split(",");
        String name = parts[0].trim();
        String phone = parts[1].trim();
        CardType cardType = CardType.fromText(parts[2]);
        return new Member2(name, phone, cardType);
    }
}
