# getter/setter 的合理使用

日期：2026-06-18
主题：Java / 面向对象编程 / 封装 / getter/setter 的合理使用

## 1. 定位：这个概念解决什么问题？

你已经学过封装，也理解了构造方法和封装的关系。

现在进入一个更细的问题：

> 字段设成 `private` 之后，到底要不要给它提供 getter 和 setter？

很多初学者会形成一个习惯：

```java
private String name;
private int age;
private String id;

public String getName() { return name; }
public void setName(String name) { this.name = name; }

public int getAge() { return age; }
public void setAge(int age) { this.age = age; }

public String getId() { return id; }
public void setId(String id) { this.id = id; }
```

这种写法看起来很“标准”，但不一定合理。

getter/setter 的合理使用核心是：

> 不是每个字段都必须暴露 getter/setter，而是根据对象的规则决定外部能不能读、能不能改、应该怎么改。

也就是说，getter/setter 不是自动生成的模板，而是对象对外开放的“访问权限”。

## 2. 类比：房间里的物品

可以把一个对象想象成一个房间。

房间里有很多东西：

- 身份证；
- 钱包；
- 空调温度；
- 灯的开关；
- 保险箱密码。

这些东西不是都应该被别人随便看、随便改。

比如：

- 身份证号：可以给别人看，但不能让别人改；
- 空调温度：可以调，但要限制范围，比如 16 到 30 度；
- 钱包余额：可以查看，但不能直接改成任意数字；
- 保险箱密码：可能既不应该直接查看，也不应该随便设置；
- 灯：不一定需要 `setLight(true)`，也可以提供 `turnOn()` 和 `turnOff()`。

对应到 Java：

- getter：允许外部读取；
- setter：允许外部修改；
- 没有 getter：外部不能直接知道这个字段；
- 没有 setter：外部不能直接修改这个字段；
- 业务方法：比 setter 更能表达真实动作。

## 3. 核心机制：getter/setter 是访问控制的一部分

最简单的封装形式是：

```java
public class Student {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

这表示：

- `name` 字段不能被外部直接访问；
- 外部可以通过 `getName()` 读取；
- 外部可以通过 `setName()` 修改。

但注意，这只是其中一种情况。

合理使用 getter/setter 时，可以分成四种访问模式。

## 4. 四种常见访问模式

### 4.1 可读可写：有 getter，也有 setter

适合那些外部确实需要读取，也允许修改的字段。

例如学生姓名：

```java
public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.equals("")) {
            System.out.println("姓名不能为空");
            return;
        }
        this.name = name;
    }
}
```

这里 `name` 可以读取，也可以修改，但修改时要校验。

### 4.2 只读：有 getter，没有 setter

适合创建后不应该被外部修改的字段。

例如学生学号：

```java
public class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
```

外部可以读取：

```java
System.out.println(student.getId());
```

但不能修改：

```java
student.setId("S002"); // 编译错误，因为没有这个方法
```

这正是构造方法和封装的配合：

- 构造方法设置 `id`；
- getter 允许读取；
- 不提供 setter，防止修改。

### 4.3 只写：有 setter，没有 getter

这种情况比较少见，但也存在。

例如密码字段：

```java
public class User {
    private String password;

    public void setPassword(String password) {
        if (password == null || password.length() < 6) {
            System.out.println("密码长度不能少于 6 位");
            return;
        }
        this.password = password;
    }

    public boolean checkPassword(String input) {
        return password != null && password.equals(input);
    }
}
```

这里不提供：

```java
public String getPassword()
```

因为密码不应该被外部直接读取。

外部如果要验证密码，应该调用：

```java
user.checkPassword("123456");
```

也就是说，有些字段可以被设置，但不应该被直接查看。

### 4.4 不直接读写：没有 getter，也没有 setter，只提供业务方法

这是封装更成熟的用法。

例如银行账户余额：

```java
public class BankAccount {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("存款金额必须大于 0");
            return;
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("取款金额必须大于 0");
            return;
        }
        if (amount > balance) {
            System.out.println("余额不足");
            return;
        }
        balance -= amount;
    }
}
```

这里有 `getBalance()`，但没有 `setBalance()`。

因为余额可以看，但不能随便设置。

它只能通过：

- `deposit`；
- `withdraw`；

来改变。

这比 `setBalance` 更合理。

## 5. setter 里应该做什么？

setter 不只是赋值。

如果字段有规则，setter 应该负责检查规则。

### 5.1 年龄

```java
public void setAge(int age) {
    if (age < 0 || age > 150) {
        System.out.println("年龄不合法");
        return;
    }
    this.age = age;
}
```

### 5.2 商品价格

```java
public void setPrice(double price) {
    if (price < 0) {
        System.out.println("价格不能小于 0");
        return;
    }
    this.price = price;
}
```

### 5.3 姓名

```java
public void setName(String name) {
    if (name == null || name.equals("")) {
        System.out.println("姓名不能为空");
        return;
    }
    this.name = name;
}
```

如果 setter 完全不校验：

```java
public void setAge(int age) {
    this.age = age;
}
```

那么它只是把 `private` 字段换成了“间接 public”，封装效果很弱。

## 6. getter 也不一定只是返回字段

getter 通常用于读取字段，但它也可以返回计算结果。

例如：

```java
public class User {
    private String username;
    private String nickname;

    public User(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }

    public String getDisplayName() {
        return nickname + "(" + username + ")";
    }
}
```

这里没有直接暴露 `username` 和 `nickname`，而是提供一个更适合外部使用的展示名称。

这说明：

> getter 的本质不是“必须返回某个字段”，而是“提供一个外部需要读取的信息”。

再比如矩形：

```java
public class Rectangle {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}
```

`area` 不一定需要作为字段保存，它可以通过 `width` 和 `height` 计算出来。

## 7. 什么时候不该提供 setter？

以下情况通常不该提供 setter。

### 7.1 创建后不应该变化

例如：

- 学号；
- 订单号；
- 身份证号；
- 账号编号。

这些字段更适合构造方法赋值，只提供 getter。

### 7.2 变化必须通过业务动作发生

例如余额、库存、积分。

不推荐：

```java
setBalance(1000);
setStock(20);
setPoints(500);
```

更推荐：

```java
deposit(1000);
withdraw(200);
sell(3);
restock(10);
addPoints(50);
usePoints(20);
```

因为业务方法更能表达“为什么变化”。

### 7.3 字段是内部实现细节

例如一个类内部用来缓存计算结果的字段：

```java
private int cachedValue;
```

外部不需要知道这个字段，也不应该修改它。

这种字段通常不提供 getter，也不提供 setter。

## 8. 什么时候不该提供 getter？

以下情况通常不该提供 getter。

### 8.1 敏感信息

比如密码：

```java
private String password;
```

不应该提供：

```java
public String getPassword() {
    return password;
}
```

更合理的是：

```java
public boolean checkPassword(String input) {
    return password.equals(input);
}
```

### 8.2 外部不需要知道的内部状态

例如：

```java
private boolean dirty;
private int retryCount;
```

如果只是类内部用来控制流程的状态，外部不需要读取，就不提供 getter。

### 8.3 容易导致外部依赖内部实现

如果外部直接依赖太多内部字段，以后类内部结构一改，外部代码也要跟着改。

封装的目标之一就是减少这种依赖。

## 9. JavaBean 规范和合理封装的区别

你以后可能会看到 JavaBean 规范。

JavaBean 常见写法是：

- 私有字段；
- 公共无参构造方法；
- getter/setter；
- 方法名符合 `getXxx`、`setXxx`。

例如：

```java
public class Student {
    private String name;
    private int age;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

JavaBean 这种写法常用于框架、工具、数据传输场景。

但从面向对象设计角度看，不是所有类都应该机械套 JavaBean。

区别是：

- JavaBean 更偏“规范格式”；
- 合理封装更关注“对象状态是否被正确保护”。

初学阶段你可以先掌握 JavaBean 写法，但要知道：真正做设计时，不要盲目给所有字段都生成 setter。

## 10. 一个完整例子：订单

```java
public class Order {
    private String orderNo;
    private String productName;
    private int quantity;
    private boolean paid;

    public Order(String orderNo, String productName, int quantity) {
        this.orderNo = orderNo;
        this.productName = productName;
        setQuantity(quantity);
        this.paid = false;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            System.out.println("购买数量必须大于 0");
            return;
        }
        this.quantity = quantity;
    }

    public void pay() {
        if (paid) {
            System.out.println("订单已经支付过了");
            return;
        }
        paid = true;
    }
}
```

这个例子里：

- `orderNo`：订单号，创建时确定，只读，没有 setter；
- `productName`：商品名，创建时确定，只读；
- `quantity`：数量，可以读取，也可以修改，但必须大于 0；
- `paid`：支付状态，可以读取，但不能随便 `setPaid(false)`；
- `pay()`：支付状态只能通过业务动作变成已支付。

为什么不提供：

```java
public void setPaid(boolean paid) {
    this.paid = paid;
}
```

因为这样外部可以随便把订单改成已支付或未支付，破坏业务规则。

## 11. 常见误区

### 误区一：private 字段必须配 getter/setter

不对。

`private` 的意思是“外部不能直接访问”，不是“必须换一种方式全部暴露出去”。

### 误区二：setter 只是赋值方法

不对。

setter 应该承担必要的校验。

如果不校验，它的封装意义会变弱。

### 误区三：所有字段都应该能被外部读取

不对。

有些字段是内部细节，外部不需要知道。

### 误区四：getter 一定对应一个字段

不一定。

getter 可以返回计算结果，比如 `getArea()`、`getDisplayName()`。

### 误区五：业务方法不如 setter 标准

不对。

很多时候业务方法比 setter 更好。

比如 `withdraw(100)` 比 `setBalance(900)` 更清楚，因为它表达了余额变化的原因。

## 12. 判断是否该写 getter/setter

写类时可以这样问自己：

1. 外部真的需要读取这个字段吗？如果不需要，不写 getter。
2. 外部真的应该修改这个字段吗？如果不应该，不写 setter。
3. 修改这个字段有没有规则？如果有，在 setter 或业务方法里校验。
4. 这个字段是不是创建后不该变？如果是，构造方法赋值 + getter + 无 setter。
5. 这个字段变化是不是有业务含义？如果有，优先考虑业务方法。
6. 这个字段是不是内部实现细节？如果是，getter/setter 都不写。

## 13. 苏格拉底式检查

请你思考下面几个问题：

1. 为什么不是所有 `private` 字段都应该提供 setter？
2. 学生学号 `id` 为什么通常只提供 getter，不提供 setter？
3. 银行账户的 `balance` 为什么可以提供 `getBalance()`，但不应该提供 `setBalance()`？
4. 密码字段为什么不适合提供 `getPassword()`？
5. `pay()` 和 `setPaid(true)` 相比，哪个更符合封装思想？为什么？
6. 如果一个字段只是类内部计算用的缓存，外部不需要知道，应不应该提供 getter？

## 14. 递归深入入口

如果继续往下学，可以进入这些子主题：

1. JavaBean 规范；
2. boolean 字段的 `isXxx()` 命名；
3. 业务方法替代 setter；
4. 只读对象；
5. 不可变对象；
6. DTO 与普通业务对象的区别；
7. getter 返回可变对象的风险。

一句话总结：

> getter/setter 不是机械生成的模板，而是对象对外开放读写权限的设计选择。