package Cat;
// Абстрактний клас, який представляє кота
public abstract class Cat {
    // Поля (дані)
    private String name;
    private int age;

    // Конструктор
    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Абстрактний метод, який повинен бути перевизначений в підкласах
    public abstract void makeSound();
    }


