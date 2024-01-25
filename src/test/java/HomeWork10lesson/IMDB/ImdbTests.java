package HomeWork10lesson.IMDB;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
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
            // Удаляем номер из названия фильма
            String filmNameWithoutNumber = filmDetails.getName().substring(3).trim();
            Map.Entry<String, String> filmEntry = new AbstractMap.SimpleEntry<>(filmNameWithoutNumber, filmDetails.getLink());
            data[i][0] = filmEntry;
        }

        return data;
    }




    @Test(dataProvider = "top100films")
    public void verifyFilmDetails(Map.Entry<String, String> filmEntry) {
        String filmName = filmEntry.getKey();
        String filmLink = filmEntry.getValue();

        // Open film page
        Selenide.open(filmLink);

        // Verify film details
        verifyFilmDetailsOnPage(filmName);
    }

    private void verifyFilmDetailsOnPage(String filmName) {
        // Get film details from the page with waits
        Selenide.$("[data-testid='hero__primary-text']").shouldBe(Condition.exist);
        String filmTitle = Selenide.$("[data-testid='hero__primary-text']").getText();

        Selenide.$x("/html/body/div[2]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/ul/li[1]/a")
                .shouldBe(Condition.exist);
        String filmYear = Selenide.$x("/html/body/div[2]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/ul/li[1]/a")
                .getText();

        Selenide.$("[class=\"sc-bde20123-1 cMEQkK\"]").shouldBe(Condition.exist);
        String filmRating = Selenide.$("[class=\"sc-bde20123-1 cMEQkK\"]").getText();

        // Perform verifications
        Selenide.$("[data-testid='hero__primary-text']").shouldHave(Condition.text(filmName));
        Selenide.$x("/html/body/div[2]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/ul/li[1]/a")
                .shouldHave(Condition.text(filmYear));
        Selenide.$("[class=\"sc-bde20123-1 cMEQkK\"]").shouldHave(Condition.text(filmRating));

        // Print film details
        System.out.println("Actual Film Title: " + filmTitle);
        System.out.println("Actual Film Year: " + filmYear);
        System.out.println("Actual Film Rating: " + filmRating);
    }


}