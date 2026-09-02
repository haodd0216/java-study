# Practice Session - 2026-09-02

## Concept Practiced
- Concept: 常用核心 API / Object、String 与包装类
- Difficulty: Intermediate
- Exercise Name: 会员办理中心 (MemberCenter)

## User's Submitted Code

```java
// TODO 1: toString
@Override
public String toString() {
    return this.name + "[" + this.phone + "]";
}

// TODO 2: equals
@Override
public boolean equals(Object obj) {
    if(obj instanceof Member) {
        return this.name.equals(((Member) obj).name) && this.phone.equals(((Member) obj).phone);
    }
    return false;
}

// TODO 3: hashCode
@Override
public int hashCode() {
    return Objects.hash(this.name, this.phone);
}

// TODO 4: register
boolean register(Member m) {
    if(this.members.contains(m)) {
        return false;
    }
    this.members.add(m);
    return true;
}

// TODO 5: formatMembers —— 编号从 i 开始(规格要 i+1)，页脚 "共2人"(规格要 "———— 共 2 人 ————")
String formatMembers() {
    StringBuilder sb = new StringBuilder();
    sb.append("———— 会员名单 ————").append("\n");
    for(int i = 0; i < members.size(); i++) {
        Member m = members.get(i);
        sb.append(i).append(". ").append(m.name).append("[").append(m.phone).append("]").append("\n");
    }
    sb.append("共").append(members.size()).append("人");
    return sb.toString();
}

// TODO 6: parseLine
Member parseLine(String line) {
    String[] parts = line.split(",");
    return new Member(parts[0].trim(), parts[1].trim(), parts[2].trim());
}

// TODO 7: comparePoints
static boolean comparePoints(Integer a, Integer b) {
    return a.equals(b);
}
```

## AI Feedback

**Acknowledge:** 7 个 TODO 的核心逻辑全对——toString 格式正确、equals 忽略 cardType 语义正确、hashCode 用 Objects.hash 且与 equals 字段一致(铁律成立)、register 用 contains 自动判重、parseLine 记得 trim、comparePoints 用 equals 避开了 Integer 缓存陷阱。概念层面全部打通。

**改进点:**
1. TODO 5 formatMembers:编号从 0 开始(规格要 1.)、页脚格式不对(规格要 `———— 共 2 人 ————`),且手动重复拼接 name+phone,建议复用重写后的 `toString()`(避免日后多字段时改多处)。
2. equals 健壮性:字段为 null 会 NPE,建议 `Objects.equals`;两次强转可存局部变量 `Member other = (Member) obj`。
3. 提醒 equals/hashCode 必须同步更新(夫妻字段);ArrayList.contains 只用 equals,hashCode 是给 HashMap/HashSet 用的。

**澄清:** main 里最后两行输出标签含 "==" 是 starter 命名误导,实际调用的是 comparePoints(equals 实现),输出 true/false 正确。

## Assessment
- Understanding: Solid
- Status update: in_progress → needs_practice (待改 TODO5 格式 + 回答复用 toString 的问题后冲刺 mastered)
- confidence: 0.40 → 0.45
- practice_count: 0 → 1

---

## Round 2 — 复评 (同一天)

## 用户修改后的关键代码

```java
// equals — 升级为 pattern matching + Objects.equals(null 安全)
@Override
public boolean equals(Object obj) {
    if(obj instanceof Member other) {
        return Objects.equals(this.name, other.name) && Objects.equals(this.phone, other.phone);
    }
    return false;
}

// TODO 5 formatMembers — 编号改 i+1，页脚补上 ————
sb.append(i + 1).append(". ").append(m.name).append("[").append(m.phone).append("]").append("\n");
...
sb.append("———— 共").append(members.size()).append("人 ————");
```

## AI 反馈 (Round 2)

1. 运行通过:编号从 1 开始 ✓、重复拒绝 ✓、Integer 陷阱复现 ✓。
2. 表扬:主动采用了 Objects.equals(null 安全) + Java 16 pattern matching `instanceof Member other`,省去重复强转,equals 达到教科书写法。
3. 思考题订正:核心要点是"单一数据源(DRY)"——会员显示格式应只存在于 Member.toString(),formatMembers 应 `sb.append(m)` 复用 toString,这样将来改显示格式(如加卡类型)只改一处;用户原答聚焦在 StringBuilder 可扩展性,方向相关但未点中 DRY 复用。
4. 反问留作业:方案 A(手拼字段)改格式需改多处,漏改会导致多打印位置不一致。
5. 微小瑕疵(未阻塞):页脚 "共2人" 与规格 "共 2 人" 差空格,纯外观。

## Assessment (Round 2)
- Understanding: Excellent
- Status update: needs_practice → mastered
- confidence: 0.45 → 0.72 (+0.27, 两次优秀提交 + 主动吸收 null 安全/pattern matching)
- practice_count: 1 → 2

