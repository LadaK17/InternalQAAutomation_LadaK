package HomeWork4lesson;

public class BubbleSortWith2For {
    public static void main(String[] args) {
        int[] array = {31, 432, 3, 42, 51, 999, -1};

        int n = array.length;

        // Внешній цикл для кількості ітерацій
        for (int i = 0; i < n - 1; i++) {

            // Внутрішній цикл для порівняння та обміну елементів
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Обмін елементів, якщо потрібно
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        // Виведення відсортованого масиву
        System.out.println("Відсортований масив: ");
        for (int value : array) {
            System.out.print(value + " ");
        }
    }
}
