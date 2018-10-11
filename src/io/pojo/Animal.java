package io.pojo;

import java.io.Serializable;

public class Animal implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private double weight = 10.0;

	public Animal(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Animal{" +
						"name='" + name + '\'' +
						", weight=" + weight +
						'}';
	}
}
