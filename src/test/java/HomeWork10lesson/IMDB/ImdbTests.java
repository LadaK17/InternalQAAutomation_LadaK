package HomeWork10lesson.IMDB;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.Assert;
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
        Object[][] data = new Object[films.size()][2];

        for (int i = 0; i < films.size(); i++) {
            FilmDetails filmDetails = films.get(i);
            String filmNameWithoutNumber = filmDetails.getName().length() > 3
                    ? filmDetails.getName().substring(3).trim()
                    : filmDetails.getName().trim();

            Map.Entry<String, String> filmEntry = new AbstractMap.SimpleEntry<>(filmNameWithoutNumber, filmDetails.getLink());
            String releaseYearFromDataProvider = filmDetails.getReleaseYear();

            data[i][0] = filmEntry;
            data[i][1] = releaseYearFromDataProvider;
        }

        return data;
    }

@Test(dataProvider = "top100films")
public void verifyFilmDetails(Map.Entry<String, String> filmEntry, String releaseYearFromDataProvider) throws IOException {
    String filmName = filmEntry.getKey();
    String filmLink = filmEntry.getValue();
    List<FilmDetails> top100Films = imdbApi.getTop100Films();

    // Iterate over the films and find the one with the matching link
    FilmDetails filmDetails = top100Films.stream()
            .filter(film -> film.getLink().equals(filmLink))
            .findFirst()
            .orElse(null);

    if (filmDetails != null) {
        String releaseYear = filmDetails.getReleaseYear();
        String filmRating = parseImdbRating(filmDetails.getRating());



        // Print data from the DataProvider
        System.out.println("Expected Film Name: " + filmName);
        System.out.println("Expected Film Link: " + filmLink);
        System.out.println("Expected Release Year: " + releaseYearFromDataProvider); // Use the parameter from DataProvider
        System.out.println("Expected Film Rating: " + filmRating);

        // Open film page
        Selenide.open(filmLink);

        // Verify film details
        verifyFilmDetailsOnPage(filmName, releaseYearFromDataProvider, filmRating);

    }
}



    private String parseImdbRating(String rawRating) {
        // Extract only the numeric part of the IMDb rating
        String[] parts = rawRating.split("\\s+");

        if (parts.length >= 1) {
            return parts[0];
        } else {
            return "Rating Not Found";
        }
    }


private void verifyFilmDetailsOnPage(String expectedFilmName, String expectedReleaseYear, String expectedFilmRating) {
    // Get film details from the page with waits
    String actualFilmTitle = Selenide.$("[data-testid='hero__primary-text']").shouldBe(Condition.exist).getText();
    String actualFilmYear = Selenide.$x("//*[@id=\"__next\"]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/ul/li[1]/a")
            .shouldBe(Condition.exist).getText();
    String actualFilmRating = Selenide.$("[class=\"sc-bde20123-1 cMEQkK\"]").shouldBe(Condition.exist).getText();

    // Log film details
    System.out.println("Actual Film Title: " + actualFilmTitle);
    System.out.println("Actual Film Year: " + actualFilmYear);
    System.out.println("Actual Film Rating: " + actualFilmRating);

    // Сравниваем значения
    Assert.assertEquals(actualFilmTitle, expectedFilmName);
    Assert.assertEquals(actualFilmYear, expectedReleaseYear);
    Assert.assertEquals(actualFilmRating, expectedFilmRating);
    if (actualFilmTitle.equals(expectedFilmName) && actualFilmYear.equals(expectedReleaseYear) && actualFilmRating.equals(expectedFilmRating)) {
        System.out.println("Verification PASSED: All values match expected values.");
    }
}
}
