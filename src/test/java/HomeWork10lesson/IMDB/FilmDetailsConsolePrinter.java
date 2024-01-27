package HomeWork10lesson.IMDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FilmDetailsConsolePrinter {

    private static final Logger logger = LoggerFactory.getLogger(FilmDetailsConsolePrinter.class);

    public static void printFilmDetails(List<FilmDetails> films) {
        for (FilmDetails film : films) {
            logger.debug("Film Name: {}", film.getName());
            logger.debug("Film Link: {}", film.getLink());
            logger.debug("Film Year: {}", film.getReleaseYear());
            logger.debug("Film Rating: {}", film.getFilmRating());
            logger.debug("-------------------------");

            // Add System.out.println for debugging
            System.out.println("Film Name: " + film.getName());
            System.out.println("Film Link: " + film.getLink());
            System.out.println("Film Year: " + film.getReleaseYear());
            System.out.println("Film Rating: " + film.getFilmRating());
            System.out.println("-------------------------");
        }
    }

    public static void main(String[] args) {
        try {
            ImdbApi imdbApi = new ImdbApi();
            List<FilmDetails> films = imdbApi.getTop100Films();
            printFilmDetails(films);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
