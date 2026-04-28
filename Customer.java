import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Customer extends Person {
    private double wallet;
    private List<Product> cart;
    private List<Order> orderHistory; // История заказов

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

    // Снятие со счета (покупка)
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

    // Добавление продукта в корзину
    public void addProduct(Product p) {
        if (p != null) {
            cart.add(p);
            System.out.println("Товар \"" + p.getTitle() + "\" добавлен в корзину");
        }
    }

    // Удаление продукта из корзины
    public boolean removeProduct(Product p) {
        if (p != null && cart.remove(p)) {
            System.out.println("Товар \"" + p.getTitle() + "\" удален из корзины");
            return true;
        }
        return false;
    }

    // Удаление продукта по индексу
    public boolean removeProduct(int index) {
        if (index >= 0 && index < cart.size()) {
            Product removed = cart.remove(index);
            System.out.println("Товар \"" + removed.getTitle() + "\" удален из корзины");
            return true;
        }
        return false;
    }

    // Показать содержимое корзины (StreamAPI)
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

    // Подсчет общей стоимости корзины (StreamAPI)
    public double getCartTotal() {
        return cart.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    // Оформление покупки с сохранением в историю заказов
    public boolean checkout() {
        double total = getCartTotal();
        if (total == 0) {
            System.out.println("Корзина пуста. Нечего оплачивать.");
            return false;
        }

        if (hasEnoughMoney(total)) {
            // Создаем заказ перед очисткой корзины
            Order order = new Order(new ArrayList<>(cart), total);
            orderHistory.add(order);

            withdraw(total);
            cart.clear();
            System.out.println("Покупка оформлена! Списано: $" + total);
            return true;
        } else {
            System.out.println("Недостаточно средств. Нужно: $" + total
                    + ", Доступно: $" + wallet);
            return false;
        }
    }

    // Получить корзину
    public List<Product> getCart() {
        return cart;
    }

    // Очистить корзину
    public void clearCart() {
        cart.clear();
        System.out.println("Корзина очищена");
    }

    // РАСШИРЕННОЕ МЕНЮ

    // 1. Показать доступность товаров (StreamAPI)
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

    // 2. Показать историю заказов (StreamAPI)
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
                            " | Сумма: $" + order.getTotalAmount());
                    System.out.println("Товары:");
                    order.getProducts().stream()
                            .forEach(p -> System.out.println("  - " + p.getTitle() + " | $" + p.getPrice()));
                    System.out.println();
                });
    }

    // 3. Фильтрация товаров по цене (StreamAPI)
    public void showFilteredProducts(double maxPrice) {
        System.out.println("\n=== Товары дешевле $" + maxPrice + " ===");
        Product.getAllProducts().stream()
                .filter(p -> p.getPrice() <= maxPrice)
                .forEach(p -> System.out.println(p.getTitle() + " - $" + p.getPrice()));
    }

    // 4. Поиск товаров по названию (StreamAPI)
    public void searchProducts(String keyword) {
        System.out.println("\n=== Поиск товаров: \"" + keyword + "\" ===");
        List<Product> found = Product.getAllProducts().stream()
                .filter(p -> p.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (found.isEmpty()) {
            System.out.println("Товары не найдены");
        } else {
            found.forEach(p -> System.out.println(p.getTitle() + " - $" + p.getPrice()));
        }
    }

    // 5. Статистика по корзине (StreamAPI)
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

    // Получить историю заказов
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