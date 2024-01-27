package HomeWork10lesson.IMDB;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class ImdbApi {

  public final api.imdb.ImdbRestClient client = new api.imdb.ImdbRestClient();

  public ResponseBody getTopChartResponse() throws IOException {
    return client.books.getTopChart().execute().body();
  }

  public List<Map.Entry<String, FilmDetails>> getTopChart() throws IOException {
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

        // Отримати рік випуску фільму
        var ipcTitleElementsReleaseYear = ipcTitleElement.select(".sc-1e00898e-7.hcJWUf.cli-title-metadata");
        for (Element ipcTitleElementReleaseYear : ipcTitleElementsReleaseYear) {
          var div = ipcTitleElementReleaseYear.select(".sc-1e00898e-8.hsHAHC.cli-title-metadata-item").first();
          if (div != null) {
            var releaseYear = div.text();

            // Отримати значення з іншого тегу
            var ipcTitleFilmRatingElements = ipcTitleElement.select(".sc-e2dbc1a3-0.ajrIH.sc-1e00898e-2.cvciZu.cli-ratings-container");
            for (Element ipcTitleFilmRatingElement : ipcTitleFilmRatingElements) {
              var span = ipcTitleFilmRatingElement.select(".ipc-rating-star.ipc-rating-star--base.ipc-rating-star--imdb.ratingGroup--imdb-rating").first();
              if (span != null) {
                var filmRating = span.text();

                // Створюємо об'єкт FilmDetails та додаємо його до списку
                FilmDetails filmDetails = new FilmDetails(h3Text, link, releaseYear, filmRating);

                allFilms.add(new AbstractMap.SimpleEntry<>(h3Text, filmDetails));
              }
            }
          }
        }
      }
    }
    return allFilms;
  }


  public List<FilmDetails> getTop100Films() throws IOException {
    return getTopChart().stream()
            .map(Map.Entry::getValue)
            .limit(100)
            .toList();
  }

}
