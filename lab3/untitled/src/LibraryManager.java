import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Основная программа для управления библиотеками
 */
public class LibraryManager {
    private static LibraryRepository libraryRepository = new LibraryRepository();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== СИСТЕМА УПРАВЛЕНИЯ БИБЛИОТЕКАМИ ===");
        showMainMenu();
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
            System.out.println("1. Управление библиотеками");
            System.out.println("2. Управление книгами");
            System.out.println("3. Поиск книг");
            System.out.println("4. Статистика");
            System.out.println("0. Выход");

            int choice = getIntInput("Выберите действие: ");

            switch (choice) {
                case 1:
                    showLibraryManagementMenu();
                    break;
                case 2:
                    showBookManagementMenu();
                    break;
                case 3:
                    showSearchMenu();
                    break;
                case 4:
                    showStatistics();
                    break;
                case 0:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }
    }

    private static void showLibraryManagementMenu() {
        while (true) {
            System.out.println("\n=== УПРАВЛЕНИЕ БИБЛИОТЕКАМИ ===");
            System.out.println("1. Создать библиотеку");
            System.out.println("2. Показать все библиотеки");
            System.out.println("3. Удалить библиотеку");
            System.out.println("0. Назад");

            int choice = getIntInput("Выберите действие: ");

            switch (choice) {
                case 1:
                    createLibrary();
                    break;
                case 2:
                    showAllLibraries();
                    break;
                case 3:
                    deleteLibrary();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }
    }

    private static void showBookManagementMenu() {
        if (libraryRepository.size() == 0) {
            System.out.println("Сначала создайте хотя бы одну библиотеку!");
            return;
        }

        while (true) {
            System.out.println("\n=== УПРАВЛЕНИЕ КНИГАМИ ===");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Показать все книги в библиотеке");
            System.out.println("0. Назад");

            int choice = getIntInput("Выберите действие: ");

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    showAllBooksInLibrary();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }
    }

    private static void showSearchMenu() {
        if (libraryRepository.size() == 0) {
            System.out.println("Сначала создайте хотя бы одну библиотеку!");
            return;
        }

        while (true) {
            System.out.println("\n=== ПОИСК КНИГ ===");
            System.out.println("1. Найти книги по автору");
            System.out.println("2. Найти книги по названию");
            System.out.println("3. Найти книги по году");
            System.out.println("0. Назад");

            int choice = getIntInput("Выберите действие: ");

            switch (choice) {
                case 1:
                    searchBooksByAuthor();
                    break;
                case 2:
                    searchBooksByTitle();
                    break;
                case 3:
                    searchBooksByYear();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }
    }

    // === РЕАЛИЗАЦИЯ МЕТОДОВ ===

    private static void createLibrary() {
        System.out.print("Введите название библиотеки: ");
        String name = scanner.nextLine();

        try {
            Library library = new Library(name);
            if (libraryRepository.add(library)) {
                System.out.println("Библиотека \"" + name + "\" успешно создана!");
            } else {
                System.out.println("Библиотека с таким названием уже существует.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void showAllLibraries() {
        if (libraryRepository.size() == 0) {
            System.out.println("Библиотек нет.");
            return;
        }

        System.out.println("\n=== СПИСОК БИБЛИОТЕК ===");
        List<Library> libraries = libraryRepository.getAll();
        for (int i = 0; i < libraries.size(); i++) {
            System.out.println((i + 1) + ". " + libraries.get(i));
        }
    }

    private static void deleteLibrary() {
        showAllLibraries();
        if (libraryRepository.size() == 0) return;

        int index = getIntInput("Введите номер библиотеки для удаления: ") - 1;
        if (index >= 0 && index < libraryRepository.size()) {
            Library library = libraryRepository.get(index);
            String name = library.getName();
            libraryRepository.remove(library);
            System.out.println("Библиотека \"" + name + "\" удалена.");
        } else {
            System.out.println("Неверный номер библиотеки.");
        }
    }

    private static void addBook() {
        Library library = selectLibrary();
        if (library == null) return;

        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();

        System.out.print("Введите автора книги: ");
        String author = scanner.nextLine();

        int year = getIntInput("Введите год издания: ");

        try {
            Book book = new Book(title, author, year);
            if (library.addBook(book)) {
                System.out.println("Книга успешно добавлена в библиотеку!");
            } else {
                System.out.println("Такая книга уже существует в библиотеке.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void showAllBooksInLibrary() {
        Library library = selectLibrary();
        if (library == null) return;

        List<Book> books = library.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("В библиотеке нет книг.");
            return;
        }

        System.out.println("\n=== КНИГИ В БИБЛИОТЕКЕ \"" + library.getName() + "\" ===");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }

    private static void searchBooksByAuthor() {
        System.out.print("Введите автора для поиска: ");
        String author = scanner.nextLine();

        System.out.println("\n=== РЕЗУЛЬТАТЫ ПОИСКА ПО АВТОРУ: " + author + " ===");
        boolean found = false;

        List<Library> libraries = libraryRepository.getAll();
        for (Library library : libraries) {
            List<Book> books = library.getBooksByAuthor(author);
            if (!books.isEmpty()) {
                found = true;
                System.out.println("\nВ библиотеке \"" + library.getName() + "\":");
                for (int i = 0; i < books.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + books.get(i));
                }
            }
        }

        if (!found) {
            System.out.println("Книги автора \"" + author + "\" не найдены.");
        }
    }

    private static void searchBooksByTitle() {
        System.out.print("Введите название книги для поиска: ");
        String title = scanner.nextLine();

        System.out.println("\n=== РЕЗУЛЬТАТЫ ПОИСКА ПО НАЗВАНИЮ: " + title + " ===");
        boolean found = false;

        List<Library> libraries = libraryRepository.getAll();
        for (Library library : libraries) {
            List<Book> books = library.searchBooksByTitle(title);
            if (!books.isEmpty()) {
                found = true;
                System.out.println("\nВ библиотеке \"" + library.getName() + "\":");
                for (int i = 0; i < books.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + books.get(i));
                }
            }
        }

        if (!found) {
            System.out.println("Книги с названием \"" + title + "\" не найдены.");
        }
    }

    private static void searchBooksByYear() {
        int year = getIntInput("Введите год для поиска: ");

        System.out.println("\n=== РЕЗУЛЬТАТЫ ПОИСКА ПО ГОДУ: " + year + " ===");
        boolean found = false;

        List<Library> libraries = libraryRepository.getAll();
        for (Library library : libraries) {
            List<Book> books = library.getBooksByYear(year);
            if (!books.isEmpty()) {
                found = true;
                System.out.println("\nВ библиотеке \"" + library.getName() + "\":");
                for (int i = 0; i < books.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + books.get(i));
                }
            }
        }

        if (!found) {
            System.out.println("Книги " + year + " года не найдены.");
        }
    }

    private static void showStatistics() {
        if (libraryRepository.size() == 0) {
            System.out.println("Библиотек нет.");
            return;
        }

        System.out.println("\n=== СТАТИСТИКА ===");
        int totalBooks = 0;

        List<Library> libraries = libraryRepository.getAll();
        for (Library library : libraries) {
            System.out.println(library.getStatistics());
            totalBooks += library.getBookCount();
        }

        System.out.println("\nОБЩАЯ СТАТИСТИКА:");
        System.out.println("Всего библиотек: " + libraries.size());
        System.out.println("Всего книг: " + totalBooks);
    }

    // === ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ===

    private static Library selectLibrary() {
        if (libraryRepository.size() == 0) {
            System.out.println("Библиотек нет.");
            return null;
        }

        showAllLibraries();
        int index = getIntInput("Выберите библиотеку: ") - 1;

        if (index >= 0 && index < libraryRepository.size()) {
            return libraryRepository.get(index);
        } else {
            System.out.println("Неверный номер библиотеки.");
            return null;
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число!");
            }
        }
    }
}