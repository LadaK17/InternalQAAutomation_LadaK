package HomeWork4lesson;
    public class RombPattern {

        public static void main(String[] args) {
            int rows = 5; // Кількість рядків у ромбі

            // Виведення верхньої частини ромба
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= rows - i; j++) {
                    System.out.print("  "); // Додаємо пробіли перед "*" у кожному рядку
                }
                for (int k = 1; k <= i * 2 - 1; k++) {
                    System.out.print("* ");
                }
                System.out.println();
            }

            // Виведення нижньої частини ромба
            for (int i = rows - 1; i >= 1; i--) {
                for (int j = 1; j <= rows - i; j++) {
                    System.out.print("  "); // Додаємо пробіли перед "*" у кожному рядку
                }
                for (int k = 1; k <= i * 2 - 1; k++) {
                    System.out.print("* ");
                }
                System.out.println();
            }
        }
    }
