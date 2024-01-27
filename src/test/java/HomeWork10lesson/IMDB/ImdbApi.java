package HomeWork10lesson.IMDB;

import java.io.IOException;
import java.util.*;

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
        var releaseYear = getReleaseYear(ipcTitleElement);

        // Отримати значення з іншого тегу
        var additionalValue = ipcTitleElement.select(".cli-title-metadata-item").text();

        // Створюємо об'єкт FilmDetails та додаємо його до списку
        FilmDetails filmDetails = new FilmDetails(h3Text, link, releaseYear, additionalValue);
        allFilms.add(new AbstractMap.SimpleEntry<>(h3Text, filmDetails));
      }
    }
    return allFilms;
  }

  private String getReleaseYear(Element ipcTitleElement) {
    var releaseYearElement = ipcTitleElement.select(".sc-1e00898e-8.hsHAHC.cli-title-metadata-item");
    if (releaseYearElement != null && !releaseYearElement.isEmpty()) {
      return releaseYearElement.text();
    }
    return "N/A";
  }

  public List<FilmDetails> getTop100Films() throws IOException {
    List<Map.Entry<String, FilmDetails>> filmEntries = getTopChart();
    return filmEntries.stream()
            .map(Map.Entry::getValue)
            .limit(100)
            .toList();
  }



}
