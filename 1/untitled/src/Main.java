import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManager manager = new ProductManager();

        System.out.println("=== Ввод данных о товарах ===");

        // Ввод количества товаров
        System.out.print("Введите количество товаров: ");
        int productCount = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        // Ввод данных о товарах
        for (int i = 0; i < productCount; i++) {
            System.out.println("\n--- Товар #" + (i + 1) + " ---");

            System.out.print("Введите ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Введите наименование: ");
            String name = scanner.nextLine();

            System.out.print("Введите UPC: ");
            String upc = scanner.nextLine();

            System.out.print("Введите производителя: ");
            String manufacturer = scanner.nextLine();

            System.out.print("Введите цену: ");
            double price = scanner.nextDouble();

            System.out.print("Введите срок хранения (в днях): ");
            int shelfLife = scanner.nextInt();

            System.out.print("Введите количество: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            // Создаем и добавляем товар
            Product product = new Product(id, name, upc, manufacturer, price, shelfLife, quantity);
            manager.addProduct(product);
        }

        // Меню для выполнения запросов
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== МЕНЮ ===");
            System.out.println("1 - Список товаров для заданного наименования");
            System.out.println("2 - Список товаров для заданного наименования с максимальной ценой");
            System.out.println("3 - Список товаров с сроком хранения больше заданного");
            System.out.println("4 - Показать все товары");
            System.out.println("0 - Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите наименование товара: ");
                    String searchName = scanner.nextLine();
                    System.out.println("\n=== Товары с наименованием '" + searchName + "' ===");
                    printProducts(manager.getProductsByName(searchName));
                    break;

                case 2:
                    System.out.print("Введите наименование товара: ");
                    String nameWithPrice = scanner.nextLine();
                    System.out.print("Введите максимальную цену: ");
                    double maxPrice = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("\n=== " + nameWithPrice + " ценой до " + maxPrice + " руб. ===");
                    printProducts(manager.getProductsByNameAndMaxPrice(nameWithPrice, maxPrice));
                    break;

                case 3:
                    System.out.print("Введите минимальный срок хранения (в днях): ");
                    int minShelfLife = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\n=== Товары со сроком хранения > " + minShelfLife + " дней ===");
                    printProducts(manager.getProductsWithShelfLifeGreaterThan(minShelfLife));
                    break;

                case 4:
                    System.out.println("=== Все товары ===");
                    printProducts(manager.getAllProducts());
                    break;

                case 0:
                    exit = true;
                    System.out.println("Выход из программы...");
                    break;

                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }

        scanner.close();
    }

    private static void printProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Товары не найдены.");
        } else {
            for (Product product : products) {
                System.out.println(product);
                System.out.println("---");
            }
        }
    }
}