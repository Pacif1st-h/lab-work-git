import java.util.ArrayList;
import java.util.List;

/**
 * Менеджер для выполнения запросов к репозиторию товаров.
 */
public class ProductManager {
    private ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public void addProduct(Product product) {
        repository.add(product);
    }

    public List<Product> getProductsByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : repository.getAll()) {
            if (product.getName().equalsIgnoreCase(name)) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> getProductsByNameAndMaxPrice(String name, double maxPrice) {
        List<Product> result = new ArrayList<>();
        for (Product product : repository.getAll()) {
            if (product.getName().equalsIgnoreCase(name) && product.getPrice() <= maxPrice) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> getProductsWithShelfLifeGreaterThan(int minShelfLife) {
        List<Product> result = new ArrayList<>();
        for (Product product : repository.getAll()) {
            if (product.getShelfLife() > minShelfLife) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> getAllProducts() {
        return repository.getAll();
    }
}