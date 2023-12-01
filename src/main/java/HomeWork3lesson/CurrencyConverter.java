package HomeWork3lesson;
public class CurrencyConverter {

    private double commission;
    private double exchangeRate;

    // Конструктор для ініціалізації полів при створенні об'єкта класу
    public CurrencyConverter(double commission, double exchangeRate) {
        this.commission = commission;
        this.exchangeRate = exchangeRate;
    }

    // Метод для конвертації долара в гривні
    private double convertUsdToUah(double amountInUsd) {
        return amountInUsd * exchangeRate;
    }

    // Метод для обрахунку комісії
    private double calculateCommission(double amountInUah) {
        return amountInUah * commission; // Комісія від суми в гривні
    }

    // Публічний метод для обрахунку суми до виплати
    public double calculateTotalAmount(double amountInUsd) {
        double amountInUah = convertUsdToUah(amountInUsd);
        double commissionAmount = calculateCommission(amountInUah);
        return amountInUah - commissionAmount;
    }
}
