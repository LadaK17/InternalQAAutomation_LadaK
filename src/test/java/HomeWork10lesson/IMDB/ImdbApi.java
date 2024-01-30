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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.text;
import static javax.management.Query.attr;

public class ImdbApi {

  public final api.imdb.ImdbRestClient client = new api.imdb.ImdbRestClient();

  public ResponseBody getTopChartResponse() throws IOException {
    return client.books.getTopChart().execute().body();
  }

  public List<Map.Entry<String, FilmDetails>> getTopChart() {
    try {
      var htmlString = getTopChartResponse().string();
      var document = Jsoup.parse(htmlString);
      var ipcTitleElements = document.select(".ipc-metadata-list-summary-item");

      var allFilms = new ArrayList<Map.Entry<String, FilmDetails>>();

      for (Element ipcTitleElement : ipcTitleElements) {
        var h3Text = ipcTitleElement.select(".ipc-title-link-wrapper h3").text().trim();
        var aTag = ipcTitleElement.select(".ipc-title-link-wrapper").first();
        if (aTag != null) {
          var href = aTag.attr("href");
          var link = api.imdb.ImdbRestClient.BASE_URL + href;

          // Extracting metadata
          var metadataElement = ipcTitleElement.select(".cli-title-metadata");
          String releaseYear;
          var ratingContainer = ipcTitleElement.select(".cli-ratings-container");
          String rating;

          if (!metadataElement.isEmpty()) {
            // Find the span with the text "IMDb rating" and get its preceding sibling span with the release year
            releaseYear = metadataElement.select("span").first().text();

            // Extract the rating from the span with class "ipc-rating-star--imdb" or "ipc-rating-star--base"
            rating = ratingContainer.select("span").first().text();

          } else {
            releaseYear = "Release Year Not Found";
            rating = "Rating Not Found";
          }

          // Creating FilmDetails object and adding it to the list
          FilmDetails filmDetails = new FilmDetails(h3Text, link, releaseYear, rating);
          allFilms.add(new AbstractMap.SimpleEntry<>(h3Text, filmDetails));
        }
      }

      return allFilms;
    } catch (IOException e) {
      e.printStackTrace(); // Handle or log the exception as needed
      return Collections.emptyList();
    }
  }


public List<FilmDetails> getTop100Films() {
  return getTopChart().stream()
          .map(Map.Entry::getValue)
          .limit(100)
          .collect(Collectors.toList());
}



}
