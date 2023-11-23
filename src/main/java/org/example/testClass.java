package org.example;

import com.github.javafaker.Faker;

public class testClass {
    public static void main(String[] args) {
        System.out.println(Faker.instance().cat().name().toUpperCase());
    }
}
