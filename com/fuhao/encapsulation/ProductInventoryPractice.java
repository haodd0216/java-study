package com.fuhao.encapsulation;

public class ProductInventoryPractice {
    public static void main(String[] args) {
        // 在这里创建 Product 对象并测试
        Product product = new Product("P001", "Java练习本", 19.9, 10);
        System.out.println(product.getProductId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getStock());
        System.out.println(product.getSoldCount());
        System.out.println(product.getSalesAmount());
        product.sell(3);
        product.restock(5);
        product.sell(20);
        product.restock(0);
        product.setPrice(25.5);
        System.out.println(product.getPrice());
        System.out.println(product.getStock());
        System.out.println(product.getSoldCount());
        System.out.println(product.getSalesAmount());
    }
}

class Product {
    // TODO: 定义 private 字段：productId、name、price、stock、soldCount
    private final String productId;
    private String name;
    private double price;
    private int stock;
    private int soldCount;
    // TODO: 编写构造方法：传入 productId、name、price、stock，soldCount 初始为 0
    public Product(String productId, String name, double price, int stock) {
        this.productId = productId;
        this.setName(name);
        this.setPrice(price);
        this.stock = stock;
        this.soldCount = 0;
    }
    // TODO: productId 只提供 getter，不提供 setter
    public String getProductId() {
        return this.productId;
    }
    // TODO: name 提供 getter 和 setter，setter 校验 null 和空字符串
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        if(name == null || name.isEmpty()) {
            System.out.println("创建商品时必须填写商品名称");
            return;
        }
        this.name = name;
    }
    // TODO: price 提供 getter 和 setter，setter 校验价格必须大于 0
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        if(price <= 0) {
            System.out.println("商品价格必须大于0");
            return;
        }
        this.price = price;
    }
    // TODO: stock 只提供 getter，不提供 setter
    public int getStock() {
        return this.stock;
    }
    // TODO: soldCount 只提供 getter，不提供 setter
    public int getSoldCount() {
        return this.soldCount;
    }
    // TODO: 编写 restock 方法，用于补货
    public void restock(int count) {
        if(count <= 0) {
            System.out.println("补货数量必须大于0");
            return;
        }
        this.stock += count;
    }
    // TODO: 编写 sell 方法，用于销售商品
    public void sell(int count) {
        if(count <= 0) {
            System.out.println("销售数量必须大于0");
            return;
        }
        if(count > this.stock) {
            System.out.println("库存不足");
            return;
        }
        this.stock -= count;
        this.soldCount += count;
    }
    // TODO: 编写 getSalesAmount 方法，返回销售总额：price * soldCount
    public double getSalesAmount() {
        return this.price * this.soldCount;
    }
}
