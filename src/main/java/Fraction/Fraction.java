package Fraction;

public class Fraction {
    private int numerator;
    private int denominator;

    // Конструктор класу
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        simplify(); // Спростимо дріб при створенні об'єкта
    }

    // Перевизначення методу equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Fraction otherFraction = (Fraction) obj;

        // Порівняння спрощених дробів
        return this.numerator == otherFraction.numerator && this.denominator == otherFraction.denominator;
    }

    // Метод для спрощення дробу
    private void simplify() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;

        if (denominator < 0) {
            // Забезпечення, що знаменник завжди додатній
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    // Метод для знаходження найбільшого спільного дільника
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Метод для виведення дробу у вигляді рядка
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}

