/**
 * Класс Book представляет книгу с названием, автором и годом написания.
 */
public class Book {
    private final String title;
    private final String author;
    private final int year;

    public Book(String title, String author, int year) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Автор книги не может быть пустым");
        }
        if (year < 0 || year > 2100) {
            throw new IllegalArgumentException("Некорректный год издания");
        }

        this.title = title.trim();
        this.author = author.trim();
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "\"" + title + "\" - " + author + " (" + year + " г.)";
    }
}