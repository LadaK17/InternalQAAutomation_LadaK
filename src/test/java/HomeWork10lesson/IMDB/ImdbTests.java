package HomeWork10lesson.IMDB;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class ImdbTests {

    private final ImdbApi imdbApi = new ImdbApi();
    @DataProvider(name = "top100films")
    public Object[][] getTop100Films() throws IOException {
        List<FilmDetails> films = imdbApi.getTop100Films();
        Object[][] data = new Object[films.size()][1];

        for (int i = 0; i < films.size(); i++) {
            FilmDetails filmDetails = films.get(i);
//             Удаляем номер из названия фильма
            String filmNameWithoutNumber = filmDetails.getName().substring(3).trim();
            Map.Entry<String, String> filmEntry = new AbstractMap.SimpleEntry<>(filmNameWithoutNumber, filmDetails.getLink());
            data[i][0] = filmDetails;
        }

        return data;
    }


    @Test(dataProvider = "top100films")
    public void verifyFilmDetails(FilmDetails filmDetails) throws IOException {
        String filmName = filmDetails.getName();
        String filmLink = filmDetails.getLink();
        String releaseYear = filmDetails.getReleaseYear();
        String filmRating = filmDetails.getFilmRating();

        // Open film page
        Selenide.open(filmLink);

        // Verify film details
        verifyFilmDetailsOnPage(filmName, releaseYear, filmRating);
    }


    private void verifyFilmDetailsOnPage(String filmName, String expectedReleaseYear, String expectedFilmRating) {
        // Get film details from the page with waits
        Selenide.$("[data-testid='hero__primary-text']").shouldBe(Condition.exist);
        String filmTitle = Selenide.$("[data-testid='hero__primary-text']").getText();

        Selenide.$(".ipc-link.ipc-link--baseAlt.ipc-link--inherit-color").shouldBe(Condition.exist);
        String filmYear = Selenide.$(".ipc-link.ipc-link--baseAlt.ipc-link--inherit-color").getText();

        Selenide.$("[data-testid='hero-rating-bar__aggregate-rating']").shouldBe(Condition.exist);
        String filmRating = Selenide.$("[data-testid='hero-rating-bar__aggregate-rating']").getText();

        // Выполнение проверок
        Selenide.$("[data-testid='hero__primary-text']").shouldHave(Condition.text(filmName));
        Selenide.$x("/html/body/div[2]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/ul/li[1]/a").shouldHave(Condition.text(expectedReleaseYear));
        Selenide.$("[class=\"sc-bde20123-1 cMEQkK\"]").shouldHave(Condition.text(expectedFilmRating));

        // Вывод информации о фильме
        System.out.println("Actual Film Title: " + filmTitle);
        System.out.println("Actual Film Year: " + filmYear);
        System.out.println("Actual Film Rating: " + filmRating);
    }



    public static void printFilm() throws IOException {
        ImdbApi imdbApi = new ImdbApi();

        // Викликати метод getTop100Films() та отримати список фільмів
        List<FilmDetails> films = imdbApi.getTop100Films();

        // Вывести данные в консоль
        for (FilmDetails film : films) {
            System.out.println("Film Name: " + film.getName());
            System.out.println("Film Link: " + film.getLink());
            System.out.println("Film Year: " + film.getReleaseYear());
            System.out.println("Film Rating: " + film.getFilmRating());
            System.out.println("-------------------------");
        }
    }

}
