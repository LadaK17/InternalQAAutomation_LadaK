package Cat;

// Конкретний клас, який реалізує абстрактний клас Cat
public class RegularCat extends Cat {
    // Конструктор
    public RegularCat(String name, int age) {
        super(name, age);
    }

    // Перевизначений метод для визначення звуку звичайного кота
    @Override
    public void makeSound() {
        System.out.println("Regular cat meows.");
    }
}
