package HomeWork5lesson;
import java.util.Scanner;

public class FractionTest {
    public static void main(String[] args) {
        // Створення об'єктів Fraction з консолі за допомогою Scanner
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть перший дріб (у вигляді a/b): ");
        Fraction fraction1 = createFractionFromInput(scanner.nextLine());

        System.out.print("Введіть другий дріб (у вигляді a/b): ");
        Fraction fraction2 = createFractionFromInput(scanner.nextLine());

        // Порівняння та виведення результату
        System.out.println("Результат порівняння дробів: " + fraction1.equals(fraction2));

        // Закриття Scanner
        scanner.close();
    }

    // Метод для створення об'єкта Fraction з рядка введення
    private static Fraction createFractionFromInput(String input) {
        String[] parts = input.split("/");
        int numerator = Integer.parseInt(parts[0]);
        int denominator = Integer.parseInt(parts[1]);
        return new Fraction(numerator, denominator);
    }
}

