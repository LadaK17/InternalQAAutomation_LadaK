package HomeWork7lessonUPDATEDtask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination implements Comparable<Pagination> {
    private int limit;
    private int offset;
    private int total;

    // Constructors, getters, setters, and other methods

    @Override
    public int compareTo(Pagination other) {
        // Implement your comparison logic here
        // Compare based on the 'total' field for example
        return Integer.compare(this.total, other.total);
    }

    // Rest of the class
}
