package Cat;

// Конкретний клас, який реалізує абстрактний клас Cat
public class PersianCat extends Cat {
    // Конструктор
    public PersianCat(String name, int age) {
        super(name, age);
    }

    // Перевизначений метод для визначення звуку перського кота
    @Override
    public void makeSound() {
        System.out.println("Persian cat meows softly.");
    }
}
