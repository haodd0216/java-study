package com.fuhao.inner_class;

public class Main {
    public static void main(String[] args) {
        Outer1 outer = new Outer1("xiaoming");
        Outer1.Inner inner = outer.new Inner();
        inner.hello();
        inner.setName("xiaohong");
        inner.hello();

        Outer outer1 = new Outer();

        MyInterface inter1 = outer1.exposeShow();
        inter1.show("xiao", "lan");
    }
}

class Outer1 {
    private String name;

    Outer1(String name) {
        this.name = name;
    }

    class Inner {
        void hello() {
            System.out.println("Hello, " + Outer1.this.name);
        }

        void setName(String name) {
            Outer1.this.name = name;
        }
    }
}
