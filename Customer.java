import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Predicate;

public class Customer extends Person {
    private double wallet;
    private List<Product> cart;
    private List<Order> orderHistory;

    public Customer(String n, String s, int b) {
        super(n, s, b);
        this.wallet = 0.0;
        this.cart = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    public Customer(String n, String s, int b, double initialWallet) {
        super(n, s, b, initialWallet);
        this.wallet = initialWallet;
        this.cart = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    @Override
    public void deposit(double q) {
        if (q > 0) {
            wallet += q;
            super.deposit(q);
            System.out.println("Кошелек пополнен на " + q);
        } else {
            System.out.println("Сумма пополнения должна быть положительной");
        }
    }

    public boolean withdraw(double q) {
        if (q <= 0 || q > wallet) return false;
        wallet -= q;
        super.pay(q);
        return true;
    }

    @Override
    public double checkBalance() {
        return wallet;
    }

    @Override
    public boolean hasEnoughMoney(double q) {
        return wallet >= q;
    }

    @Override
    public Financeble.FinanceStatus getFinanceStatus() {
        if (wallet <= 0) {
            return Financeble.FinanceStatus.INSOLVENT;
        } else if (wallet < 1000) {
            return Financeble.FinanceStatus.LIMITED;
        } else {
            return Financeble.FinanceStatus.SOLVENT;
        }
    }

    public void addProduct(Product p) {
        if (p != null) {
            cart.add(p);
            System.out.println("Товар \"" + p.getTitle() + "\" добавлен в корзину");
        }
    }

    public boolean removeProduct(Product p) {
        if (p != null && cart.remove(p)) {
            System.out.println("Товар \"" + p.getTitle() + "\" удален из корзины");
            return true;
        }
        return false;
    }

    public boolean removeProduct(int index) {
        if (index >= 0 && index < cart.size()) {
            Product removed = cart.remove(index);
            System.out.println("Товар \"" + removed.getTitle() + "\" удален из корзины");
            return true;
        }
        return false;
    }

    public void showCart() {
        if (cart.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println("Содержимое корзины:");
            cart.stream()
                    .map(p -> (cart.indexOf(p) + 1) + ". " + p.getTitle() + " - $" + p.getPrice())
                    .forEach(System.out::println);
            System.out.println("Общая стоимость: $" + getCartTotal());
        }
    }

    // STRATEGY PATTERN - можно менять стратегию подсчета цены
    @FunctionalInterface
    public interface PricingStrategy {
        double calculateTotal(List<Product> products);
    }

    public double getCartTotal() {
        return getCartTotal(products -> products.stream()
                .mapToDouble(Product::getPrice)
                .sum());
    }

    // STRATEGY PATTERN - метод принимает стратегию
    public double getCartTotal(PricingStrategy strategy) {
        return strategy.calculateTotal(cart);
    }

    // STRATEGY PATTERN - фильтрация с разными стратегиями
    public List<Product> filterProducts(Predicate<Product> filterStrategy) {
        return cart.stream()
                .filter(filterStrategy)
                .collect(Collectors.toList());
    }

    // Оформление покупки с Immutable Order
    public boolean checkout() {
        return checkout(products -> products.stream()
                .mapToDouble(Product::getPrice)
                .sum());
    }

    // STRATEGY PATTERN - checkout с разными стратегиями расчета
    public boolean checkout(PricingStrategy strategy) {
        double total = strategy.calculateTotal(cart);

        if (cart.isEmpty()) {
            System.out.println("Корзина пуста. Нечего оплачивать.");
            return false;
        }

        if (hasEnoughMoney(total)) {
            // Создаем Immutable Order через Builder
            Order order = new Order.Builder()
                    .products(new ArrayList<>(cart))
                    .success(true)
                    .message("Заказ успешно оформлен")
                    .build();

            orderHistory.add(order);
            withdraw(total);
            cart.clear();

            System.out.println("Покупка оформлена! " + order.getMessage());
            System.out.println("Номер заказа: #" + order.getOrderId());
            System.out.println("Списано: $" + order.getTotalAmount());
            return true;
        } else {
            // Создаем неуспешный заказ для истории
            Order failedOrder = new Order.Builder()
                    .products(new ArrayList<>(cart))
                    .success(false)
                    .message("Недостаточно средств. Нужно: $" + total +
                            ", Доступно: $" + wallet)
                    .build();

            orderHistory.add(failedOrder);
            System.out.println(failedOrder.getMessage());
            return false;
        }
    }

    public List<Product> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
        System.out.println("Корзина очищена");
    }

    public void showProductAvailability() {
        System.out.println("\n=== Доступность товаров ===");
        Product.getAllProducts().stream()
                .collect(Collectors.groupingBy(Product::getCategory))
                .forEach((category, products) -> {
                    System.out.println("Категория: " + category);
                    products.forEach(p ->
                            System.out.println("  - " + p.getTitle() + " | $" + p.getPrice() + " | Доступен")
                    );
                });
    }

    public void showOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("\nИстория заказов пуста");
            return;
        }

        System.out.println("\n=== История заказов ===");
        orderHistory.stream()
                .forEach(order -> {
                    System.out.println("Заказ #" + order.getOrderId() +
                            " | Дата: " + order.getOrderDate() +
                            " | Сумма: $" + order.getTotalAmount() +
                            " | Статус: " + (order.isSuccess() ? "УСПЕХ" : "ОТКАЗ"));
                    System.out.println("  Сообщение: " + order.getMessage());
                    System.out.println("Товары:");
                    order.getProducts().stream()
                            .forEach(p -> System.out.println("  - " + p.getTitle() + " | $" + p.getPrice()));
                    System.out.println();
                });
    }

    public void showFilteredProducts(double maxPrice) {
        System.out.println("\n=== Товары дешевле $" + maxPrice + " ===");
        // STRATEGY PATTERN - используем Predicate как стратегию фильтрации
        filterProducts(p -> p.getPrice() <= maxPrice)
                .forEach(p -> System.out.println(p.getTitle() + " - $" + p.getPrice()));
    }

    public void searchProducts(String keyword) {
        System.out.println("\n=== Поиск товаров: \"" + keyword + "\" ===");
        // STRATEGY PATTERN - используем Predicate как стратегию поиска
        List<Product> found = Product.getAllProducts().stream()
                .filter(p -> p.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (found.isEmpty()) {
            System.out.println("Товары не найдены");
        } else {
            found.forEach(p -> System.out.println(p.getTitle() + " - $" + p.getPrice()));
        }
    }

    public void showCartStatistics() {
        if (cart.isEmpty()) {
            System.out.println("\nКорзина пуста, статистика недоступна");
            return;
        }

        double avgPrice = cart.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);

        Product cheapest = cart.stream()
                .min((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                .orElse(null);

        Product mostExpensive = cart.stream()
                .max((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                .orElse(null);

        System.out.println("\n=== Статистика корзины ===");
        System.out.println("Количество товаров: " + cart.size());
        System.out.println("Средняя цена: $" + String.format("%.2f", avgPrice));
        System.out.println("Самый дешевый: " + (cheapest != null ? cheapest.getTitle() + " ($" + cheapest.getPrice() + ")" : "N/A"));
        System.out.println("Самый дорогой: " + (mostExpensive != null ? mostExpensive.getTitle() + " ($" + mostExpensive.getPrice() + ")" : "N/A"));
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", id=" + getId() +
                ", wallet=" + wallet +
                ", cartSize=" + cart.size() +
                ", ordersCount=" + orderHistory.size() +
                ", financeStatus=" + getFinanceStatus() +
                '}';
    }
}