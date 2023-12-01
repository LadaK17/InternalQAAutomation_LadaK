package HomeWork3lesson;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Створення об'єкта Scanner для зчитування введення користувача
        Scanner scanner = new Scanner(System.in);

        // Встановлення фіксованих значень для комісії та обмінного курсу
        double commission = 0.01; // 1%
        double exchangeRate = 36.55;

        // Створення об'єкта CurrencyConverter
        CurrencyConverter converter = new CurrencyConverter(commission, exchangeRate);

        // Зчитування суми в доларах з клавіатури
        System.out.print("Введіть суму в доларах: ");
        double amountInUsd = scanner.nextDouble();

        // Обчислення та виведення результату на екран
        double totalAmount = converter.calculateTotalAmount(amountInUsd);
        System.out.println("Сума до виплати в UAH (з урахуванням комісії): " + totalAmount);

        // Закриття Scanner
        scanner.close();
    }
}