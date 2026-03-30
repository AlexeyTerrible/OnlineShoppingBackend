import java.util.ArrayList;
import java.util.HashMap;

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


    public static void showAllProducts() {
        for (String key : map.keySet()) {
            System.out.println("Категория: " + key);
            for (Product product : map.get(key)) {
                product.showInfo();
            }
            System.out.println();
        }
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
}