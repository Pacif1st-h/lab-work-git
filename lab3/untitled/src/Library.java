import java.util.ArrayList;
import java.util.List;

/**
 * Класс Library представляет библиотеку с коллекцией книг.
 */
public class Library {
    private final String name;
    private final List<Book> books;

    public Library(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название библиотеки не может быть пустым");
        }
        this.name = name.trim();
        this.books = new ArrayList<>();
    }

    /**
     * Добавляет книгу в библиотеку
     */
    public boolean addBook(Book book) {
        if (book == null) {
            return false;
        }

        // Проверяем, есть ли уже такая книга
        for (Book existingBook : books) {
            if (existingBook.getTitle().equalsIgnoreCase(book.getTitle()) &&
                    existingBook.getAuthor().equalsIgnoreCase(book.getAuthor()) &&
                    existingBook.getYear() == book.getYear()) {
                return false; // книга уже есть
            }
        }

        books.add(book);
        return true;
    }

    /**
     * Ищет книги по автору
     */
    public List<Book> getBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        if (author == null || author.trim().isEmpty()) {
            return result;
        }

        String searchAuthor = author.trim().toLowerCase();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().equals(searchAuthor)) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Ищет книги по названию
     */
    public List<Book> searchBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        if (title == null || title.trim().isEmpty()) {
            return result;
        }

        String searchTitle = title.trim().toLowerCase();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchTitle)) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Ищет книги по году
     */
    public List<Book> getBooksByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() == year) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Возвращает все книги
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Возвращает количество книг
     */
    public int getBookCount() {
        return books.size();
    }

    /**
     * Удаляет книгу
     */
    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Библиотека: \"" + name + "\" (книг: " + books.size() + ")";
    }

    /**
     * Простая статистика
     */
    public String getStatistics() {
        if (books.isEmpty()) {
            return "В библиотеке \"" + name + "\" нет книг";
        }

        // Считаем уникальных авторов
        List<String> authors = new ArrayList<>();
        for (Book book : books) {
            String author = book.getAuthor();
            if (!authors.contains(author)) {
                authors.add(author);
            }
        }

        // Находим минимальный и максимальный год
        int minYear = books.get(0).getYear();
        int maxYear = books.get(0).getYear();
        for (Book book : books) {
            int year = book.getYear();
            if (year < minYear) minYear = year;
            if (year > maxYear) maxYear = year;
        }

        return "Библиотека: \"" + name + "\"\nКниг: " + books.size() +
                ", Авторов: " + authors.size() + ", Годы: " + minYear + "-" + maxYear;
    }
}