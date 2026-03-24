import java.util.Objects;

public abstract class Product {
    private int id;
    private String title;
    private float price;
    protected String category;
    private static int counter = 0;

    private int generateId() {
        return ++counter;
    }

    public Product(String title, float price, String category) {
        this.id = generateId();
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public Product() {
    }

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public abstract void showInfo();

    @Override
    public String toString() {
        return "Product{id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}