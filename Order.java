import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// IMMUTABLE OBJECT
public final class Order {
    private static int orderCounter = 0;
    private final int orderId;
    private final List<Product> products; // Неизменяемая копия
    private final double totalAmount;
    private final String orderDate;
    private final boolean success;
    private final String message;

    // Приватный конструктор для Builder
    private Order(Builder builder) {
        this.orderId = builder.orderId;
        // Защитная копия для иммутабельности
        this.products = Collections.unmodifiableList(new ArrayList<>(builder.products));
        this.totalAmount = builder.totalAmount;
        this.orderDate = builder.orderDate;
        this.success = builder.success;
        this.message = builder.message;
    }

    // Builder Pattern
    public static class Builder {
        private int orderId;
        private List<Product> products = new ArrayList<>();
        private double totalAmount;
        private String orderDate;
        private boolean success = true;
        private String message = "Заказ успешно оформлен";

        public Builder() {
            this.orderId = ++orderCounter;
            this.orderDate = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        }

        public Builder products(List<Product> products) {
            this.products = new ArrayList<>(products);
            this.totalAmount = products.stream()
                    .mapToDouble(Product::getPrice)
                    .sum();
            return this;
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    // Только геттеры, нет сеттеров
    public int getOrderId() {
        return orderId;
    }

    public List<Product> getProducts() {
        return products; // Возвращаем неизменяемый список
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Order #" + orderId + " | " + orderDate + " | $" + totalAmount +
                " | " + (success ? "SUCCESS" : "FAILED") + " | " + message;
    }
}