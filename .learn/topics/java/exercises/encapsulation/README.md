# 练习：封装

## 练习前须知

1. 代码文件写在 `com/fuhao/encapsulation/` 目录下
2. 文件需要声明 `package com.fuhao.encapsulation;`
3. `.learn/topics/java/exercises/encapsulation/README.md` 只保存练习说明

---

## 练习 1：银行账户 BankAccount

### 目标

通过实现一个 `BankAccount` 类，练习：

- 字段私有化；
- 构造方法设置必要初始状态；
- 合理提供 getter；
- 避免不合理 setter；
- 使用业务方法保护对象状态。

### 文件

`com/fuhao/encapsulation/BankAccountPractice.java`

### 背景

你要设计一个简单的银行账户类。

银行账户有：

- 账号：`accountNo`
- 账户持有人：`ownerName`
- 余额：`balance`

### 要求

#### 1. 字段设计

`BankAccount` 应该有 3 个字段：

```java
private String accountNo;
private String ownerName;
private double balance;
```

#### 2. 构造方法

提供一个构造方法：

```java
public BankAccount(String accountNo, String ownerName)
```

要求：

- 创建对象时必须传入账号和持有人姓名；
- 初始余额由类自己设置为 `0`；
- 不要让外部通过构造方法随便传入余额。

#### 3. getter/setter

要求：

- `accountNo`：只提供 getter，不提供 setter；
- `ownerName`：提供 getter 和 setter；
- `balance`：只提供 getter，不提供 setter。

`setOwnerName` 需要校验：

- 不能是 `null`；
- 不能是空字符串 `""`。

#### 4. 业务方法

提供两个业务方法：

```java
public void deposit(double amount)
public void withdraw(double amount)
```

要求：

`deposit`：

- 存款金额必须大于 0；
- 合法时增加余额；
- 不合法时打印：`存款金额必须大于0`

`withdraw`：

- 取款金额必须大于 0；
- 取款金额不能大于余额；
- 合法时减少余额；
- 金额小于等于 0 时打印：`取款金额必须大于0`
- 余额不足时打印：`余额不足`

#### 5. main 方法测试

在 `main` 方法里测试：

1. 创建账户：账号 `A001`，持有人 `小明`；
2. 打印账号、持有人、余额；
3. 存款 `100`；
4. 取款 `30`；
5. 尝试取款 `1000`；
6. 尝试存款 `-10`；
7. 修改持有人为 `小王`；
8. 再次打印持有人和余额。

---

## 练习 2：商品库存 Product

### 目标

练习更进阶的封装设计：

- 有些字段只读，不允许修改；
- 有些字段可修改，但必须校验；
- 库存和销量不能直接 setter，只能通过业务方法变化；
- getter 可以返回计算结果。

### 文件

`com/fuhao/encapsulation/ProductInventoryPractice.java`

### 背景

你要设计一个商品类 `Product`。

商品有：

- 商品编号：`productId`
- 商品名称：`name`
- 单价：`price`
- 库存：`stock`
- 已售数量：`soldCount`

### 要求

#### 1. 字段设计

`Product` 应该有 5 个字段：

```java
private String productId;
private String name;
private double price;
private int stock;
private int soldCount;
```

#### 2. 构造方法

提供构造方法：

```java
public Product(String productId, String name, double price, int stock)
```

要求：

- 创建商品时必须传入商品编号、名称、价格和初始库存；
- `soldCount` 由类自己初始化为 `0`；
- `name` 不能为空；
- `price` 必须大于 `0`；
- `stock` 不能小于 `0`。

非法时可以打印提示并 `return`。

#### 3. getter/setter 设计

要求：

- `productId`：只提供 getter，不提供 setter；
- `name`：提供 getter 和 setter；
- `price`：提供 getter 和 setter；
- `stock`：只提供 getter，不提供 setter；
- `soldCount`：只提供 getter，不提供 setter。

#### 4. 业务方法

提供补货方法：

```java
public void restock(int count)
```

要求：

- `count` 必须大于 `0`；
- 合法时增加库存；
- 不合法时打印：`补货数量必须大于0`

提供销售方法：

```java
public void sell(int count)
```

要求：

- `count` 必须大于 `0`；
- `count` 不能大于当前库存；
- 合法时减少库存，并增加已售数量；
- `count <= 0` 时打印：`销售数量必须大于0`
- 库存不足时打印：`库存不足`

提供销售额方法：

```java
public double getSalesAmount()
```

要求：

- 返回 `price * soldCount`；
- 不需要定义 `salesAmount` 字段。

#### 5. main 方法测试

在 `main` 方法里测试：

1. 创建商品：编号 `P001`，名称 `Java练习本`，价格 `19.9`，库存 `10`；
2. 打印商品编号、名称、价格、库存、已售数量、销售额；
3. 销售 `3` 件；
4. 补货 `5` 件；
5. 尝试销售 `20` 件；
6. 尝试补货 `0` 件；
7. 修改价格为 `25.5`；
8. 再次打印价格、库存、已售数量、销售额。

## 预期重点

这次练习的核心不是多写 getter/setter，而是判断：

> 字段变化是否应该由外部直接设置，还是应该通过业务动作发生？

完成后告诉我“写好了”，我会读取代码并运行检查。