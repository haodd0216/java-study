package com.fuhao.oop_static;

public class Person {

	// TODO
	static int count = 0;
	String name;

	public Person(String name) {
		count++;
		this.name = name;
	}

	public static int getCount() {
		return count;
	}
}
