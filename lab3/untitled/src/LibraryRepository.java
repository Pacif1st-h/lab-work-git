import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для хранения и управления коллекцией объектов Library.
 */
public class LibraryRepository {
    private List<Library> libraries = new ArrayList<>();

    /**
     * Метод добавления библиотеки в репозиторий
     */
    public boolean add(Library library) {
        if (library == null) {
            return false;
        }

        // Проверяем, есть ли уже библиотека с таким именем
        for (Library existingLibrary : libraries) {
            if (existingLibrary.getName().equalsIgnoreCase(library.getName())) {
                return false; // библиотека уже есть
            }
        }

        libraries.add(library);
        return true;
    }

    /**
     * Метод удаления библиотеки
     */
    public boolean remove(Library library) {
        return libraries.remove(library);
    }

    /**
     * Метод получения всех библиотек
     */
    public List<Library> getAll() {
        return new ArrayList<>(libraries);
    }

    /**
     * Метод получения библиотеки по индексу
     */
    public Library get(int index) {
        if (index >= 0 && index < libraries.size()) {
            return libraries.get(index);
        }
        return null;
    }

    /**
     * Метод получения количества библиотек
     */
    public int size() {
        return libraries.size();
    }

    /**
     * Метод очистки репозитория
     */
    public void clear() {
        libraries.clear();
    }
}