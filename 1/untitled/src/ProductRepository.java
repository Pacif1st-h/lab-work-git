import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для хранения и управления коллекцией объектов Product.
 */
public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    /**
     * Метод добавления товара в репозиторий
     */
    public void add(Product product) {
        products.add(product);
        System.out.println("Добавлен товар: " + product.getName());
    }

    /**
     * Метод удаления товара по ID
     */
    public boolean remove(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                System.out.println("Удалён товар с ID: " + id);
                return true;
            }
        }
        System.out.println("Товар с ID " + id + " не найден.");
        return false;
    }

    /**
     * Метод изменения товара по ID
     */
    public boolean update(int id, Product newProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, newProduct);
                System.out.println("Обновлён товар с ID: " + id);
                return true;
            }
        }
        System.out.println("Товар с ID " + id + " не найден.");
        return false;
    }

    /**
     * Метод получения товара по ID
     */
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        System.out.println("Товар с ID " + id + " не найден.");
        return null;
    }

    /**
     * Метод получения всех товаров
     */
    public List<Product> getAll() {
        return new ArrayList<>(products);
    }

    /**
     * Метод получения количества товаров
     */
    public int size() {
        return products.size();
    }

    /**
     * Метод очистки репозитория
     */
    public void clear() {
        products.clear();
        System.out.println("Репозиторий очищен.");
    }
}