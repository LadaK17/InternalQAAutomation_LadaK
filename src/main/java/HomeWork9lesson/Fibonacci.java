package HomeWork9lesson;
import java.util.stream.Stream;

public class Fibonacci {

    public static void main(String[] args) {
        calculateInfiniteFibonacci();
    }

    public static void calculateInfiniteFibonacci() {
        Stream.iterate(new int[]{0, 1}, fibPair -> new int[]{fibPair[1], fibPair[0] + fibPair[1]})
                .map(pair -> {
                    System.out.print(pair[0] + " ");
                    return pair[0];
                })
                .limit(15)  // обмеження кількості виведених чисел
                .reduce((first, second) -> second);
    }
}
