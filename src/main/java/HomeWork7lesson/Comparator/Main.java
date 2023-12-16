package HomeWork7lesson.Comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        List<Person> list = new ArrayList<>();

        list.add(new Person("Mykola", 31));
        list.add(new Person("Oleksandr", 33));
        list.add(new Person("Mykyta", 21));

        // Створення та використання Comparator для сортування за віком
        Comparator<Person> ageComparator = Comparator.comparingInt(Person::getAge);
        Collections.sort(list, ageComparator);

        // Виведення результату
        list.forEach(System.out::println);
    }
}
