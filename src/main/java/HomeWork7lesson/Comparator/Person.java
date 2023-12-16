package HomeWork7lesson.Comparator;
public class Person implements Comparable<Person> {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person p) {
        return Integer.compare(this.age, p.age);
    }
    @Override
    public String toString() {
        return "Name is: " + this.name + " Age is: " + this.age;
    }
}
