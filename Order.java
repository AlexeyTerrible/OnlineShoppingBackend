import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order {
    private static int orderCounter = 0;
    private int orderId;
    private List<Product> products;
    private double totalAmount;
    private String orderDate;

    public Order(List<Product> products, double totalAmount) {
        this.orderId = ++orderCounter;
        this.products = products;
        this.totalAmount = totalAmount;
        this.orderDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Order #" + orderId + " | " + orderDate + " | $" + totalAmount;
    }
}