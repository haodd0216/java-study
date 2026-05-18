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
    }
}
