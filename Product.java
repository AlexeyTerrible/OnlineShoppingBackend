import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Product {
    private int id;
    private String title;
    private float price;
    protected String category;
    private static int counter = 0;

    private static HashMap<String, ArrayList<Product>> map = new HashMap<>();

    private int generateId() {
        return ++counter;
    }

    public Product(String title, float price, String category) {
        this.id = generateId();
        this.title = title;
        this.price = price;
        this.category = category;

        addToMap();
    }

    private void addToMap() {
        map.putIfAbsent(category, new ArrayList<>());
        map.get(category).add(this);
    }

    // StreamAPI версия показа всех продуктов
    public static void showAllProducts() {
        map.forEach((key, products) -> {
            System.out.println("Категория: " + key);
            products.forEach(Product::showInfo);
            System.out.println();
        });
    }

    // Получить все продукты как список (для StreamAPI)
    public static List<Product> getAllProducts() {
        return map.values().stream()
                .flatMap(ArrayList::stream)
                .collect(Collectors.toList());
    }

    // Фильтрация продуктов по категории (StreamAPI)
    public static List<Product> getProductsByCategory(String category) {
        return map.getOrDefault(category, new ArrayList<>());
    }

    // Статистика по ценам (StreamAPI)
    public static void showPriceStatistics() {
        List<Product> allProducts = getAllProducts();
        if (allProducts.isEmpty()) return;

        double avgPrice = allProducts.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);

        double maxPrice = allProducts.stream()
                .mapToDouble(Product::getPrice)
                .max()
                .orElse(0.0);

        double minPrice = allProducts.stream()
                .mapToDouble(Product::getPrice)
                .min()
                .orElse(0.0);

        System.out.println("\n=== Статистика цен ===");
        System.out.println("Средняя цена: $" + String.format("%.2f", avgPrice));
        System.out.println("Максимальная цена: $" + maxPrice);
        System.out.println("Минимальная цена: $" + minPrice);
    }

    public abstract void showInfo();

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Float.compare(price, product.price) == 0 &&
                Objects.equals(title, product.title) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}