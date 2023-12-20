package HomeWork8lesson;

public class CustomUncheckedException extends RuntimeException {
    public CustomUncheckedException(String message, Throwable cause) {
        super(message, cause);
    }
}

