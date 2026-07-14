/**
 * 类与对象 Intermediate 练习 — ClassroomPractice
 *
 * 完成 6 个 TODO，覆盖构造器重载、this、引用语义、对象交互、静态字段。
 */

// ============================================================
// TODO 1-5: 完成 Student 类
// ============================================================
class Student {
    // --- 实例字段 ---
    String name;
    int score;

    // --- 静态字段 ---
    // TODO 4: 定义一个 static int studentCount，初始值 0
    static int studentCount = 0;

    // ============================================================
    // TODO 1: 无参构造器 → 调用有参构造器
    // 要求：
    //   - 用 this("未命名", 0) 调用下面的有参构造器
    //   - 提示：this(参数) 必须在构造器的**第一行**
    // ============================================================
    Student() {
        // === 在这里用 this(...) 调用有参构造器 ===
        this("未命名", 0);
    }

    // ============================================================
    // TODO 2: 有参构造器
    // 要求：
    //   - 用 this.name = name; this.score = score; 初始化字段
    //   - （TODO 5 关联）在这个构造器中让 studentCount + 1
    // ============================================================
    Student(String name, int score) {
        // === 初始化字段 ===
        this.name = name;
        this.score = score;

        // TODO 5: 构造器中递增 studentCount（提示：studentCount++）
        studentCount++;
    }

    // ============================================================
    // TODO 3: 比较成绩 — 对象交互
    // 要求：
    //   - 返回 "XXX 比 YYY 高"
    //   - 如果 this.score > other.score → "this.name 比 other.name 高"
    //   - 如果 this.score < other.score → "other.name 比 this.name 高"
    //   - 如果相等 → "this.name 和 other.name 一样高"
    //
    // 提示：通过 other.name / other.score 访问参数对象的字段
    // ============================================================
    String compareScore(Student other) {
        // === 在这里实现比较逻辑 ===
        if(this.score > other.score) {
            return this.name + " 比 " + other.name + " 高";
        } else if(this.score < other.score) {
            return other.name + " 比 " + this.name + " 高";
        } else {
            return this.name + " 和 " + other.name + " 一样高";
        }
    }

    // 获取静态字段（可选：如果 studentCount 是 private 的则用）
    static int getStudentCount() {
        return studentCount;  // 修改为你定义的静态字段名
    }
}

// ============================================================
// TODO 6: 完成 Classroom 类
// 要求：
//   - 有一个 Student[] students 字段（数组，最大容量 10）
//   - 有一个 int count 字段记录当前学生数
//   - addStudent(Student s) 方法：把 s 加入数组，count++
//   - printAll() 方法：遍历 students 数组，打印每个学生的 name 和 score
//   - 只打印实际添加的学生（遍历到 count），不要打印 null
// ============================================================
class Classroom {
    // === 在这里写字段和两个方法 ===
    Student[] students = new Student[10];
    int count = 0;

     void addStudent(Student s) {
         students[count++] = s;
     }

     void printAll() {
         for(int i = 0; i < count; i++) {
             System.out.println("姓名：" + students[i].name + ", " + "分数：" + students[i].score);
         }
     }
}

// ============================================================
// 运行入口
// ============================================================
public class ClassroomPractice {
    public static void main(String[] args) {
        System.out.println("=== 测试 TODO 1&2: 构造器重载 ===");
        Student s1 = new Student("Alice", 92);
        Student s2 = new Student("Bob", 85);
        Student s3 = new Student();  // 应调用无参构造器

        System.out.println("s3 name: " + s3.name + ", score: " + s3.score);
        // 预期: s3 name: 未命名, score: 0

        System.out.println("\n=== 测试 TODO 3: 对象交互 ===");
        System.out.println(s1.compareScore(s2));  // Alice 比 Bob 高
        System.out.println(s2.compareScore(s1));  // Alice 比 Bob 高

        System.out.println("\n=== 测试 TODO 4&5: 静态字段 ===");
        System.out.println("已创建学生数: " + Student.getStudentCount());
        // 预期: 3

        System.out.println("\n=== 测试 TODO 6: Classroom 类 ===");
        Classroom classroom = new Classroom();
        classroom.addStudent(s1);
        classroom.addStudent(s2);
        classroom.addStudent(new Student("Charlie", 78));
        classroom.printAll();
        // 预期: 三行输出，每行一个学生的 name + score
    }
}
