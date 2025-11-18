import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для хранения и управления коллекцией объектов Book.
 */
public class BookRepository {
    private List<Book> books = new ArrayList<>();

    /**
     * Метод добавления книги в репозиторий
     */
    public boolean add(Book book) {
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
     * Метод удаления книги
     */
    public boolean remove(Book book) {
        return books.remove(book);
    }

    /**
     * Метод получения всех книг
     */
    public List<Book> getAll() {
        return new ArrayList<>(books);
    }

    /**
     * Метод поиска книг по автору
     */
    public List<Book> findByAuthor(String author) {
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
     * Метод поиска книг по названию
     */
    public List<Book> findByTitle(String title) {
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
     * Метод поиска книг по году
     */
    public List<Book> findByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() == year) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Метод получения количества книг
     */
    public int size() {
        return books.size();
    }

    /**
     * Метод очистки репозитория
     */
    public void clear() {
        books.clear();
    }
}