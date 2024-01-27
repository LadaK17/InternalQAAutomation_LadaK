package HomeWork10lesson.IMDB;

public class FilmDetails {
    private String name;
    private String link;
    private String releaseYear;
    private String filmRating;

    // Конструктор з параметрами
    public FilmDetails(String name, String link, String releaseYear, String filmRating) {
        this.name = name;
        this.link = link;
        this.releaseYear = releaseYear;
        this.filmRating = filmRating;
    }

    // Геттери і сеттери для всіх полів

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getFilmRating() {
        return filmRating;
    }

    public void setFilmRating(String filmRating) {
        this.filmRating = filmRating;
    }
}
