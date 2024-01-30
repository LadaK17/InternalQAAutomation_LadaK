package HomeWork10lesson.IMDB;

import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.text;

public class ImdbApi {

  public final api.imdb.ImdbRestClient client = new api.imdb.ImdbRestClient();

  public ResponseBody getTopChartResponse() throws IOException {
    return client.books.getTopChart().execute().body();
  }

  public List<Map.Entry<String, FilmDetails>> getTopChart() {
    try {
      var htmlString = getTopChartResponse().string();
      var document = Jsoup.parse(htmlString);
      var ipcTitleElements = document.select(".ipc-title");

      var allFilms = new ArrayList<Map.Entry<String, FilmDetails>>();

      for (Element ipcTitleElement : ipcTitleElements) {
        var aTag = ipcTitleElement.select("a").first();
        if (aTag != null) {
          var href = aTag.attr("href");
          var h3Text = aTag.select("h3").text();
          var link = api.imdb.ImdbRestClient.BASE_URL + href;

          // Extracting release year
          var releaseYearElement = ipcTitleElement.select(".div.sc-1e00898e-7.hcJWUf.cli-title-metadata").first();
          String releaseYear;

          if (releaseYearElement != null) {
            releaseYear = releaseYearElement.select(".span:nth-child(1)").text();
          } else {
            releaseYear = "Release Year Not Found";
          }

          // Extracting film rating
          var ratingElement = ipcTitleElement.select(".ipc-rating-star--imdb").first();
          var filmRating = ratingElement != null ? ratingElement.attr("aria-label").split(":")[1].trim() : "Film Rating Not Found";

          // Creating FilmDetails object and adding it to the list
          FilmDetails filmDetails = new FilmDetails(h3Text, link, releaseYear, filmRating);
          allFilms.add(new AbstractMap.SimpleEntry<>(h3Text, filmDetails));
        }
      }

      return allFilms;
    } catch (IOException e) {
      e.printStackTrace(); // Handle or log the exception as needed
      return Collections.emptyList();
    }
  }

  public FilmDetails getFilmDetails(String filmLink) {
    try {
      Document document = Jsoup.connect(filmLink).get();

      Element releaseYearElement = document.select(".ipc-title .ipc-title__metadata span:contains(Release Date)").first();
      String releaseYear = releaseYearElement != null ? releaseYearElement.text() : "Release Year Not Found";

      Element filmRatingElement = document.select(".ipc-rating-star--imdb").first();
      String[] ratingSplit = filmRatingElement != null ? filmRatingElement.attr("aria-label").split(":") : new String[0];
      String filmRating = ratingSplit.length > 1 ? ratingSplit[1].trim() : "Film Rating Not Found";

      return new FilmDetails("", filmLink, releaseYear, filmRating);
    } catch (IOException e) {
      e.printStackTrace(); // Handle or log the exception as needed
      return null;
    }
  }





  public List<FilmDetails> getTop100Films() {
    return getTopChart().stream()
            .map(Map.Entry::getValue)
            .limit(100)
            .collect(Collectors.toList());
  }

}
