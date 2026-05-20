package com.fuhao.inner_class;

public class Outer {
    private String message;

    private class Inner implements MyInterface {

        @Override
        public void show(String firstName, String lastName) {
            System.out.println(firstName + " " + lastName);
        }

    }

    public MyInterface exposeShow() {
        return new Inner();
    }
}
