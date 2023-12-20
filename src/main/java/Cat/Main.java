package Cat;

public class Main {
    public static void main(String[] args) {
        Cat regularCat = new RegularCat("Whiskers", 3);
        Cat persianCat = new PersianCat("Fluffy", 2);

        // Динамічний поліморфізм - виклик методу makeSound() для об'єктів різних типів котів
        regularCat.makeSound();
        persianCat.makeSound();
    }
}

