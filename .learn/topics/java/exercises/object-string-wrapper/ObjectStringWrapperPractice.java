import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Object、String 与包装类 — Intermediate 练习
 *
 * 会员办理中心：重写 equals/hashCode/toString，处理 CSV 字符串，避开包装类 == 陷阱。
 * 打开 README.md 阅读完整需求，完成下方 7 个 TODO。
 */
class Member {
    String name;
    String phone;
    String cardType;   // 卡类型，如 "三年卡"

    Member(String name, String phone, String cardType) {
        this.name = name;
        this.phone = phone;
        this.cardType = cardType;
    }

    // ============================================================
    // TODO 1: 重写 toString()
    // 要求：返回 "张三[13800138000]" 格式（姓名 + 手机号），不含卡类型
    // ============================================================
    @Override
    public String toString() {
        return this.name + "[" + this.phone + "]";   // TODO: 实现
    }

    // ============================================================
    // TODO 2: 重写 equals(Object)
    // 要求：姓名相同 且 手机号相同 → 同一个会员（不管 cardType）
    // 提示：标准三步 — ① this==obj ② null/类型 ③ 强转后按字段 equals
    // ============================================================
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member other) {
            return Objects.equals(this.name, other.name) && Objects.equals(this.phone, other.phone);
        }
        return false;   // TODO: 实现
    }

    // ============================================================
    // TODO 3: 重写 hashCode()
    // 要求：与 equals 用同样的字段 (name, phone)，保证铁律成立
    // 提示：return Objects.hash(name, phone);
    // ============================================================
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.phone);   // TODO: 实现
    }
}

class MemberCenter {
    List<Member> members = new ArrayList<>();

    // ============================================================
    // TODO 4: 会员办理 register(Member)
    // 要求：members 里已存在「内容相等」的会员 → 拒绝，返回 false
    //       否则加入列表，返回 true
    // 提示：直接用 members.contains(m)，contains 内部会调用你写的 equals
    // ============================================================
    boolean register(Member m) {
        if(this.members.contains(m)) {
            return false;
        }
        this.members.add(m);
        return true;   // TODO: 实现
    }

    // ============================================================
    // TODO 5: 会员卡片墙 formatMembers()
    // 要求：返回如下格式（注意换行、编号从 1 开始）：
    //   ———— 会员名单 ————
    //   1. 张三[13800138000]
    //   2. 李四[13911112222]
    //   ———— 共 2 人 ————
    // 提示：用 StringBuilder 逐行 append
    // ============================================================
    String formatMembers() {
        StringBuilder sb = new StringBuilder();
        sb.append("———— 会员名单 ————")
                .append("\n");
        for(int i = 0; i < members.size(); i++) {
            Member m = members.get(i);
            sb.append(i + 1)
                    .append(". ")
                    .append(m.name)
                    .append("[")
                    .append(m.phone)
                    .append("]")
                    .append("\n");
        }
        sb.append("———— 共")
                .append(members.size())
                .append("人 ————");
        return sb.toString();   // TODO: 实现
    }

    // ============================================================
    // TODO 6: 解析一行 CSV parseLine(String)
    // 要求：输入 "李四,13911112222,一年卡" → 返回 Member("李四","13911112222","一年卡")
    //       注意 parts 里的字段可能有首尾空格，要 trim
    // ============================================================
    Member parseLine(String line) {
        String[] parts = line.split(",");
        return new Member(parts[0].trim(), parts[1].trim(), parts[2].trim());   // TODO: 实现
    }
}

// ============================================================
// 入口：自检程序。不要修改 runTests()，只完成上面 TODO。
// ============================================================
public class ObjectStringWrapperPractice {

    // TODO 7: 正确比较两个 Integer 是否「值」相等
    // 要求：用 equals() 比较，不要用 ==（Integer 缓存 -128~127 是陷阱）
    static boolean comparePoints(Integer a, Integer b) {
        return a.equals(b);   // TODO: 实现
    }

    public static void main(String[] args) {
        MemberCenter center = new MemberCenter();

        // --- 模拟 CSV 导入（含重复行）---
        String[] csvLines = {
            "张三, 13800138000, 三年卡",
            "李四,13911112222,一年卡",
            "张三,13800138000, 三年卡"   // 重复
        };

        System.out.println("=== 解析 CSV ===");
        for (String line : csvLines) {
            Member m = center.parseLine(line);
            boolean ok = center.register(m);
            if (ok) {
                System.out.println("新增: " + m);          // 调用了你重写的 toString
            } else {
                System.out.println("重复会员被拒绝: " + m);
            }
        }

        // --- 会员卡片墙 ---
        System.out.println("\n=== 会员卡片墙 ===");
        System.out.println(center.formatMembers());

        // --- 包装类积分比较：观察 == 陷阱 ---
        System.out.println("\n=== 包装类积分比较 ===");
        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;
        System.out.println("a == b ? " + (a == b));        // 127: 缓存内, true
        System.out.println("c == d ? " + (c == d));        // 128: 超出缓存, false —— 陷阱!
        System.out.println("c equals d ? " + c.equals(d)); // true

        // 你写的 comparePoints 必须用 equals，这两行都该是 false
        Member zhang = new Member("张三", "13800138000", "三年卡");
        Member li = new Member("李四", "13911112222", "一年卡");
        System.out.println("张三积分 == 李四积分 ? " + comparePoints(100, 100));
        System.out.println("张三积分 equals 李四积分 ? " + comparePoints(200, 100));
    }
}
