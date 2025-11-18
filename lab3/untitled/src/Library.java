import java.util.ArrayList;
import java.util.List;

/**
 * Класс Library представляет библиотеку с репозиторием книг.
 */
public class Library {
    private final String name;
    private final BookRepository bookRepository;

    public Library(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название библиотеки не может быть пустым");
        }
        this.name = name.trim();
        this.bookRepository = new BookRepository(); // используем репозиторий
    }

    /**
     * Добавляет книгу в библиотеку
     */
    public boolean addBook(Book book) {
        return bookRepository.add(book);
    }

    /**
     * Ищет книги по автору
     */
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    /**
     * Ищет книги по названию
     */
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    /**
     * Ищет книги по году
     */
    public List<Book> getBooksByYear(int year) {
        return bookRepository.findByYear(year);
    }

    /**
     * Возвращает все книги
     */
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    /**
     * Возвращает количество книг
     */
    public int getBookCount() {
        return bookRepository.size();
    }

    /**
     * Удаляет книгу
     */
    public boolean removeBook(Book book) {
        return bookRepository.remove(book);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Библиотека: \"" + name + "\" (книг: " + bookRepository.size() + ")";
    }

    /**
     * Простая статистика
     */
    public String getStatistics() {
        List<Book> allBooks = bookRepository.getAll();
        if (allBooks.isEmpty()) {
            return "В библиотеке \"" + name + "\" нет книг";
        }

        // Считаем уникальных авторов
        List<String> authors = new ArrayList<>();
        for (Book book : allBooks) {
            String author = book.getAuthor();
            if (!authors.contains(author)) {
                authors.add(author);
            }
        }

        // Находим минимальный и максимальный год
        int minYear = allBooks.get(0).getYear();
        int maxYear = allBooks.get(0).getYear();
        for (Book book : allBooks) {
            int year = book.getYear();
            if (year < minYear) minYear = year;
            if (year > maxYear) maxYear = year;
        }

        return "Библиотека: \"" + name + "\"\nКниг: " + allBooks.size() +
                ", Авторов: " + authors.size() + ", Годы: " + minYear + "-" + maxYear;
    }
}