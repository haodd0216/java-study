package com.fuhao;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("小红");
        person.setAge(33);
        System.out.println("我叫" + person.getName() + "，今年" + person.getAge() + "岁");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        names.forEach(System.out::println);

        outer:
        for(int i = 1; i <= 100; i++) {
            if ((i % 7) == 0 && (i % 9) == 0) {
                System.out.println(i);
                break outer;
            }
            System.out.println(i);
        }
    }
}
