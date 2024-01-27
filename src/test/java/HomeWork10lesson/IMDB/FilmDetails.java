package HomeWork10lesson.IMDB;

public class FilmDetails {
    private String name;
    private String link;
    private String releaseYear;
    private String additionalValue;

    // Конструктор з параметрами
    public FilmDetails(String name, String link, String releaseYear, String additionalValue) {
        this.name = name;
        this.link = link;
        this.releaseYear = releaseYear;
        this.additionalValue = additionalValue;
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

    public String getAdditionalValue() {
        return additionalValue;
    }

    public void setAdditionalValue(String additionalValue) {
        this.additionalValue = additionalValue;
    }
}
