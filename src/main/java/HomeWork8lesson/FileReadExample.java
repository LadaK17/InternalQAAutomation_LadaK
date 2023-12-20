package HomeWork8lesson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReadExample {

    public static void main(String[] args) {
        FileReadExample example = new FileReadExample();
        example.myHomework();
    }

    public void myHomework() {
        try {
            // Шлях до вашого файлу
            // Шлях до вашого каталогу
            String filePath = "/Users/ladaharcenko/Desktop/Domashka8";


            // Читання вмісту файлу
            String content = readFile(filePath);

            // Вивід вмісту файлу у консоль
            System.out.println("File content:\n" + content);
        } catch (IOException e) {
            // Вивід стек-трейсу у консоль
            e.printStackTrace();

            // Вивід користувацького повідомлення про помилку
            System.err.println("Помилка при читанні файлу: " + e.getMessage());


            // Перекидання користувацької неперевіряємої помилки
            throw new CustomUncheckedException("Помилка при читанні файлу", e);
        }
    }

    private String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readString(path);
    }
}
